import React, { PropsWithChildren } from 'react';
import { createTheme, CssBaseline, ThemeProvider, responsiveFontSizes } from '@mui/material';

let baseTheme = createTheme({
  palette: {
    mode: 'light',
    primary: {
      main: '#2B6CB0',
    },
    secondary: {
      main: '#38B2AC',
    },
    background: {
      default: '#f7f9fc',
    },
  },
  shape: {
    borderRadius: 10,
  },
  components: {
    MuiButton: {
      defaultProps: {
        variant: 'contained',
      },
    },
  },
});

baseTheme = responsiveFontSizes(baseTheme);

export const AppThemeProvider: React.FC<PropsWithChildren> = ({ children }) => {
  return (
    <ThemeProvider theme={baseTheme}>
      <CssBaseline />
      {children}
    </ThemeProvider>
  );
};


