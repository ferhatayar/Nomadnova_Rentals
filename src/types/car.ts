export type FuelType = 'BENZİN' | 'DİZEL' | 'ELEKTRİK' | 'HİBRİT';
export type Transmission = 'MANUEL' | 'OTOMATİK';
export type CarStatus = 'AVAILABLE' | 'RENTED' | 'SOLD';

export interface CarOwner {
  id: string;
  username: string;
  email: string;
  phone: string;
  role: 'USER' | 'ADMIN' | 'CUSTOMER';
  createdAt: string;
}

export interface Car {
  id: string;
  owner?: CarOwner;
  ownerId?: string;
  brand: string;
  model: string;
  year: number;
  fuelType: FuelType;
  transmission: Transmission;
  pricePerDay: number;
  priceForSale: number;
  status: CarStatus;
  createdAt: string;
  carImages?: { id: string; imageUrl: string }[] | null;
  coverImageUrl?: string;
}


