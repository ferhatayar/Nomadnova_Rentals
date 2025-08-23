# Nomadnova Rentals - Araç Kiralama ve Satış Platformu

## 📋 Proje Hakkında

Nomadnova Rentals, araç kiralama ve satış işlemlerini yönetmek için geliştirilmiş modern bir Spring Boot uygulamasıdır. Bu platform, kullanıcıların araç kiralayabilmesi, satın alabilmesi ve tüm işlemlerini güvenli bir şekilde gerçekleştirebilmesi için tasarlanmıştır.

## 🚀 Teknolojiler

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Security** - Güvenlik ve kimlik doğrulama
- **Spring Data JPA** - Veritabanı işlemleri
- **PostgreSQL** - Ana veritabanı
- **JWT (JSON Web Token)** - Token tabanlı kimlik doğrulama
- **Lombok** - Kod tekrarını azaltma
- **Maven** - Bağımlılık yönetimi

## 🏗️ Proje Yapısı

```
src/main/java/com/ferhatayar/
├── config/          # Konfigürasyon sınıfları
├── controller/      # REST API kontrolcüleri
├── dto/            # Data Transfer Objects
├── enums/          # Enumeration sınıfları
├── exception/      # Özel exception sınıfları
├── handler/        # Global exception handler
├── jwt/            # JWT implementasyonu
├── model/          # Entity sınıfları
├── repository/     # Repository interfaces
├── service/        # Business logic servisleri
└── starter/        # Ana uygulama sınıfı
```

## 🔐 Özellikler

### Kimlik Doğrulama ve Güvenlik
- JWT tabanlı kimlik doğrulama
- Role-based access control (RBAC)
- Güvenli endpoint'ler
- Refresh token desteği

### Araç Yönetimi
- Araç ekleme, düzenleme, silme
- Araç durumu takibi (müsait, kiralandı, satıldı)
- Araç görselleri yönetimi
- Yakıt tipi ve vites bilgileri

### Kiralama Sistemi
- Araç kiralama işlemleri
- Kiralama durumu takibi
- Tarih bazlı kiralama

### Satış Sistemi
- Araç satış işlemleri
- Satış geçmişi
- Ödeme entegrasyonu

### Kullanıcı Yönetimi
- Kullanıcı kayıt ve giriş
- Adres bilgileri yönetimi
- Profil yönetimi

### Ödeme Sistemi
- Çoklu ödeme yöntemi desteği
- Ödeme durumu takibi
- Güvenli ödeme işlemleri

## 🗄️ Veritabanı Şeması

Proje PostgreSQL veritabanı kullanır ve aşağıdaki ana tabloları içerir:

- **users** - Kullanıcı bilgileri
- **cars** - Araç bilgileri
- **rentals** - Kiralama kayıtları
- **purchases** - Satış kayıtları
- **payments** - Ödeme bilgileri
- **addresses** - Adres bilgileri
- **car_images** - Araç görselleri
- **refresh_tokens** - Yenileme token'ları

## ⚙️ Kurulum ve Çalıştırma

### Gereksinimler
- Java 17 veya üzeri
- Maven 3.6+
- PostgreSQL 12+

### Veritabanı Kurulumu
1. PostgreSQL'i kurun ve çalıştırın
2. `nomadnova` adında bir şema oluşturun
3. `application.properties` dosyasındaki veritabanı bilgilerini güncelleyin

### Uygulama Kurulumu
1. Projeyi klonlayın:
```bash
git clone https://github.com/ferhatayar/Nomadnova_Rentals.git
cd Nomadnova_Rentals
```

2. Bağımlılıkları yükleyin:
```bash
mvn clean install
```

3. Uygulamayı çalıştırın:
```bash
mvn spring-boot:run
```

Uygulama varsayılan olarak `http://localhost:8080` adresinde çalışacaktır.

## 🔧 Konfigürasyon

`application.properties` dosyasında aşağıdaki ayarları yapabilirsiniz:

```properties
# Veritabanı ayarları
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA ayarları
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 📚 API Dokümantasyonu

### Ana Endpoint'ler

- **POST** `/api/auth/login` - Kullanıcı girişi
- **POST** `/api/auth/register` - Kullanıcı kaydı
- **GET** `/api/cars` - Araç listesi
- **POST** `/api/cars` - Yeni araç ekleme
- **GET** `/api/rentals` - Kiralama listesi
- **POST** `/api/rentals` - Yeni kiralama
- **GET** `/api/purchases` - Satış listesi
- **POST** `/api/purchases` - Yeni satış

## 🧪 Test

Testleri çalıştırmak için:

```bash
mvn test
```

## 📝 Lisans

Bu proje MIT lisansı altında lisanslanmıştır.

## 👥 Geliştirici

- **Ferhat Ayar** - [GitHub](https://github.com/ferhatayar)

## 🤝 Katkıda Bulunma

1. Bu repository'yi fork edin
2. Feature branch oluşturun (`git checkout -b feature/AmazingFeature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some AmazingFeature'`)
4. Branch'inizi push edin (`git push origin feature/AmazingFeature`)
5. Pull Request oluşturun

## 📞 İletişim

Proje hakkında sorularınız için:
- GitHub Issues: [Nomadnova_Rentals Issues](https://github.com/ferhatayar/Nomadnova_Rentals/issues)

---

**Not:** Bu proje geliştirme aşamasındadır ve production ortamında kullanılmadan önce ek güvenlik testleri yapılması önerilir.
