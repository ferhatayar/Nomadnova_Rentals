import React from 'react';
import { Box, MenuItem, TextField, Button } from '@mui/material';
import { useForm } from 'react-hook-form';

export interface FiltersForm {
  brand?: string;
  model?: string;
  minPrice?: number;
  maxPrice?: number;
  fuelType?: 'BENZİN' | 'DİZEL' | 'ELEKTRİK' | 'HİBRİT' | '';
}

interface Props {
  initial?: FiltersForm;
  onApply: (values: FiltersForm) => void;
}

export const CarFilters: React.FC<Props> = ({ initial, onApply }) => {
  const { register, handleSubmit, reset } = useForm<FiltersForm>({ defaultValues: initial });

  return (
    <Box component="form" onSubmit={handleSubmit(onApply)} mb={3}
      sx={{
        display: 'grid',
        gridTemplateColumns: { xs: '1fr', sm: 'repeat(2, 1fr)', md: 'repeat(6, 1fr)' },
        gap: 2,
      }}
    >
      <TextField fullWidth label="Marka" {...register('brand')} sx={{ gridColumn: { xs: 'auto', md: 'span 1' } }} />
      <TextField fullWidth label="Model" {...register('model')} sx={{ gridColumn: { xs: 'auto', md: 'span 1' } }} />
      <TextField fullWidth label="Min Fiyat" type="number" {...register('minPrice', { valueAsNumber: true })} sx={{ gridColumn: { xs: 'auto', md: 'span 1' } }} />
      <TextField fullWidth label="Max Fiyat" type="number" {...register('maxPrice', { valueAsNumber: true })} sx={{ gridColumn: { xs: 'auto', md: 'span 1' } }} />
      <TextField fullWidth label="Yakıt" select defaultValue="" {...register('fuelType')} sx={{ gridColumn: { xs: 'auto', md: 'span 1' } }}>
        <MenuItem value="">Tümü</MenuItem>
        <MenuItem value="BENZİN">Benzin</MenuItem>
        <MenuItem value="DİZEL">Dizel</MenuItem>
        <MenuItem value="HİBRİT">Hibrit</MenuItem>
        <MenuItem value="ELEKTRİK">Elektrik</MenuItem>
      </TextField>
      <Box sx={{ display: 'flex', gap: 1, justifyContent: 'flex-end', gridColumn: { xs: 'auto', md: 'span 2' } }}>
        <Button type="submit" variant="contained">Filtrele</Button>
        <Button variant="outlined" onClick={() => reset({})}>Temizle</Button>
      </Box>
    </Box>
  );
};


