# 📋 Template Method Pattern

## Tek Cümlede
> **Algoritmanın iskeletini üst sınıfta tanımla, bazı adımları alt sınıflara bırak.**

---

## 🎯 Ne Zaman Kullanılır?
- Algoritmanın **genel yapısı sabit**, bazı adımları **değişken** olduğunda
- **Kod tekrarını** önlemek istediğinde (ortak adımlar üst sınıfta)
- Alt sınıfların **sadece belirli adımları** değiştirmesini istediğinde
- **"Hollywood Prensibi"**: "Bizi arama, biz seni ararız"

---

## 🧩 Yapı (UML)

```
┌─────────────────────────────────────────┐
│           BeverageMaker                 │
│         (Abstract Class)                │
├─────────────────────────────────────────┤
│ + prepareRecipe() {final}               │  ← TEMPLATE METHOD
│   ├── boilWater()                       │  ← Ortak (private)
│   ├── brew()                            │  ← Abstract (alt sınıf doldurur)
│   ├── pourInCup()                       │  ← Ortak (private)
│   └── addCondiments()                   │  ← Abstract (alt sınıf doldurur)
├─────────────────────────────────────────┤
│ - boilWater()                           │
│ - pourInCup()                           │
│ ~ abstract brew()                       │
│ ~ abstract addCondiments()              │
└────────────────────▲────────────────────┘
                     │
                     │ extends
        ┌────────────┴────────────┐
        │                         │
┌───────┴───────┐         ┌───────┴───────┐
│      Tea      │         │    Coffee     │
├───────────────┤         ├───────────────┤
│ + brew()      │         │ + brew()      │
│ + addCondi..()│         │ + addCondi..()│
└───────────────┘         └───────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **AbstractClass** | `BeverageMaker` | Şablon metodu + ortak adımlar |
| **ConcreteClass** | `Tea`, `Coffee` | Değişken adımları doldurur |
| **Template Method** | `prepareRecipe()` | Algoritmanın iskeleti (final!) |
| **Abstract Methods** | `brew()`, `addCondiments()` | Alt sınıfların dolduracağı adımlar |

---

## 🔥 Bu Projedeki Senaryo (Çay/Kahve Yapımı)

```
┌─────────────────────────────────────────────────────────────┐
│                    prepareRecipe()                          │
│                   (Template Method)                         │
├─────────────────────────────────────────────────────────────┤
│  1. boilWater()        → Ortak   → "Boiling water..."       │
│  2. brew()             → DEĞİŞKEN → Tea: "Steeping tea..."  │
│                                   → Coffee: "Dripping..."   │
│  3. pourInCup()        → Ortak   → "Pouring into cup..."    │
│  4. addCondiments()    → DEĞİŞKEN → Tea: "Adding lemon..."  │
│                                   → Coffee: "Adding milk..."|
└─────────────────────────────────────────────────────────────┘
```

**Kilit Nokta:** Sıra DEĞİŞMEZ! Sadece bazı adımların içeriği değişir.

---

## 💻 Kod Akışı

```java
// Çay hazırla
BeverageMaker tea = new Tea();
tea.prepareRecipe();
// Output:
// Boiling water...
// Steeping tea...        ← Tea'nin brew()'u
// Pouring into cup...
// Adding lemon...        ← Tea'nin addCondiments()'ı

// Kahve hazırla
BeverageMaker coffee = new Coffee();
coffee.prepareRecipe();
// Output:
// Boiling water...
// Dripping coffee...     ← Coffee'nin brew()'u
// Pouring into cup...
// Adding milk and sugar... ← Coffee'nin addCondiments()'ı
```

---

## ⚡ Hızlı Hatırlatma

```
        TEMPLATE METHOD (üst sınıf)              ALT SINIFLAR

    ┌─────────────────────────┐           ┌─────────────────────┐
    │    prepareRecipe()      │           │        Tea          │
    │    ─────────────────    │           ├─────────────────────┤
    │    1. boilWater()    ───┼─ Sabit    │ brew() {            │
    │    2. brew()         ───┼───────────┼──► steeping tea     │
    │    3. pourInCup()    ───┼─ Sabit    │ }                   │
    │    4. addCondiments()───┼───────────┼──► adding lemon     │
    │                         │           └─────────────────────┘
    │    final = Kimse        │           ┌─────────────────────┐
    │    sırayı bozamaz!      │           │       Coffee        │
    └─────────────────────────┘           ├─────────────────────┤
                                          │ brew() {            │
                                          │   dripping coffee   │
                                          │ }                   │
                                          │ addCondiments() {   │
                                          │   adding milk       │
                                          │ }                   │
                                          └─────────────────────┘
```

---

## 🔑 Kritik Kod

```java
// BeverageMaker.java - TEMPLATE METHOD
public abstract class BeverageMaker {
    
    // final = Alt sınıflar bu metodu override EDEMEZ!
    public final void prepareRecipe() {
        boilWater();      // Ortak
        brew();           // Abstract - Alt sınıf doldurur
        pourInCup();      // Ortak
        addCondiments();  // Abstract - Alt sınıf doldurur
    }
    
    // Ortak adımlar - private
    private void boilWater() { ... }
    private void pourInCup() { ... }
    
    // Değişken adımlar - abstract
    abstract void brew();
    abstract void addCondiments();
}
```

**`final` anahtar kelimesi = Algoritma sırası değiştirilemez!**

---

## 🆚 Gerçek Hayat Analogisi

**Ev İnşaatı** 🏠
- **Template Method:** İnşaat süreci (Temel → Duvar → Çatı → Boya)
- **Ortak Adımlar:** Temel atma (hep aynı)
- **Değişken Adımlar:** Boya rengi, çatı tipi (müşteriye göre)

```
Temel at → Duvar ör → Çatı yap → Boya at
   │          │          │          │
 Sabit      Sabit    Kiremit/    Beyaz/
                     Düz çatı    Mavi/Sarı
```

**IKEA Mobilya Montajı** 🪑
- **Template Method:** Montaj kılavuzu sırası
- **Ortak:** Parçaları çıkar, vida sık
- **Değişken:** Hangi parçalar, kaç vida

---

## 🎣 Hook Metotları (Opsiyonel Adımlar)

```java
public abstract class BeverageMaker {
    
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {  // ← HOOK
            addCondiments();
        }
    }
    
    // HOOK: Varsayılan davranış var, alt sınıf isterse değiştirir
    boolean customerWantsCondiments() {
        return true;  // Varsayılan: Evet
    }
}

class Tea extends BeverageMaker {
    @Override
    boolean customerWantsCondiments() {
        return false;  // Çaya limon istemiyorum
    }
}
```

**Hook vs Abstract:**
- **Abstract:** Alt sınıf MUTLAKA doldurmalı
- **Hook:** Alt sınıf İSTERSE değiştirebilir

---

## 🎬 Hollywood Prensibi

```
┌─────────────────────────────────────────────────────────────┐
│              "Don't call us, we'll call you"                │
│              "Bizi arama, biz seni ararız"                  │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   ÜST SINIF (BeverageMaker)     ALT SINIF (Tea/Coffee)      │
│                                                             │
│   prepareRecipe() ─────────────────────────┐                │
│        │                                   │                │
│        ├── boilWater()                     │                │
│        │                                   ▼                │
│        ├── brew() ─────────────────► Tea.brew()             │
│        │                                   │                │
│        ├── pourInCup()                     │                │
│        │                                   ▼                │
│        └── addCondiments() ────────► Tea.addCondiments()    │
│                                                             │
│   Üst sınıf KONTROL EDİYOR, alt sınıfları ÇAĞIRIYOR!        │
└─────────────────────────────────────────────────────────────┘
```

---

## ✅ Avantajları
- **Kod tekrarı yok:** Ortak adımlar tek yerde
- **Kontrollü genişleme:** Alt sınıflar sadece izin verilen yerleri değiştirir
- **Algoritma bütünlüğü:** `final` ile sıra korunur
- **Open/Closed:** Yeni içecek = Yeni alt sınıf, üst sınıf değişmez

## ❌ Dezavantajları
- Alt sınıflar üst sınıfa **sıkı bağımlı** (inheritance)
- Çok fazla abstract metot = Alt sınıflar karmaşıklaşır
- Liskov Substitution ihlal riski

---

## 🆚 Template Method vs Strategy

| Template Method | Strategy |
|-----------------|----------|
| **Inheritance** (miras) | **Composition** (bileşim) |
| Alt sınıflar bazı adımları değiştirir | Tüm algoritma değişir |
| Compile-time seçim | Runtime seçim |
| `class Tea extends BeverageMaker` | `cart.setStrategy(new PayPal())` |
| Sıra SABİT, adımlar değişken | Tüm algoritma değişken |

---

## 🎯 Özet

```
┌─────────────────────────────────────────────────────────────┐
│                    TEMPLATE METHOD                          │
│                                                             │
│   "Algoritmanın iskeleti üstte, detaylar altta"             │
│                                                             │
│   AbstractClass:                                            │
│   ┌─────────────────────────────────────┐                   │
│   │ templateMethod() {final}            │                   │
│   │   step1();  // ortak                │                   │
│   │   step2();  // abstract → alt sınıf │                   │
│   │   step3();  // ortak                │                   │
│   │   step4();  // abstract → alt sınıf │                   │
│   └─────────────────────────────────────┘                   │
│                                                             │
│   Üst sınıf SÜRECİ kontrol eder                             │
│   Alt sınıf DETAYLARI doldurur                              │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

**Hatırla:** Template Method = **İskelet üstte, et altta!** 🦴
