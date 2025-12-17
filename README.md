# 🎨 Design Patterns Implementation

Java ile yazılmış **Tasarım Desenleri (Design Patterns)** örnekleri. Her pattern için çalışır kod ve detaylı Türkçe dokümantasyon bulunmaktadır.

---

## 📚 İçindekiler

### 01 - Creational Patterns (Yaratımsal)
Nesnelerin **nasıl oluşturulacağı** ile ilgilenir.

| Pattern | Açıklama | Klasör |
|---------|----------|--------|
| **Singleton** | Bir sınıftan sadece tek bir nesne oluşturulmasını garanti eder | [📁 Singleton](01-Creational/Singleton) |
| **Factory Method** | Nesne oluşturmayı alt sınıflara bırakır | [📁 Factory Method](01-Creational/FactoryMethod) |

---

### 02 - Structural Patterns (Yapısal)
Sınıfların ve nesnelerin **nasıl birleştirileceği** ile ilgilenir.

| Pattern | Açıklama | Klasör |
|---------|----------|--------|
| **Adapter** | Uyumsuz arayüzleri birbirine bağlar | [📁 Adapter](02-Structural/Adapter) |
| **Composite** | Ağaç yapısında nesneleri tek tip gibi işler | [📁 Composite](02-Structural/Composite) |
| **Decorator** | Nesnelere dinamik olarak yeni özellikler ekler | [📁 Decorator](02-Structural/Decorator) |
| **Facade** | Karmaşık sistemlere basit bir arayüz sağlar | [📁 Facade](02-Structural/Facade) |

---

### 03 - Behavioral Patterns (Davranışsal)
Nesneler arasındaki **iletişim ve sorumluluk dağılımı** ile ilgilenir.

| Pattern | Açıklama | Klasör |
|---------|----------|--------|
| **Command** | İstekleri nesne olarak kapsüller | [📁 Command](03-Behavioral/Command) |
| **Iterator** | Koleksiyonları iç yapısını bilmeden dolaşır | [📁 Iterator](03-Behavioral/Iterator) |
| **Observer** | Bir nesne değiştiğinde bağımlılarını bilgilendirir | [📁 Observer](03-Behavioral/Observer) |
| **State** | Duruma göre davranış değiştirir | [📁 State](03-Behavioral/State) |
| **Strategy** | Algoritmaları değiştirilebilir hale getirir | [📁 Strategy](03-Behavioral/Strategy) |
| **Template Method** | Algoritma iskeletini tanımlar, adımları alt sınıflara bırakır | [📁 Template Method](03-Behavioral/TemplateMethod) |

---

## 🚀 Nasıl Çalıştırılır?

Her pattern klasöründe bir `Main.java` dosyası bulunur. Çalıştırmak için:

```bash
# Örnek: Singleton Pattern
cd 01-Creational/Singleton
javac *.java
java Main
```

---

## 📖 Dokümantasyon

Her pattern klasöründe `docs*.md` dosyası bulunur. Bu dosyalar:
- 🎯 Pattern'in ne işe yaradığını
- 💡 Neden kullanıldığını
- 🏗️ UML diyagramını
- 💻 Kod örneklerini
- 🎓 Sınav ipuçlarını içerir

---

## 🗂️ Proje Yapısı

```
design-patterns-implementation/
├── 01-Creational/
│   ├── FactoryMethod/
│   └── Singleton/
├── 02-Structural/
│   ├── Adapter/
│   ├── Composite/
│   ├── Decorator/
│   └── Facade/
├── 03-Behavioral/
│   ├── Command/
│   ├── Iterator/
│   ├── Observer/
│   ├── State/
│   ├── Strategy/
│   └── TemplateMethod/
└── README.md
```

---

## 🎯 Hızlı Referans

| Kategori | Ne Zaman Kullan? |
|----------|------------------|
| **Creational** | Nesne oluşturma mantığını soyutlamak istediğinde |
| **Structural** | Sınıfları/nesneleri düzenlemek, birleştirmek istediğinde |
| **Behavioral** | Nesneler arası iletişimi düzenlemek istediğinde |

---

## 📝 Lisans

Bu proje eğitim amaçlıdır.

---

⭐ **Sınav öncesi hızlıca göz atmak için ideal!** İyi çalışmalar! 🍀