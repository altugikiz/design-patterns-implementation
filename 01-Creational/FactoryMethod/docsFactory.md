# 🏭 Factory Method Pattern

## Tek Cümlede
> **Nesne oluşturmayı alt sınıflara bırak, `new` kelimesini client'tan gizle.**

---

## 🎯 Ne Zaman Kullanılır?
- Hangi nesnenin oluşturulacağı **runtime'da** belirlenecekse
- `new` kullanımını **merkezi bir yerde** toplamak istiyorsan
- Kodda çok fazla `if-else new Car()`, `if-else new Truck()` varsa
- Yeni ürün tipi eklerken **mevcut kodu değiştirmek istemiyorsan**

---

## 🧩 Yapı (UML)

```
┌─────────────────────┐         ┌─────────────────────┐
│   VehicleFactory    │         │       Vehicle       │
│  (Abstract Creator) │         │   (Product Interface)│
├─────────────────────┤         ├─────────────────────┤
│ + createVehicle()   │────────►│ + getType()         │
└──────────▲──────────┘         └──────────▲──────────┘
           │                               │
           │ extends                       │ implements
           │                               │
┌──────────┴──────────┐         ┌──────────┴──────────┐
│                     │         │                     │
│  ┌───────────────┐  │         │  ┌───────────────┐  │
│  │  CarFactory   │  │         │  │     Car       │  │
│  ├───────────────┤  │         │  ├───────────────┤  │
│  │createVehicle()│──┼─────────┼─►│ + getType()   │  │
│  │ return new Car│  │         │  └───────────────┘  │
│  └───────────────┘  │         │                     │
│                     │         │  ┌───────────────┐  │
│  ┌───────────────┐  │         │  │    Truck      │  │
│  │ TruckFactory  │  │         │  ├───────────────┤  │
│  ├───────────────┤  │         │  │ + getType()   │  │
│  │createVehicle()│──┼─────────┼─►└───────────────┘  │
│  │return new Truck  │         │                     │
│  └───────────────┘  │         │  ┌───────────────┐  │
│                     │         │  │     Bike      │  │
│  ┌───────────────┐  │         │  ├───────────────┤  │
│  │  BikeFactory  │  │         │  │ + getType()   │  │
│  ├───────────────┤  │         │  └───────────────┘  │
│  │createVehicle()│──┼─────────┼─►                   │
│  │return new Bike│  │         │                     │
│  └───────────────┘  │         │                     │
└─────────────────────┘         └─────────────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **Product** | `Vehicle` | Ürün arayüzü |
| **ConcreteProduct** | `Car`, `Truck`, `Bike` | Gerçek ürünler |
| **Creator** | `VehicleFactory` | Fabrika arayüzü (abstract) |
| **ConcreteCreator** | `CarFactory`, `TruckFactory`, `BikeFactory` | Gerçek fabrikalar |

---

## 🔥 Bu Projedeki Senaryo (Lojistik)

```
┌──────────────┐     createVehicle()     ┌──────────────┐
│   Client     │ ───────────────────────►│   Factory    │
│   (Main)     │                         │              │
└──────────────┘                         └──────┬───────┘
       │                                        │
       │ simulateDelivery(factory)              │ return new Car/Truck/Bike
       │                                        │
       ▼                                        ▼
  Factory'den                              ┌──────────────┐
  gelen Vehicle'ı                          │   Vehicle    │
  kullan!                                  │ (Car/Truck)  │
                                           └──────────────┘
```

**Kilit Nokta:** `simulateDelivery()` hangi araç geldiğini bilmiyor! Sadece `Vehicle` arayüzüyle konuşuyor.

---

## 💻 Kod Akışı

```java
// SENARYO 1: Araba ile teslimat
VehicleFactory factory1 = new CarFactory();
simulateDelivery(factory1);  // → Car is on the way!

// SENARYO 2: Kamyon ile teslimat (Kod değişmedi!)
VehicleFactory factory2 = new TruckFactory();
simulateDelivery(factory2);  // → Truck is on the way!

// SENARYO 3: Bisiklet kurye eklendi (Yeni factory, eski kod aynı!)
VehicleFactory factory3 = new BikeFactory();
simulateDelivery(factory3);  // → Bike is on the way!
```

```java
// simulateDelivery - Factory'nin ne döndüreceğini BİLMİYOR!
public static void simulateDelivery(VehicleFactory factory) {
    Vehicle vehicle = factory.createVehicle();  // ← new YOK!
    System.out.println(vehicle.getType() + " is on the way!");
}
```

---

## ⚡ Hızlı Hatırlatma

```
         CLIENT                    FACTORY                   PRODUCT
    ┌──────────────┐          ┌──────────────┐          ┌──────────────┐
    │     Main     │          │  CarFactory  │          │     Car      │
    │              │ ────────►│              │ ────────►│              │
    │  Bilmez:     │ factory  │  Bilir:      │ new Car  │  Gerçek      │
    │  new Car()   │          │  new Car()   │          │  ürün        │
    └──────────────┘          └──────────────┘          └──────────────┘
          │                          │
          │    simulateDelivery()    │
          │    ───────────────────►  │
          │                          │
          │  factory.createVehicle() │
          │    ◄───────────────────  │
          │       Vehicle döner      │
```

---

## 🚫 Factory OLMADAN (Kötü Kod)

```java
public void startDelivery(String vehicleType) {
    Vehicle vehicle;
    
    if (vehicleType.equals("car")) {
        vehicle = new Car();
    } else if (vehicleType.equals("truck")) {
        vehicle = new Truck();
    } else if (vehicleType.equals("bike")) {
        vehicle = new Bike();
    } else if (vehicleType.equals("drone")) {
        vehicle = new Drone();  // Yeni ürün = Kodu değiştir! 😱
    }
    
    vehicle.deliver();
}
```

**Problem:** Her yeni araç = if-else ekle = SOLID ihlali!

---

## ✅ Factory İLE (İyi Kod)

```java
public void startDelivery(VehicleFactory factory) {
    Vehicle vehicle = factory.createVehicle();  // Tek satır! 🎉
    vehicle.deliver();
}

// Yeni araç (Drone) eklemek için:
// 1. Drone implements Vehicle
// 2. DroneFactory extends VehicleFactory
// 3. Mevcut kod DEĞİŞMEZ!
```

---

## 🆚 Gerçek Hayat Analogisi

**Pizza Dükkanı** 🍕
- **Creator:** Pizzacı (Tarife göre pizza yapar)
- **ConcreteCreator:** İtalyan Pizzacı, Türk Pizzacı
- **Product:** Pizza
- **ConcreteProduct:** Margherita, Lahmacun

```
Müşteri sipariş verir → Pizzacı üretir → Pizza gelir
Müşteri "new Margherita()" demez!
```

---

## 🔑 Kritik Kod

```java
// VehicleFactory.java - FACTORY METHOD
abstract class VehicleFactory {
    abstract Vehicle createVehicle();  // ← Alt sınıflar bunu doldurur!
}

// CarFactory.java - Sadece Car üretir
class CarFactory extends VehicleFactory {
    @Override
    Vehicle createVehicle() {
        return new Car();  // ← new burada GİZLİ!
    }
}
```

**`new` kelimesi Factory'nin içinde → Client'ta YOK!**

---

## ✅ Avantajları
- **Open/Closed:** Yeni ürün = Yeni factory, mevcut kod değişmez
- **Single Responsibility:** Oluşturma mantığı ayrı sınıfta
- **Loose Coupling:** Client somut sınıfları bilmez
- `new` tek yerde → Değişiklik kolay

## ❌ Dezavantajları
- Her ürün için ayrı factory sınıfı = Çok fazla sınıf
- Basit durumlar için overkill olabilir

---

## 🆚 Factory Method vs Abstract Factory

| Factory Method | Abstract Factory |
|----------------|------------------|
| **Tek ürün** ailesi | **Birden fazla ürün** ailesi |
| `createVehicle()` | `createCar()`, `createTruck()`, `createBike()` |
| Miras (inheritance) | Kompozisyon |
| Daha basit | Daha karmaşık |

---

## 🆚 Factory Method vs Simple Factory

| Factory Method | Simple Factory |
|----------------|----------------|
| Abstract class + subclasses | Tek static metod |
| Alt sınıflar karar verir | if-else ile karar |
| Daha esnek | Daha basit |
| `CarFactory.createVehicle()` | `Factory.create("car")` |