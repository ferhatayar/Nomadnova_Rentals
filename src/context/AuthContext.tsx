import React, { createContext, useCallback, useContext, useEffect, useMemo, useState } from 'react';
import { authenticate, refreshToken as refreshTokenApi, getAccessToken, getRefreshToken, saveTokens, clearTokens } from '../services/authService';
import type { AuthTokens, UserRole } from '../types/auth';
import { getUserByUsername } from '../services/userService';

interface AuthState {
  isAuthenticated: boolean;
  username?: string;
  role?: UserRole;
  tokens?: AuthTokens;
  userId?: number;
}

interface AuthContextValue extends AuthState {
  login: (username: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export const AuthProvider: React.FC<React.PropsWithChildren> = ({ children }) => {
  const [state, setState] = useState<AuthState>({ isAuthenticated: false });

  useEffect(() => {
    const access = getAccessToken();
    const refresh = getRefreshToken();
    if (access && refresh) {
      const payload = parseJwt(access);
      const baseState = { isAuthenticated: true, username: payload?.sub, role: payload?.role, userId: payload?.id || payload?.userId, tokens: { accessToken: access, refreshToken: refresh } } as AuthState;
      setState(baseState);
      // Eğer userId yoksa username ile çek
      if (!baseState.userId && baseState.username) {
        getUserByUsername(baseState.username).then((u) => setState((prev) => ({ ...prev, userId: u.id }))).catch(() => void 0);
      }
    }
  }, []);

  // Logout event'ini dinle
  useEffect(() => {
    const handleLogout = () => {
      setState({ isAuthenticated: false });
    };

    window.addEventListener('auth:logout', handleLogout);
    return () => {
      window.removeEventListener('auth:logout', handleLogout);
    };
  }, []);

  const setAuthFromAccess = useCallback((accessToken: string) => {
    const payload = parseJwt(accessToken);
    setState((prev) => ({ ...prev, isAuthenticated: true, username: payload?.sub, role: payload?.role, userId: payload?.id || payload?.userId, tokens: { accessToken, refreshToken: getRefreshToken() || '' } }));
    if (!(payload?.id || payload?.userId) && payload?.sub) {
      getUserByUsername(payload.sub).then((u) => setState((prev) => ({ ...prev, userId: u.id }))).catch(() => void 0);
    }
  }, []);

  const login = useCallback(async (username: string, password: string) => {
    const res = await authenticate({ username, password });
    const tokens = res.payload as AuthTokens;
    saveTokens(tokens);
    setAuthFromAccess(tokens.accessToken);
  }, [setAuthFromAccess]);

  const logout = useCallback(() => {
    clearTokens();
    setState({ isAuthenticated: false });
  }, []);

  const value = useMemo<AuthContextValue>(() => ({
    ...state,
    login,
    logout,
  }), [state, login, logout]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be used within AuthProvider');
  return ctx;
};

function parseJwt(token?: string): any | undefined {
  if (!token) return undefined;
  try {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map(function (c) {
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join('')
    );
    return JSON.parse(jsonPayload);
  } catch (e) {
    return undefined;
  }
}


