# 🎁 Decorator Pattern

## Tek Cümlede
> **Nesneye çalışma zamanında yeni özellikler ekleyen "sarmalayıcı" sınıf.**

---

## 🎯 Ne Zaman Kullanılır?
- Bir nesneye dinamik olarak özellik eklemek istediğinde
- Alt sınıf patlamasından (class explosion) kaçınmak istediğinde
- Özellikleri isteğe bağlı kombinasyonlarla kullanmak istediğinde

---

## 🧩 Yapı (UML)

```
        ┌─────────────────┐
        │     Coffee      │◄─────────────────────────┐
        │   (Component)   │                          │
        ├─────────────────┤                          │
        │ + getCost()     │                          │
        │ + getDescription│                          │
        └────────▲────────┘                          │
                 │                                   │
    ┌────────────┴────────────┐                      │
    │                         │                      │
┌───┴───────────┐    ┌────────┴────────┐             │
│ SimpleCoffee  │    │ CoffeeDecorator │─────────────┘
│  (Concrete)   │    │   (Decorator)   │  wraps Coffee
├───────────────┤    ├─────────────────┤
│ + getCost()   │    │ - tempCoffee    │
│ + getDesc()   │    │ + getCost()     │
└───────────────┘    └────────▲────────┘
                              │
                 ┌────────────┴────────────┐
                 │                         │
          ┌──────┴──────┐          ┌───────┴─────┐
          │MilkDecorator│          │SugarDecorator│
          ├─────────────┤          ├─────────────┤
          │ + getCost() │          │ + getCost() │
          │ + getDesc() │          │ + getDesc() │
          └─────────────┘          └─────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **Component** | `Coffee` | Ortak arayüz |
| **ConcreteComponent** | `SimpleCoffee` | Temel nesne (sade kahve) |
| **Decorator** | `CoffeeDecorator` | Sarmalayıcı temel sınıf |
| **ConcreteDecorator** | `MilkDecorator`, `SugarDecorator` | Ek özellik ekleyenler |

---

## 🔥 Bu Projedeki Senaryo (Starbucks)

```
Sade Kahve (50₺) ──► + Süt (15₺) ──► + Şeker (5₺) = 70₺
```

1. **Problem:** Her kombinasyon için ayrı sınıf mı oluşturacağız?
   - `SutluKahve`, `SekerliKahve`, `SutluSekerliKahve`... 💥 Patlama!
2. **Çözüm:** Decorator ile iç içe sarmalama (Matruşka bebek gibi)

---

## 💻 Kod Akışı

```java
// İç içe sarmalama - Matruşka bebekleri gibi
Coffee superCoffee = new SugarDecorator(    // En dış katman: Şeker
                        new MilkDecorator(   // Orta katman: Süt
                            new SimpleCoffee() // Çekirdek: Sade Kahve
                        )
                     );

superCoffee.getCost();        // 50 + 15 + 5 = 70₺
superCoffee.getDescription(); // "Plain Coffee, Milk, Sugar"
```

**Çağrı zinciri:**
```
SugarDecorator.getCost()
    └── MilkDecorator.getCost()
            └── SimpleCoffee.getCost() → 50
        └── return 50 + 15 = 65
    └── return 65 + 5 = 70
```

---

## ⚡ Hızlı Hatırlatma

```
┌─────────────────────────────────────────┐
│            SugarDecorator (+5₺)         │
│  ┌───────────────────────────────────┐  │
│  │        MilkDecorator (+15₺)       │  │
│  │  ┌─────────────────────────────┐  │  │
│  │  │     SimpleCoffee (50₺)      │  │  │
│  │  └─────────────────────────────┘  │  │
│  └───────────────────────────────────┘  │
└─────────────────────────────────────────┘
              TOPLAM = 70₺
```

---

## 🆚 Gerçek Hayat Analogisi

**Kıyafet Katmanları** 👕
- Çekirdek: T-shirt (Sen)
- Decorator 1: Kazak (+ Sıcaklık)
- Decorator 2: Mont (+ Rüzgar koruması)
- Decorator 3: Yağmurluk (+ Su geçirmezlik)

Her katman özellik ekler, altındakini değiştirmez!

---

## 🔑 Kritik Nokta

```java
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee tempCoffee;  // ← İÇİNDE BİR COFFEE TUTUYOR!
    
    public double getCost() {
        return tempCoffee.getCost();  // ← Önce içindekine sor, sonra ekle
    }
}
```

**Decorator hem `Coffee`'dir, hem de içinde `Coffee` tutar!**

---

## ✅ Avantajları
- Open/Closed: Mevcut kodu değiştirmeden özellik ekle
- Single Responsibility: Her decorator tek bir özellik ekler
- Çalışma zamanında kombinasyon oluştur

## ❌ Dezavantajları
- Çok fazla küçük sınıf oluşabilir
- Decorator sırası önemli olabilir
- Debugging zorlaşabilir (iç içe çağrılar)

---

## 🆚 Inheritance vs Decorator

| Inheritance | Decorator |
|-------------|-----------|
| Compile-time | Runtime |
| Sabit kombinasyon | Dinamik kombinasyon |
| Class explosion | Esnek yapı |
| `SutluSekerliKahve extends Kahve` | `new Seker(new Sut(new Kahve()))` |


