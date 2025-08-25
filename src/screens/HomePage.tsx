import React, { useEffect, useState } from 'react';
import { Box, Typography, CircularProgress, Alert, Card, CardContent, CardMedia, Button, Chip } from '@mui/material';
import { CarFilters } from '../components/cars/CarFilters';
import type { FiltersForm } from '../components/cars/CarFilters';
import { listCars } from '../services/carService';
import type { Car } from '../types/car';
import { DirectionsCar, LocalGasStation, Settings, CalendarToday } from '@mui/icons-material';

export const HomePage: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [cars, setCars] = useState<Car[]>([]);
  const [error, setError] = useState<string | null>(null);

  const fetchCars = async (filters?: FiltersForm) => {
    setLoading(true);
    setError(null);
    try {
      const data = await listCars({
        brand: filters?.brand || undefined,
        model: filters?.model || undefined,
        minPrice: filters?.minPrice || undefined,
        maxPrice: filters?.maxPrice || undefined,
        fuelType: filters?.fuelType || undefined,
      });
      
      // Debug: response formatını kontrol et
      console.log('Response data:', data);
      console.log('Data type:', typeof data);
      console.log('Is array:', Array.isArray(data));
      
      // Eğer data bir object ise ve payload property'si varsa
      if (data && typeof data === 'object' && 'payload' in data) {
        setCars(Array.isArray(data.payload) ? data.payload : []);
      } else if (Array.isArray(data)) {
        setCars(data);
      } else {
        console.error('Unexpected data format:', data);
        setCars([]);
      }
    } catch (err: any) {
      console.error('Araç listesi yüklenirken hata:', err);
      setError(err.response?.data?.message || 'Araç listesi yüklenirken bir hata oluştu');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { fetchCars(); }, []);

  const getFuelTypeIcon = (fuelType: string) => {
    switch (fuelType) {
      case 'BENZİN': return <LocalGasStation sx={{ fontSize: 16 }} />;
      case 'DİZEL': return <LocalGasStation sx={{ fontSize: 16 }} />;
      case 'ELEKTRİK': return <Settings sx={{ fontSize: 16 }} />;
      case 'HİBRİT': return <Settings sx={{ fontSize: 16 }} />;
      default: return <LocalGasStation sx={{ fontSize: 16 }} />;
    }
  };

  return (
    <Box sx={{ minHeight: '100vh', background: '#f8fafc' }}>
      {/* Hero Section - Full Width */}
      <Box
        sx={{
          background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
          color: 'white',
          py: 12,
          width: '100%',
          position: 'relative',
          overflow: 'hidden'
        }}
      >
        {/* Background Pattern */}
        <Box
          sx={{
            position: 'absolute',
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            background: 'url("data:image/svg+xml,%3Csvg width="60" height="60" viewBox="0 0 60 60" xmlns="http://www.w3.org/2000/svg"%3E%3Cg fill="none" fill-rule="evenodd"%3E%3Cg fill="%23ffffff" fill-opacity="0.05"%3E%3Ccircle cx="30" cy="30" r="2"/%3E%3C/g%3E%3C/g%3E%3C/svg%3E")',
            opacity: 0.3
          }}
        />
        
        <Box sx={{ 
          maxWidth: '1200px', 
          mx: 'auto', 
          px: { xs: 2, sm: 3, md: 4 },
          position: 'relative',
          zIndex: 1
        }}>
          <Box textAlign="center">
            <DirectionsCar sx={{ fontSize: 80, mb: 3, opacity: 0.9 }} />
            <Typography variant="h1" fontWeight={800} gutterBottom sx={{ fontSize: { xs: '2.5rem', sm: '3.5rem', md: '4rem' } }}>
              NomadNova Rentals
            </Typography>
            <Typography variant="h4" sx={{ opacity: 0.9, mb: 4, fontWeight: 500 }}>
              Araç kiralama ve satış platformu
            </Typography>
            <Typography variant="h6" sx={{ opacity: 0.8, maxWidth: 800, mx: 'auto', lineHeight: 1.6 }}>
              Geniş araç filomuzdan size en uygun olanını seçin. Güvenli, hızlı ve uygun fiyatlı kiralama hizmeti.
            </Typography>
          </Box>
        </Box>
      </Box>

      {/* Main Content */}
      <Box sx={{ 
        maxWidth: '1200px', 
        mx: 'auto', 
        px: { xs: 2, sm: 3, md: 4 },
        py: 6
      }}>
        {/* Filters Section */}
        <Box mb={8}>
          <Typography variant="h3" fontWeight={700} gutterBottom textAlign="center" mb={6} color="text.primary">
            Araç Ara
          </Typography>
          <Box maxWidth={900} mx="auto">
            <CarFilters onApply={(f) => fetchCars(f)} />
          </Box>
        </Box>

        {/* Error Alert */}
        {error && (
          <Box mb={4}>
            <Alert severity="error" onClose={() => setError(null)}>
              {error}
            </Alert>
          </Box>
        )}

        {/* Loading */}
        {loading && (
          <Box display="flex" justifyContent="center" py={8}>
            <CircularProgress size={80} sx={{ color: '#667eea' }} />
          </Box>
        )}

        {/* Cars Grid */}
        {!loading && (
          <>
            {Array.isArray(cars) && cars.length > 0 ? (
              <Box
                sx={{
                  display: 'grid',
                  gridTemplateColumns: {
                    xs: '1fr',
                    sm: 'repeat(2, 1fr)',
                    md: 'repeat(3, 1fr)',
                    lg: 'repeat(4, 1fr)'
                  },
                  gap: 4
                }}
              >
                {cars.map((car) => (
                  <Box key={car.id}>
                    <Card
                      elevation={8}
                      sx={{
                        height: '100%',
                        display: 'flex',
                        flexDirection: 'column',
                        borderRadius: 4,
                        overflow: 'hidden',
                        transition: 'all 0.3s ease',
                        '&:hover': {
                          transform: 'translateY(-8px)',
                          boxShadow: '0 20px 40px rgba(0,0,0,0.15)',
                        }
                      }}
                    >
                      <CardMedia
                        component="img"
                        height="220"
                        image={car.coverImageUrl || 'https://images.unsplash.com/photo-1555215695-3004980ad54e?w=400'}
                        alt={`${car.brand} ${car.model}`}
                        sx={{ objectFit: 'cover' }}
                      />
                      
                      <CardContent sx={{ flexGrow: 1, p: 3 }}>
                        <Typography variant="h5" fontWeight={700} gutterBottom color="text.primary">
                          {car.brand} {car.model}
                        </Typography>
                        
                        <Box sx={{ mb: 3 }}>
                          <Chip
                            icon={getFuelTypeIcon(car.fuelType)}
                            label={car.fuelType}
                            size="small"
                            sx={{ mr: 1, mb: 1, background: '#667eea', color: 'white' }}
                          />
                          <Chip
                            icon={<CalendarToday sx={{ fontSize: 16 }} />}
                            label={`${car.year} Model`}
                            size="small"
                            sx={{ mb: 1, background: '#764ba2', color: 'white' }}
                          />
                        </Box>
                        
                        <Typography variant="body2" color="text.secondary" gutterBottom sx={{ mb: 2 }}>
                          {car.transmission} • {car.fuelType}
                        </Typography>
                        
                        <Box sx={{ mt: 3, mb: 4 }}>
                          <Typography variant="h4" fontWeight={700} color="#667eea">
                            ₺{car.pricePerDay.toLocaleString()}
                          </Typography>
                          <Typography variant="body2" color="text.secondary">
                            / gün
                          </Typography>
                        </Box>
                        
                        <Box sx={{ display: 'flex', gap: 2 }}>
                          <Button
                            variant="outlined"
                            size="large"
                            fullWidth
                            sx={{ 
                              borderRadius: 2,
                              borderColor: '#667eea',
                              color: '#667eea',
                              '&:hover': {
                                borderColor: '#5a6fd8',
                                background: 'rgba(102, 126, 234, 0.05)'
                              }
                            }}
                          >
                            Detay
                          </Button>
                          <Button
                            variant="contained"
                            size="large"
                            fullWidth
                            sx={{ 
                              borderRadius: 2,
                              background: 'linear-gradient(45deg, #667eea 30%, #764ba2 90%)',
                              '&:hover': {
                                background: 'linear-gradient(45deg, #5a6fd8 30%, #6a4190 90%)',
                              }
                            }}
                          >
                            Kirala
                          </Button>
                        </Box>
                      </CardContent>
                    </Card>
                  </Box>
                ))}
              </Box>
            ) : (
              <Box textAlign="center" py={12}>
                <DirectionsCar sx={{ fontSize: 80, color: 'text.secondary', mb: 3 }} />
                <Typography variant="h4" color="text.secondary" gutterBottom fontWeight={600}>
                  Henüz araç bulunmuyor
                </Typography>
                <Typography variant="h6" color="text.secondary" sx={{ maxWidth: 500, mx: 'auto' }}>
                  Filtrelerinizi değiştirerek daha fazla sonuç görebilirsiniz.
                </Typography>
              </Box>
            )}
          </>
        )}
      </Box>
    </Box>
  );
};


