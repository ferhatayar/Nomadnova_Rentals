import api from './api';

interface ApiResponse<T> { status: number; payload: T }

export interface SavePurchaseDto {
  carId: number | string;
  userId: number | string;
  purchaseDate: string; // YYYY-MM-DD
  price: number | string;
  paymentId?: number | string;
}

export const savePurchase = async (dto: SavePurchaseDto) => {
  const res = await api.post<ApiResponse<any>>('/rest/api/purchase/save', dto);
  return res.data.payload;
};

export const listPurchases = async () => {
  const res = await api.get<ApiResponse<any[]>>('/rest/api/purchase/list');
  return res.data.payload;
};

export const getPurchaseById = async (id: number | string) => {
  const res = await api.get<ApiResponse<any>>(`/rest/api/purchase/${id}`);
  return res.data.payload;
};

export const updatePurchase = async (id: number | string, body: Partial<SavePurchaseDto>) => {
  const res = await api.put<ApiResponse<any>>(`/rest/api/purchase/update/${id}`, body);
  return res.data.payload;
};

export const deletePurchase = async (id: number | string) => {
  const res = await api.delete<ApiResponse<any>>(`/rest/api/purchase/${id}`);
  return res.data.payload;
};


