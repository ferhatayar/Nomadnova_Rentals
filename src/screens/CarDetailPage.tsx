import React, { useEffect, useMemo, useState } from 'react';
import { Container, Box, Typography, Grid, Stack, Chip, Button, Card, CardMedia, CardContent, TextField, MenuItem } from '@mui/material';
import { useParams } from 'react-router-dom';
import { getCarById } from '../services/carService';
import type { Car } from '../types/car';
import { saveRental } from '../services/rentalService';
import { savePayment } from '../services/paymentService';
import { savePurchase } from '../services/purchaseService';
import { useSnackbar } from 'notistack';
import { useAuth } from '../context/AuthContext';

export const CarDetailPage: React.FC = () => {
  const { id } = useParams();
  const [car, setCar] = useState<Car | null>(null);
  const [loading, setLoading] = useState(false);
  const { enqueueSnackbar } = useSnackbar();
  const { userId } = useAuth();

  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [paymentMethod, setPaymentMethod] = useState<'CARD' | 'BANK_TRANSFER'>('CARD');

  useEffect(() => {
    const load = async () => {
      if (!id) return;
      setLoading(true);
      try {
        const c = await getCarById(id);
        setCar(c as Car);
      } finally {
        setLoading(false);
      }
    };
    load();
  }, [id]);

  const images = useMemo(() => car?.carImages?.map((i) => i.imageUrl).filter(Boolean) || [], [car]);

  return (
    <Container maxWidth="lg">
      <Box py={4}>
        {car && (
          <>
            <Typography variant="h4" fontWeight={800} gutterBottom>
              {car.brand} {car.model} {car.year}
            </Typography>
            <Grid container spacing={3}>
              <Grid item xs={12} md={7}>
                <Card sx={{ borderRadius: 2, overflow: 'hidden' }}>
                  <CardMedia component="img" height={360} image={images[0] || car.coverImageUrl || 'https://picsum.photos/800/480'} alt={`${car.brand} ${car.model}`} />
                </Card>
                <Stack direction="row" spacing={1} mt={1} sx={{ overflowX: 'auto' }}>
                  {images.slice(1).map((src, idx) => (
                    <Box key={idx} sx={{ width: 120, height: 80, borderRadius: 1, overflow: 'hidden' }}>
                      <img src={src} alt={`thumb-${idx}`} style={{ width: '100%', height: '100%', objectFit: 'cover' }} />
                    </Box>
                  ))}
                </Stack>
              </Grid>
              <Grid item xs={12} md={5}>
                <Card sx={{ borderRadius: 2 }}>
                  <CardContent>
                    <Stack spacing={1} mb={2} direction="row" flexWrap="wrap" useFlexGap>
                      <Chip label={car.fuelType} />
                      <Chip label={car.transmission} />
                      <Chip label={car.status} />
                    </Stack>
                    <Typography variant="h5" fontWeight={900}>₺{car.pricePerDay} / gün</Typography>
                    <Typography color="text.secondary" mb={2}>Satış: ₺{car.priceForSale}</Typography>

                    <Typography variant="subtitle1" fontWeight={700} gutterBottom>Kiralama</Typography>
                    <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2} mb={2}>
                      <TextField fullWidth type="date" label="Başlangıç" value={startDate} onChange={(e) => setStartDate(e.target.value)} InputLabelProps={{ shrink: true }} />
                      <TextField fullWidth type="date" label="Bitiş" value={endDate} onChange={(e) => setEndDate(e.target.value)} InputLabelProps={{ shrink: true }} />
                    </Stack>
                    <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                      <TextField select fullWidth label="Ödeme Yöntemi" value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value as any)}>
                        <MenuItem value="CARD">Kart</MenuItem>
                        <MenuItem value="BANK_TRANSFER">Havale/EFT</MenuItem>
                      </TextField>
                      <Button variant="contained" fullWidth disabled={loading} onClick={async () => {
                        if (!car || !id || !startDate || !endDate) return enqueueSnackbar('Tarihleri seçiniz', { variant: 'warning' });
                        try {
                          setLoading(true);
                          // 1) Ödeme oluştur
                          const payment = await savePayment({ amount: Number(car.pricePerDay) * 3, paymentMethod, paymentDate: new Date().toISOString().slice(0,10), status: 'SUCCESS' });
                          const rental = await saveRental({ carId: id, userId: userId || 0, startDate, endDate, totalPrice: Number(car.pricePerDay) * 3, status: 'CONFIRMED', paymentId: payment.id });
                          enqueueSnackbar('Kiralama oluşturuldu', { variant: 'success' });
                        } catch (e) {
                          enqueueSnackbar('Kiralama başarısız', { variant: 'error' });
                        } finally { setLoading(false); }
                      }}>Kirala</Button>
                    </Stack>

                    <Box mt={3}>
                      <Typography variant="subtitle1" fontWeight={700} gutterBottom>Satın Alma</Typography>
                      <Button variant="outlined" fullWidth disabled={loading} onClick={async () => {
                        if (!car || !id) return;
                        try {
                          setLoading(true);
                          const payment = await savePayment({ amount: Number(car.priceForSale || 0), paymentMethod, paymentDate: new Date().toISOString().slice(0,10), status: 'SUCCESS' });
                          await savePurchase({ carId: id, userId: userId || 0, purchaseDate: new Date().toISOString().slice(0,10), price: Number(car.priceForSale || 0), paymentId: payment.id });
                          enqueueSnackbar('Satın alma tamamlandı', { variant: 'success' });
                        } catch (e) {
                          enqueueSnackbar('Satın alma başarısız', { variant: 'error' });
                        } finally { setLoading(false); }
                      }}>Satın Al</Button>
                    </Box>
                  </CardContent>
                </Card>
              </Grid>
            </Grid>
          </>
        )}
        {!car && !loading && (
          <Typography color="text.secondary">Araç bilgisi yükleniyor veya bulunamadı.</Typography>
        )}
      </Box>
    </Container>
  );
};


