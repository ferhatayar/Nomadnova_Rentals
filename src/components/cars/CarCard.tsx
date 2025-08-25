import React from 'react';
import { Card, CardMedia, CardContent, Typography, CardActions, Button, Stack } from '@mui/material';
import type { Car } from '../../types/car';
import { useNavigate } from 'react-router-dom';

interface Props {
  car: Car;
}

export const CarCard: React.FC<Props> = ({ car }) => {
  const navigate = useNavigate();
  return (
    <Card>
      <CardMedia component="img" height="160" image={car.coverImageUrl || 'https://picsum.photos/640/360'} alt={`${car.brand} ${car.model}`} />
      <CardContent>
        <Typography variant="h6" fontWeight={700}>{car.brand} {car.model}</Typography>
        <Stack direction="row" spacing={2} mt={1}>
          <Typography color="text.secondary">{car.fuel_type || 'Yakıt Bilinmiyor'}</Typography>
          <Typography color="text.secondary">{car.year || '-'} Model</Typography>
        </Stack>
        <Typography mt={1} fontWeight={700}>{car.price_per_day} ₺/gün</Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick={() => navigate(`/cars/${car.id}`)}>Detay</Button>
        <Button size="small" variant="contained">Kirala</Button>
      </CardActions>
    </Card>
  );
};


