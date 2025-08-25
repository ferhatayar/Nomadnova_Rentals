import React, { useEffect, useMemo, useState } from 'react';
import { Box, Button, Container, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, Stack, TextField, Typography } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { DataGrid } from '@mui/x-data-grid';
import type { GridColDef } from '@mui/x-data-grid';
import { listAddresses, saveAddress, updateAddress, deleteAddress } from '../../services/addressService';
import { useAuth } from '../../context/AuthContext';

interface AddressFormState {
  id?: number;
  title: string;
  city: string;
  district: string;
  street: string;
  postalCode: string;
}

export const AddressesPage: React.FC = () => {
  const { username } = useAuth();
  const [rows, setRows] = useState<any[]>([]);
  const [open, setOpen] = useState(false);
  const [form, setForm] = useState<AddressFormState>({ title: '', city: '', district: '', street: '', postalCode: '' });
  const [loading, setLoading] = useState(false);

  const load = async () => {
    setLoading(true);
    try {
      const data = await listAddresses();
      setRows(Array.isArray(data) ? data : []);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load(); }, []);

  const columns = useMemo<GridColDef[]>(() => [
    { field: 'id', headerName: 'ID', width: 80 },
    { field: 'title', headerName: 'Başlık', flex: 1 },
    { field: 'city', headerName: 'Şehir', flex: 1 },
    { field: 'district', headerName: 'İlçe', flex: 1 },
    { field: 'street', headerName: 'Sokak', flex: 1 },
    { field: 'postalCode', headerName: 'Posta Kodu', width: 130 },
    {
      field: 'actions', headerName: 'İşlemler', sortable: false, width: 130, renderCell: (params) => (
        <Stack direction="row" spacing={1}>
          <IconButton size="small" onClick={() => handleEdit(params.row)}><EditIcon fontSize="small" /></IconButton>
          <IconButton size="small" color="error" onClick={() => handleDelete(params.row.id)}><DeleteIcon fontSize="small" /></IconButton>
        </Stack>
      )
    },
  ], []);

  const handleEdit = (row: any) => {
    setForm({ id: row.id, title: row.title || '', city: row.city || '', district: row.district || '', street: row.street || '', postalCode: row.postalCode || '' });
    setOpen(true);
  };

  const handleDelete = async (id: number) => {
    await deleteAddress(id);
    load();
  };

  const handleCreate = () => {
    setForm({ title: '', city: '', district: '', street: '', postalCode: '' });
    setOpen(true);
  };

  const handleSubmit = async () => {
    if (form.id) {
      await updateAddress(form.id, { ...form });
    } else {
      await saveAddress({ ...form });
    }
    setOpen(false);
    load();
  };

  return (
    <Container maxWidth="lg">
      <Box py={4}>
        <Stack direction="row" justifyContent="space-between" alignItems="center" mb={2}>
          <Box>
            <Typography variant="h5" fontWeight={800}>Adreslerim</Typography>
            <Typography color="text.secondary">{username} kullanıcısının adres yönetimi</Typography>
          </Box>
          <Button startIcon={<AddIcon />} variant="contained" onClick={handleCreate}>Yeni Adres</Button>
        </Stack>

        <Box sx={{ height: 520, width: '100%', bgcolor: 'background.paper', borderRadius: 2 }}>
          <DataGrid rows={rows} columns={columns} loading={loading} disableRowSelectionOnClick pageSizeOptions={[10]} initialState={{ pagination: { paginationModel: { pageSize: 10, page: 0 } } }} />
        </Box>

        <Dialog open={open} onClose={() => setOpen(false)} fullWidth maxWidth="sm">
          <DialogTitle>{form.id ? 'Adres Düzenle' : 'Yeni Adres'}</DialogTitle>
          <DialogContent>
            <Stack spacing={2} mt={1}>
              <TextField label="Başlık" value={form.title} onChange={(e) => setForm({ ...form, title: e.target.value })} fullWidth />
              <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                <TextField label="Şehir" value={form.city} onChange={(e) => setForm({ ...form, city: e.target.value })} fullWidth />
                <TextField label="İlçe" value={form.district} onChange={(e) => setForm({ ...form, district: e.target.value })} fullWidth />
              </Stack>
              <TextField label="Sokak" value={form.street} onChange={(e) => setForm({ ...form, street: e.target.value })} fullWidth />
              <TextField label="Posta Kodu" value={form.postalCode} onChange={(e) => setForm({ ...form, postalCode: e.target.value })} fullWidth />
            </Stack>
          </DialogContent>
          <DialogActions>
            <Button onClick={() => setOpen(false)}>İptal</Button>
            <Button variant="contained" onClick={handleSubmit}>{form.id ? 'Güncelle' : 'Kaydet'}</Button>
          </DialogActions>
        </Dialog>
      </Box>
    </Container>
  );
};


