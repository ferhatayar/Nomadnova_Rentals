import api from './api';
import type { AuthTokens } from '../types/auth';

export interface RegisterRequest {
  username: string;
  password: string;
  email: string;
  phone: string;
  createdAt: string;
  role: 'USER' | 'ADMIN';
}

export interface AuthResponse {
  status: number;
  payload: any;
}

export const register = async (data: RegisterRequest) => {
  const res = await api.post<AuthResponse>('/register', data);
  return res.data;
};

export const authenticate = async (data: { username: string; password: string }) => {
  const res = await api.post<AuthResponse>('/authenticate', data);
  return res.data;
};

export const refreshToken = async (refreshTokenValue: string) => {
  const res = await api.post<AuthResponse>('/refreshToken', { refreshToken: refreshTokenValue });
  return res.data;
};

export const saveTokens = (tokens: AuthTokens) => {
  localStorage.setItem('accessToken', tokens.accessToken);
  localStorage.setItem('refreshToken', tokens.refreshToken);
};

export const getAccessToken = () => localStorage.getItem('accessToken');
export const getRefreshToken = () => localStorage.getItem('refreshToken');
export const clearTokens = () => {
  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
};


