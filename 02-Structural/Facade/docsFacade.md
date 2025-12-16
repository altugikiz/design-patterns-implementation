# 🏠 Facade Pattern

## Tek Cümlede
> **Karmaşık alt sistemleri basit bir arayüz arkasına gizle, client'a tek kapı sun.**

---

## 🎯 Ne Zaman Kullanılır?
- Karmaşık bir sistemle **basit bir şekilde** iletişim kurmak istediğinde
- Alt sistemlerin detaylarını **gizlemek** istediğinde
- Çok fazla sınıf/metod arasındaki **bağımlılığı azaltmak** istediğinde
- **"Tek tuşla her şeyi yap"** senaryolarında

---

## 🧩 Yapı (UML)

```
                         ┌─────────────────────┐
                         │       Client        │
                         │       (Main)        │
                         └──────────┬──────────┘
                                    │
                                    │ watchMovie()
                                    │ endMovie()
                                    ▼
                         ┌─────────────────────┐
                         │  HomeTheaterFacade  │
                         │      (Facade)       │
                         ├─────────────────────┤
                         │ - amp               │
                         │ - projector         │
                         │ - lights            │
                         │ + watchMovie()      │
                         │ + endMovie()        │
                         └──────────┬──────────┘
                                    │
              ┌─────────────────────┼─────────────────────┐
              │                     │                     │
              ▼                     ▼                     ▼
    ┌─────────────────┐   ┌─────────────────┐   ┌─────────────────┐
    │    Amplifier    │   │    Projector    │   │  TheaterLights  │
    │  (Subsystem)    │   │  (Subsystem)    │   │  (Subsystem)    │
    ├─────────────────┤   ├─────────────────┤   ├─────────────────┤
    │ + on()          │   │ + on()          │   │ + dim()         │
    │ + off()         │   │ + off()         │   │ + on()          │
    │ + setVolume()   │   │ + setInput()    │   │                 │
    └─────────────────┘   └─────────────────┘   └─────────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **Facade** | `HomeTheaterFacade` | Basit arayüz sunan sınıf |
| **Subsystems** | `Amplifier`, `Projector`, `TheaterLights` | Karmaşık alt sistemler |
| **Client** | `Main` | Facade'i kullanan kod |

---

## 🔥 Bu Projedeki Senaryo (Ev Sineması)

```
┌──────────────┐                    ┌──────────────────────────────────┐
│    Client    │   watchMovie()     │         HomeTheaterFacade        │
│              │ ──────────────────►│                                  │
│  "Film izle" │                    │  1. lights.dim(10)               │
│              │                    │  2. projector.on()               │
│              │                    │  3. projector.setInput("HDMI")   │
│              │                    │  4. amp.on()                     │
│              │                    │  5. amp.setVolume(5)             │
└──────────────┘                    └──────────────────────────────────┘
      │                                           │
      │                                           ▼
      │                               ┌───────────────────┐
      │                               │   5 ADIM TEK      │
      │                               │   METODA SIĞDI!   │
      └──────────────────────────────►│                   │
                                      └───────────────────┘
```

**Kilit Nokta:** Client 5 farklı nesneyle uğraşmıyor → Sadece `watchMovie()` çağırıyor!

---

## 💻 Kod Akışı

```java
// FACADE OLMADAN (Karmaşık) ❌
lights.dim(10);
projector.on();
projector.setInput("HDMI");
amp.on();
amp.setVolume(5);
System.out.println("Film başlıyor...");

// FACADE İLE (Basit) ✅
homeTheater.watchMovie("Inception");  // Tek satır!
```

---

## ⚡ Hızlı Hatırlatma

```
       FACADE OLMADAN                         FACADE İLE
    ┌─────────────────┐                  ┌─────────────────┐
    │     Client      │                  │     Client      │
    └────────┬────────┘                  └────────┬────────┘
             │                                    │
    ┌────────┼────────┐                           │
    │        │        │                           │
    ▼        ▼        ▼                           ▼
┌──────┐ ┌──────┐ ┌──────┐               ┌──────────────┐
│ Amp  │ │Proj. │ │Lights│               │    Facade    │
└──────┘ └──────┘ └──────┘               └───────┬──────┘
                                                 │
    Client HER ŞEYİ                    ┌─────────┼─────────┐
    bilmek zorunda!                    ▼         ▼         ▼
                                   ┌──────┐ ┌──────┐ ┌──────┐
                                   │ Amp  │ │Proj. │ │Lights│
                                   └──────┘ └──────┘ └──────┘

                                   Facade HER ŞEYİ biliyor,
                                   Client sadece Facade'i biliyor!
```

---

## 🆚 Gerçek Hayat Analogisi

**Otel Resepsiyonu** 🏨
- **Client:** Misafir ("Oda, kahvaltı ve transfer istiyorum")
- **Facade:** Resepsiyonist (Tek kişiyle konuşursun)
- **Subsystems:** Kat hizmetleri, mutfak, şoför

Misafir 10 farklı departmanı aramıyor → Resepsiyona tek telefon!

**Araba Çalıştırma** 🚗
- **Client:** Sürücü (Sadece anahtarı çevir)
- **Facade:** Kontak sistemi
- **Subsystems:** Akü, motor, yakıt pompası, ateşleme...

Sürücü 50 parçayı tek tek çalıştırmıyor → Anahtarı çevirince Facade hallediyor!

---

## 🔑 Kritik Kod

```java
// HomeTheaterFacade.java - TEK TUŞLA İŞLEM
public void watchMovie(String movie) {
    lights.dim(10);           // Alt sistem 1
    projector.on();           // Alt sistem 2
    projector.setInput("HDMI");
    amp.on();                 // Alt sistem 3
    amp.setVolume(5);
    System.out.println("Movie starting: " + movie);
}
```

**Facade, alt sistemlerin karmaşıklığını gizliyor!**

---

## ✅ Avantajları
- **Basitlik:** Client karmaşık sistemi bilmez
- **Loose Coupling:** Alt sistemler değişse bile Facade aynı kalabilir
- **Layered Architecture:** Katmanlı mimari kurulabilir
- Yeni başlayanlar için kolay kullanım

## ❌ Dezavantajları
- **God Object riski:** Facade çok fazla iş yapabilir
- Alt sistem özelliklerine erişim kısıtlanabilir
- Gereksiz abstraction olabilir (basit sistemlerde)

---

## 🆚 Facade vs Adapter vs Decorator

| Facade | Adapter | Decorator |
|--------|---------|-----------|
| **Basitleştirir** | **Uyumlu hale getirir** | **Özellik ekler** |
| Karmaşık → Basit | Uyumsuz → Uyumlu | Temel → Zengin |
| Birçok sınıfı sarar | Tek sınıfı sarar | Tek sınıfı sarar |
| Yeni arayüz sunar | Mevcut arayüze uyar | Aynı arayüzü genişletir |

---

## 🎯 Özet

```
┌─────────────────────────────────────────────────────────────┐
│                         FACADE                              │
│                                                             │
│   "Karmaşık sistemin önüne basit bir kapı koy"              │
│                                                             │
│   Client ──► Facade ──► [Subsystem1, Subsystem2, ...]       │
│                                                             │
│   watchMovie() = dim + projOn + setInput + ampOn + volume   │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

**Hatırla:** Facade = Karmaşıklığı gizleyen **basit kapı** 🚪
