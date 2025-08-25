import React from 'react';
import { Container, Box, Typography } from '@mui/material';

export const ProfilePage: React.FC = () => {
  return (
    <Container maxWidth="md">
      <Box py={6}>
        <Typography variant="h5" fontWeight={600} gutterBottom>
          Profilim
        </Typography>
        <Typography color="text.secondary">Profil ve adres yönetimi burada.</Typography>
      </Box>
    </Container>
  );
};


