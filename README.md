# NomadNova Rentals - Frontend

Modern araç kiralama ve satış platformu için React tabanlı frontend uygulaması.

## 🚀 Teknolojiler

- **React 18** - Modern UI framework
- **TypeScript** - Type safety
- **Vite** - Fast build tool
- **Material-UI (MUI)** - UI component library
- **React Router** - Client-side routing
- **React Hook Form** - Form management
- **Axios** - HTTP client
- **Zod** - Schema validation

## 🏗️ Proje Yapısı

```
src/
├── components/     # Reusable UI components
│   ├── cars/      # Car-related components
│   └── layout/    # Layout components
├── context/       # React context providers
├── routes/        # Routing configuration
├── screens/       # Page components
│   ├── auth/      # Authentication pages
│   ├── admin/     # Admin pages
│   └── profile/   # Profile pages
├── services/      # API services
├── theme/         # MUI theme configuration
└── types/         # TypeScript type definitions
```

## ✨ Özellikler

### 🔐 Kimlik Doğrulama
- JWT tabanlı authentication
- Role-based access control (ADMIN/CUSTOMER)
- Protected routes
- Auto token refresh

### 🚗 Araç Yönetimi
- Araç listesi görüntüleme
- Detaylı araç bilgileri
- Filtreleme ve arama
- Responsive card layout

### 📱 Modern UI/UX
- Full-screen responsive design
- Modern gradient themes
- Smooth animations
- Glassmorphism effects
- Professional styling

### 🔄 State Management
- React Context for auth state
- Local storage for tokens
- Optimistic updates

## 🛠️ Kurulum

### Gereksinimler
- Node.js 18+
- npm veya yarn

### Kurulum Adımları

1. **Repository'yi klonlayın:**
```bash
git clone https://github.com/ferhatayar/Nomadnova_Rentals.git
cd Nomadnova_Rentals
git checkout frontend
```

2. **Bağımlılıkları yükleyin:**
```bash
npm install
```

3. **Environment dosyasını oluşturun:**
```bash
cp .env.example .env.local
```

4. **Backend URL'ini ayarlayın:**
```env
VITE_API_BASE_URL=http://localhost:8080
```

5. **Uygulamayı çalıştırın:**
```bash
npm run dev
```

Uygulama `http://localhost:5173` adresinde çalışacaktır.

## 📦 Build

Production build için:

```bash
npm run build
```

Build dosyaları `dist/` klasöründe oluşturulacaktır.

## 🔧 Konfigürasyon

### Environment Variables

`.env.local` dosyasında aşağıdaki değişkenleri ayarlayabilirsiniz:

```env
VITE_API_BASE_URL=http://localhost:8080
```

### Theme Customization

`src/theme/theme.tsx` dosyasında MUI theme'ini özelleştirebilirsiniz.

## 🧪 Test

```bash
npm run test
```

## 📱 Responsive Design

Uygulama tüm cihazlarda optimize edilmiştir:
- **Mobile**: 320px - 768px
- **Tablet**: 768px - 1024px
- **Desktop**: 1024px+

## 🔐 Güvenlik

- JWT token'ları localStorage'da güvenli şekilde saklanır
- Auto token refresh ile kesintisiz deneyim
- Protected routes ile yetkisiz erişim engellenir
- CORS policy backend ile uyumlu

## 🚀 Deployment

### Vercel
```bash
npm install -g vercel
vercel
```

### Netlify
```bash
npm run build
# dist/ klasörünü Netlify'a deploy edin
```

## 👥 Geliştirici

**Ferhat Ayar** - [GitHub](https://github.com/ferhatayar)

## 📄 Lisans

Bu proje MIT lisansı altında lisanslanmıştır.

---

**Not:** Bu frontend uygulaması, NomadNova Rentals backend API'si ile entegre çalışmak üzere tasarlanmıştır.
