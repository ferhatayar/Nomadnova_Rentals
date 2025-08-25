export type UserRole = 'ADMIN' | 'CUSTOMER';

export interface AuthTokens {
  accessToken: string;
  refreshToken: string;
}

export interface AuthUserInfo {
  username: string;
  role: UserRole;
}


