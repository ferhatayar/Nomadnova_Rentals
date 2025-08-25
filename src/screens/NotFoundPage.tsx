import React from 'react';
import { Container, Box, Typography } from '@mui/material';

export const NotFoundPage: React.FC = () => {
  return (
    <Container maxWidth="sm">
      <Box py={10} textAlign="center">
        <Typography variant="h3" fontWeight={700} gutterBottom>
          404
        </Typography>
        <Typography color="text.secondary">Aradığınız sayfa bulunamadı.</Typography>
      </Box>
    </Container>
  );
};


