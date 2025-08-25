import api from './api';

export interface AddressDto {
  id?: number;
  title: string;
  city: string;
  district: string;
  street: string;
  postalCode: string;
  userId?: number | string;
}

interface ApiResponse<T> { status: number; payload: T }

export const listAddresses = async () => {
  const res = await api.get<ApiResponse<any[]>>('/rest/api/address/list');
  return res.data.payload;
};

export const getAddressById = async (id: number | string) => {
  const res = await api.get<ApiResponse<any>>(`/rest/api/address/${id}`);
  return res.data.payload;
};

export const saveAddress = async (data: AddressDto) => {
  const res = await api.post<ApiResponse<any>>('/rest/api/address/save', data);
  return res.data.payload;
};

export const updateAddress = async (id: number | string, data: AddressDto) => {
  const res = await api.put<ApiResponse<any>>(`/rest/api/address/update/${id}`, data);
  return res.data.payload;
};

export const deleteAddress = async (id: number | string) => {
  const res = await api.delete<ApiResponse<any>>(`/rest/api/address/delete/${id}`);
  return res.data.payload;
};


