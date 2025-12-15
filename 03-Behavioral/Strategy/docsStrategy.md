# 🎯 Strategy Pattern

## Tek Cümlede
> **Algoritmaları ayrı sınıflara koy, çalışma zamanında istediğini seç.**

---

## 🎯 Ne Zaman Kullanılır?
- Aynı işi **farklı yöntemlerle** yapman gerektiğinde
- Kodda çok fazla **if-else / switch** varsa
- Algoritmaları **runtime'da değiştirmek** istediğinde
- Yeni yöntem eklerken mevcut kodu **değiştirmek istemediğinde**

---

## 🧩 Yapı (UML)

```
┌─────────────────────┐         ┌─────────────────────┐
│    ShoppingCart     │         │   PaymentStrategy   │
│     (Context)       │────────►│    (Interface)      │
├─────────────────────┤         ├─────────────────────┤
│ - paymentStrategy   │         │ + pay(amount)       │
│ + setPaymentStrategy│         └──────────▲──────────┘
│ + checkout()        │                    │
└─────────────────────┘                    │ implements
                                           │
                       ┌───────────────────┼───────────────────┐
                       │                   │                   │
              ┌────────┴────────┐ ┌────────┴────────┐ ┌────────┴────────┐
              │CreditCardPayment│ │  PayPalPayment  │ │  BitcoinPayment │
              ├─────────────────┤ ├─────────────────┤ ├─────────────────┤
              │ - cardNumber    │ │ - email         │ │ - walletAddress │
              │ + pay()         │ │ + pay()         │ │ + pay()         │
              └─────────────────┘ └─────────────────┘ └─────────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **Strategy** | `PaymentStrategy` | Ortak arayüz |
| **ConcreteStrategy** | `CreditCardPayment`, `PayPalPayment` | Gerçek algoritmalar |
| **Context** | `ShoppingCart` | Stratejiyi kullanan sınıf |

---

## 🔥 Bu Projedeki Senaryo

```
┌──────────────┐     setPaymentStrategy()     ┌──────────────┐
│ ShoppingCart │ ◄─────────────────────────── │    Client    │
│   (Context)  │                              │    (Main)    │
└──────┬───────┘                              └──────────────┘
       │
       │ checkout(100)
       │
       ▼
┌──────────────┐
│   Strategy   │ ←── Hangisi set edildiyse O çalışır!
├──────────────┤
│ CreditCard?  │ → "100 TL paid with Credit Card..."
│ PayPal?      │ → "100 TL withdrawn via PayPal..."
└──────────────┘
```

---

## 💻 Kod Akışı

```java
ShoppingCart cart = new ShoppingCart();

// SENARYO 1: Kredi Kartı ile öde
cart.setPaymentStrategy(new CreditCardPayment("1234-5678", "999"));
cart.checkout(100);  // → "100 TL paid with Credit Card..."

// SENARYO 2: Kullanıcı fikir değiştirdi, PayPal ile ödeyecek
cart.setPaymentStrategy(new PayPalPayment("altug@example.com"));
cart.checkout(250);  // → "250 TL withdrawn via PayPal..."
```

**Kilit Nokta:** `checkout()` metodu hiç değişmedi! Sadece strateji değişti.

---

## ⚡ Hızlı Hatırlatma

```
                    ┌─────────────────────┐
                    │    ShoppingCart     │
                    │                     │
     setStrategy()  │  paymentStrategy ───┼───► [CreditCard]
         │          │                     │     [PayPal]
         ▼          │  checkout() {       │     [Bitcoin]
    Değiştir!       │    strategy.pay()   │        │
                    │  }                  │        │
                    └─────────────────────┘        ▼
                                               Biri seçilir
                                               ve çalışır
```

---

## 🚫 Strategy OLMADAN (Kötü Kod)

```java
public void checkout(int amount, String method) {
    if (method.equals("credit")) {
        // Kredi kartı kodu...
    } else if (method.equals("paypal")) {
        // PayPal kodu...
    } else if (method.equals("bitcoin")) {
        // Bitcoin kodu...
    } else if (method.equals("apple_pay")) {
        // Apple Pay kodu...
    }
    // 50 tane daha if-else... 😱
}
```

**Problem:** Yeni ödeme yöntemi = Mevcut kodu değiştir = SOLID ihlali!

---

## ✅ Strategy İLE (İyi Kod)

```java
public void checkout(int amount) {
    paymentStrategy.pay(amount);  // Tek satır! 🎉
}

// Yeni ödeme yöntemi eklemek için:
// 1. Yeni sınıf oluştur: ApplePayPayment implements PaymentStrategy
// 2. Mevcut kod DEĞİŞMEZ!
```

---

## 🆚 Gerçek Hayat Analogisi

**Navigasyon Uygulaması** 🗺️
- **Context:** Google Maps
- **Strategies:** Araba, Yürüyüş, Bisiklet, Toplu Taşıma
- **pay() → calculateRoute()**

Aynı "A'dan B'ye git" işlemi, farklı algoritmalarla!

```
Araba:     En hızlı yol, otoyollar
Yürüyüş:   Kaldırımlar, parklar
Bisiklet:  Bisiklet yolları
Metro:     Duraklar, aktarmalar
```

---

## 🔑 Kritik Kod

```java
// ShoppingCart.java - DELEGATION (Yetki Devri)
public void checkout(int amount) {
    if (paymentStrategy == null) {
        System.out.println("Please select a payment method!");
        return;
    }
    paymentStrategy.pay(amount);  // ← İŞİ STRATEJİYE BIRAK!
}
```

**Context işin nasıl yapıldığını bilmiyor → Strateji biliyor!**

---

## ✅ Avantajları
- **Open/Closed:** Yeni strateji = Yeni sınıf, mevcut kod değişmez
- **Single Responsibility:** Her strateji kendi işini yapar
- **If-else cehenneminden kurtuluş**
- Runtime'da algoritma değişimi

## ❌ Dezavantajları
- Az sayıda algoritma için overkill
- Client tüm stratejileri bilmek zorunda
- Strateji sayısı arttıkça sınıf sayısı artar

---

## 🆚 Strategy vs State

| Strategy | State |
|----------|-------|
| **Algoritma** seçimi | **Durum** yönetimi |
| Client stratejiyi seçer | State kendini değiştirir |
| Birbirinden bağımsız | Birbirine geçiş yapar |
| Ödeme yöntemi | Sipariş durumu (Beklemede → Kargoda → Teslim) |

---

## 🆚 Strategy vs Command

| Strategy | Command |
|----------|---------|
| **Nasıl** yapılacak | **Ne** yapılacak |
| Algoritma değişimi | İşlem nesnesi |
| `sort(comparator)` | `button.onClick(command)` |
| Anlık çalışır | Saklanır, kuyruklanır, geri alınır |