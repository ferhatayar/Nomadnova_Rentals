import api from './api';

interface ApiResponse<T> { status: number; payload: T }

export interface SavePaymentDto {
  amount: number | string;
  paymentMethod: 'CARD' | 'BANK_TRANSFER';
  paymentDate: string; // YYYY-MM-DD
  status: 'SUCCESS' | 'FAILED';
}

export const savePayment = async (dto: SavePaymentDto) => {
  const res = await api.post<ApiResponse<any>>('/rest/api/payment/save', dto);
  return res.data.payload;
};

export const listPayments = async () => {
  const res = await api.get<ApiResponse<any[]>>('/rest/api/payment/list');
  return res.data.payload;
};

export const getPaymentById = async (id: number | string) => {
  const res = await api.get<ApiResponse<any>>(`/rest/api/payment/${id}`);
  return res.data.payload;
};

export const updatePayment = async (id: number | string, body: Partial<SavePaymentDto>) => {
  const res = await api.put<ApiResponse<any>>(`/rest/api/payment/update/${id}`, body);
  return res.data.payload;
};

export const deletePayment = async (id: number | string) => {
  // Not: backend dokümanında Delete payment: /rest/api/user/payment/id
  const res = await api.delete<ApiResponse<any>>(`/rest/api/user/payment/${id}`);
  return res.data.payload;
};


