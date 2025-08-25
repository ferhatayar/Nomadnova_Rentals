import api from './api';

interface ApiResponse<T> { status: number; payload: T }

export interface SaveRentalDto {
  carId: number | string;
  userId: number | string;
  startDate: string; // YYYY-MM-DD
  endDate: string;   // YYYY-MM-DD
  totalPrice: number | string;
  status: 'PENDING' | 'CONFIRMED' | 'CANCELLED';
  paymentId?: number | string;
}

export const saveRental = async (dto: SaveRentalDto) => {
  const res = await api.post<ApiResponse<any>>('/rest/api/rental/save', dto);
  return res.data.payload;
};

export const listRentals = async () => {
  const res = await api.get<ApiResponse<any[]>>('/rest/api/rental/list');
  return res.data.payload;
};

export const getRentalById = async (id: number | string) => {
  const res = await api.get<ApiResponse<any>>(`/rest/api/rental/${id}`);
  return res.data.payload;
};

export const updateRental = async (id: number | string, body: Partial<SaveRentalDto>) => {
  const res = await api.put<ApiResponse<any>>(`/rest/api/rental/update/${id}`, body);
  return res.data.payload;
};

export const deleteRental = async (id: number | string) => {
  // Not: backend dokümanında Delete rental: /api/user/rental/id verilmiş
  const res = await api.delete<ApiResponse<any>>(`/api/user/rental/${id}`);
  return res.data.payload;
};


