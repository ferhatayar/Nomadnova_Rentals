import React from 'react';
import type { PropsWithChildren } from 'react';
import { AppBar, Box, Container, Toolbar, Typography, Button, Avatar, Menu, MenuItem, Tooltip, IconButton } from '@mui/material';
import DirectionsCarFilledIcon from '@mui/icons-material/DirectionsCarFilled';
import { useAuth } from '../../context/AuthContext';
import { useNavigate } from 'react-router-dom';

export const AppLayout: React.FC<PropsWithChildren> = ({ children }) => {
  const { isAuthenticated, username, role, logout } = useAuth();
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const navigate = useNavigate();

  const open = Boolean(anchorEl);
  const handleOpen = (event: React.MouseEvent<HTMLButtonElement | HTMLDivElement>) => setAnchorEl(event.currentTarget as HTMLElement);
  const handleClose = () => setAnchorEl(null);

  const initial = (username?.[0] || 'U').toUpperCase();

  return (
    <Box sx={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      {/* Fixed Navbar */}
      <AppBar 
        position="fixed" 
        sx={{
          background: 'white',
          boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
          zIndex: 1200
        }}
      >
        <Toolbar sx={{ 
          gap: 2, 
          px: { xs: 2, sm: 3, md: 4 },
          minHeight: '70px'
        }}>
          <DirectionsCarFilledIcon sx={{ fontSize: 32, color: '#667eea' }} />
          <Typography 
            variant="h5" 
            fontWeight={700} 
            sx={{ 
              flexGrow: 1,
              cursor: 'pointer',
              color: '#667eea',
              '&:hover': { opacity: 0.8 }
            }}
            onClick={() => navigate('/')}
          >
            NomadNova Rentals
          </Typography>

          {!isAuthenticated ? (
            <Button 
              variant="contained"
              onClick={() => navigate('/login')}
              sx={{
                borderRadius: 2,
                px: 3,
                py: 1.5,
                fontWeight: 600,
                textTransform: 'none',
                fontSize: '1rem',
                background: 'linear-gradient(45deg, #667eea 30%, #764ba2 90%)',
                '&:hover': {
                  background: 'linear-gradient(45deg, #5a6fd8 30%, #6a4190 90%)',
                }
              }}
            >
              Giriş Yap
            </Button>
          ) : (
            <>
              <Tooltip title={username}>
                <IconButton 
                  onClick={handleOpen} 
                  sx={{
                    '&:hover': {
                      background: 'rgba(102, 126, 234, 0.1)'
                    }
                  }}
                >
                  <Avatar 
                    sx={{ 
                      bgcolor: '#667eea',
                      color: 'white',
                      fontWeight: 600,
                      fontSize: '1.1rem'
                    }}
                  >
                    {initial}
                  </Avatar>
                </IconButton>
              </Tooltip>
              <Menu 
                anchorEl={anchorEl} 
                open={open} 
                onClose={handleClose} 
                anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }} 
                transformOrigin={{ vertical: 'top', horizontal: 'right' }}
                PaperProps={{
                  sx: {
                    borderRadius: 2,
                    mt: 1,
                    minWidth: 180,
                    boxShadow: '0 8px 32px rgba(0,0,0,0.1)'
                  }
                }}
              >
                <MenuItem 
                  onClick={() => { handleClose(); navigate('/profile'); }}
                  sx={{ py: 1.5, px: 3 }}
                >
                  Profilim
                </MenuItem>
                {role === 'ADMIN' && (
                  <MenuItem 
                    onClick={() => { handleClose(); navigate('/admin'); }}
                    sx={{ py: 1.5, px: 3 }}
                  >
                    Admin Paneli
                  </MenuItem>
                )}
                <MenuItem 
                  onClick={() => { handleClose(); logout(); navigate('/'); }}
                  sx={{ py: 1.5, px: 3 }}
                >
                  Çıkış Yap
                </MenuItem>
              </Menu>
            </>
          )}
        </Toolbar>
      </AppBar>
      
      {/* Main Content with top margin for fixed navbar */}
      <Box sx={{ 
        flex: 1, 
        width: '100%',
        mt: '70px' // Height of navbar
      }}>
        {children}
      </Box>
      
      {/* Fixed Footer */}
      <Box 
        component="footer" 
        sx={{
          background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
          color: 'white',
          py: 4,
          width: '100%',
          position: 'relative',
          bottom: 0
        }}
      >
        <Container maxWidth="lg">
          <Box sx={{ 
            display: 'flex', 
            justifyContent: 'space-between', 
            alignItems: 'center',
            flexWrap: 'wrap',
            gap: 2
          }}>
            <Box>
              <Typography variant="h6" fontWeight={600} gutterBottom>
                NomadNova Rentals
              </Typography>
              <Typography variant="body2" sx={{ opacity: 0.9 }}>
                Güvenli ve uygun fiyatlı araç kiralama hizmeti
              </Typography>
            </Box>
            
            <Box sx={{ textAlign: 'right' }}>
              <Typography variant="body2" sx={{ opacity: 0.9 }}>
                © {new Date().getFullYear()} NomadNova Rentals. Tüm hakları saklıdır.
              </Typography>
            </Box>
          </Box>
        </Container>
      </Box>
    </Box>
  );
};


