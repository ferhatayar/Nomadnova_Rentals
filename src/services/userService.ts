import api from './api';

interface ApiResponse<T> { status: number; payload: T }

export interface UserDto {
  id: number;
  username: string;
  email: string;
  phone: string;
  role: 'USER' | 'ADMIN' | 'CUSTOMER';
  createdAt: string;
}

export const listUsers = async () => {
  const res = await api.get<ApiResponse<UserDto[]>>('/rest/api/user/list');
  return res.data.payload;
};

export const getUserById = async (id: number | string) => {
  const res = await api.get<ApiResponse<UserDto>>(`/rest/api/user/${id}`);
  return res.data.payload;
};

export const getUserByUsername = async (username: string) => {
  const res = await api.get<ApiResponse<UserDto>>(`/rest/api/user/username/${encodeURIComponent(username)}`);
  return res.data.payload;
};


