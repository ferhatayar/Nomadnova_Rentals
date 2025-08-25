import React from 'react';
import { Container, Box, Typography } from '@mui/material';

export const AdminPage: React.FC = () => {
  return (
    <Container maxWidth="lg">
      <Box py={6}>
        <Typography variant="h5" fontWeight={600} gutterBottom>
          Admin Paneli
        </Typography>
        <Typography color="text.secondary">Araç ekleme/güncelleme/silme ekranları burada.</Typography>
      </Box>
    </Container>
  );
};


