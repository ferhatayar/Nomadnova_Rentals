import api from './api';
import type { Car } from '../types/car';

export interface CarFilters {
  brand?: string;
  model?: string;
  minPrice?: number;
  maxPrice?: number;
  fuelType?: string;
}

export const listCars = async (filters?: CarFilters): Promise<Car[]> => {
  const params = new URLSearchParams();
  if (filters?.brand) params.append('brand', filters.brand);
  if (filters?.model) params.append('model', filters.model);
  if (filters?.minPrice) params.append('minPrice', filters.minPrice.toString());
  if (filters?.maxPrice) params.append('maxPrice', filters.maxPrice.toString());
  if (filters?.fuelType) params.append('fuelType', filters.fuelType);

  const response = await api.get(`/rest/api/car/list${params.toString() ? `?${params.toString()}` : ''}`);
  
  // Backend response formatına göre payload'ı kontrol et
  if (response.data && typeof response.data === 'object' && 'payload' in response.data) {
    return response.data.payload || [];
  }
  
  return response.data || [];
};

export const getCarById = async (id: string): Promise<Car> => {
  const response = await api.get(`/rest/api/car/${id}`);
  
  // Backend response formatına göre payload'ı kontrol et
  if (response.data && typeof response.data === 'object' && 'payload' in response.data) {
    return response.data.payload;
  }
  
  return response.data;
};

export const saveCar = async (car: Omit<Car, 'id' | 'createdAt'>): Promise<Car> => {
  const response = await api.post('/rest/api/car', car);
  
  // Backend response formatına göre payload'ı kontrol et
  if (response.data && typeof response.data === 'object' && 'payload' in response.data) {
    return response.data.payload;
  }
  
  return response.data;
};

export const updateCar = async (id: string, car: Partial<Car>): Promise<Car> => {
  const response = await api.put(`/rest/api/car/${id}`, car);
  
  // Backend response formatına göre payload'ı kontrol et
  if (response.data && typeof response.data === 'object' && 'payload' in response.data) {
    return response.data.payload;
  }
  
  return response.data;
};

export const deleteCar = async (id: string): Promise<void> => {
  await api.delete(`/rest/api/car/${id}`);
};


