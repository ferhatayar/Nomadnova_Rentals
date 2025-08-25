import React from 'react';
import './App.css';
import { AppThemeProvider } from './theme/theme';
import { AppRouter } from './routes/AppRouter';
import { AppLayout } from './components/layout/AppLayout';
import { AuthProvider } from './context/AuthContext';
import { BrowserRouter } from 'react-router-dom';
import { SnackbarProvider } from 'notistack';

function App() {
  return (
    <AppThemeProvider>
      <AuthProvider>
        <BrowserRouter>
          <SnackbarProvider maxSnack={3} autoHideDuration={2500} anchorOrigin={{ vertical: 'top', horizontal: 'right' }}>
            <AppLayout>
              <AppRouter />
            </AppLayout>
          </SnackbarProvider>
        </BrowserRouter>
      </AuthProvider>
    </AppThemeProvider>
  );
}

export default App;
