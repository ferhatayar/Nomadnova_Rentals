import React from 'react';
import { Box, Typography, TextField, Button, Stack, Paper, Link, Alert } from '@mui/material';
import { useForm } from 'react-hook-form';
import { z } from 'zod';
import { zodResolver } from '@hookform/resolvers/zod';
import { useAuth } from '../../context/AuthContext';
import { useNavigate, Link as RouterLink } from 'react-router-dom';
import { DirectionsCar } from '@mui/icons-material';

export const LoginPage: React.FC = () => {
  const navigate = useNavigate();
  const { login, role } = useAuth();
  const [error, setError] = React.useState<string | null>(null);
  
  const schema = z.object({
    username: z.string().min(1, 'Kullanıcı adı gerekli'),
    password: z.string().min(1, 'Şifre gerekli'),
  });
  
  const { register, handleSubmit, formState: { errors, isSubmitting } } = useForm<{ username: string; password: string }>({ 
    resolver: zodResolver(schema) 
  });

  const onSubmit = handleSubmit(async (values) => {
    try {
      setError(null);
      await login(values.username, values.password);
      if (role === 'ADMIN') {
        navigate('/admin');
      } else {
        navigate('/');
      }
    } catch (err: any) {
      setError(err.response?.data?.message || 'Giriş yapılırken bir hata oluştu');
    }
  });

  return (
    <Box
      sx={{
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
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
        maxWidth: '450px', 
        width: '100%', 
        px: { xs: 2, sm: 3 },
        position: 'relative',
        zIndex: 1
      }}>
        <Paper
          elevation={24}
          sx={{
            p: 5,
            borderRadius: 4,
            background: 'rgba(255, 255, 255, 0.95)',
            backdropFilter: 'blur(20px)',
            border: '1px solid rgba(255, 255, 255, 0.2)',
            boxShadow: '0 25px 50px rgba(0,0,0,0.15)'
          }}
        >
          <Box textAlign="center" mb={5}>
            <DirectionsCar sx={{ fontSize: 60, color: '#667eea', mb: 3 }} />
            <Typography variant="h3" fontWeight={700} gutterBottom color="#667eea">
              NomadNova Rentals
            </Typography>
            <Typography variant="h5" color="text.secondary" fontWeight={500}>
              Hesabınıza Giriş Yapın
            </Typography>
          </Box>

          {error && (
            <Alert severity="error" sx={{ mb: 4, borderRadius: 2 }}>
              {error}
            </Alert>
          )}

          <Box component="form" onSubmit={onSubmit} noValidate>
            <Stack spacing={4}>
              <TextField
                label="Kullanıcı Adı"
                {...register('username')}
                error={!!errors.username}
                helperText={errors.username?.message}
                fullWidth
                variant="outlined"
                sx={{
                  '& .MuiOutlinedInput-root': {
                    borderRadius: 3,
                    fontSize: '1.1rem',
                    '&:hover fieldset': {
                      borderColor: '#667eea',
                    },
                    '&.Mui-focused fieldset': {
                      borderColor: '#667eea',
                    }
                  },
                  '& .MuiInputLabel-root': {
                    fontSize: '1rem'
                  }
                }}
              />
              
              <TextField
                label="Şifre"
                type="password"
                {...register('password')}
                error={!!errors.password}
                helperText={errors.password?.message}
                fullWidth
                variant="outlined"
                sx={{
                  '& .MuiOutlinedInput-root': {
                    borderRadius: 3,
                    fontSize: '1.1rem',
                    '&:hover fieldset': {
                      borderColor: '#667eea',
                    },
                    '&.Mui-focused fieldset': {
                      borderColor: '#667eea',
                    }
                  },
                  '& .MuiInputLabel-root': {
                    fontSize: '1rem'
                  }
                }}
              />
              
              <Button
                type="submit"
                variant="contained"
                disabled={isSubmitting}
                size="large"
                sx={{
                  py: 2,
                  borderRadius: 3,
                  fontSize: '1.2rem',
                  fontWeight: 600,
                  textTransform: 'none',
                  background: 'linear-gradient(45deg, #667eea 30%, #764ba2 90%)',
                  boxShadow: '0 8px 25px rgba(102, 126, 234, 0.3)',
                  '&:hover': {
                    background: 'linear-gradient(45deg, #5a6fd8 30%, #6a4190 90%)',
                    boxShadow: '0 12px 35px rgba(102, 126, 234, 0.4)',
                    transform: 'translateY(-2px)'
                  },
                  transition: 'all 0.3s ease'
                }}
              >
                {isSubmitting ? 'Giriş Yapılıyor...' : 'Giriş Yap'}
              </Button>
            </Stack>
          </Box>

          <Box textAlign="center" mt={5}>
            <Typography variant="body1" color="text.secondary">
              Hesabınız yok mu?{' '}
              <Link
                component={RouterLink}
                to="/register"
                sx={{
                  color: '#667eea',
                  textDecoration: 'none',
                  fontWeight: 600,
                  fontSize: '1.1rem',
                  '&:hover': {
                    textDecoration: 'underline',
                    color: '#5a6fd8'
                  }
                }}
              >
                Kayıt Olun
              </Link>
            </Typography>
          </Box>
        </Paper>
      </Box>
    </Box>
  );
};


