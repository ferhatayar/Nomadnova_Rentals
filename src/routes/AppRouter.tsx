import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

import { HomePage } from '../screens/HomePage';
import { CarDetailPage } from '../screens/CarDetailPage';
import { LoginPage } from '../screens/auth/LoginPage';
import { RegisterPage } from '../screens/auth/RegisterPage';
import { ProfilePage } from '../screens/ProfilePage';
import { AdminPage } from '../screens/admin/AdminPage';
import { RequireAdmin, RequireAuth } from './guards';
import { NotFoundPage } from '../screens/NotFoundPage';
import { AddressesPage } from '../screens/profile/AddressesPage';

export const AppRouter: React.FC = () => {
  return (
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/cars/:id" element={<CarDetailPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />

          <Route element={<RequireAuth />}>
            <Route path="/profile" element={<ProfilePage />} />
            <Route path="/profile/addresses" element={<AddressesPage />} />
          </Route>

          <Route element={<RequireAdmin />}>
            <Route path="/admin" element={<AdminPage />} />
          </Route>

          <Route path="/404" element={<NotFoundPage />} />
          <Route path="*" element={<Navigate to="/404" replace />} />
        </Routes>
  );
};


