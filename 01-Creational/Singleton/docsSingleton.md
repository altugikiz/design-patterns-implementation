# 🔒 Singleton Pattern

## Tek Cümlede
> **Bir sınıftan sadece TEK BİR nesne oluştur ve her yerden aynı nesneye eriş.**

---

## 🎯 Ne Zaman Kullanılır?
- **Veritabanı bağlantısı** - Tek connection pool
- **Logger** - Tek log dosyası
- **Configuration** - Tek ayar nesnesi
- **Cache** - Tek cache instance
- Kaynak paylaşımı gerektiğinde

---

## 🧩 Yapı

```
┌─────────────────────────────────────────┐
│              Singleton                  │
├─────────────────────────────────────────┤
│ - static instance: Singleton            │  ← Kendisini tutar
│ - data: String                          │
├─────────────────────────────────────────┤
│ - Singleton(data)                       │  ← PRIVATE constructor!
│ + static getInstance(data): Singleton   │  ← Tek giriş kapısı
│ + getData(): String                     │
└─────────────────────────────────────────┘
```

---

## 🔥 3 Kritik Adım

```java
public class Singleton {
    
    // ADIM 1: Static instance (Kendisini tutuyor)
    private static Singleton instance;
    
    // ADIM 2: PRIVATE Constructor (Dışarıdan new engellenmiş!)
    private Singleton() { }
    
    // ADIM 3: Global Access Point (Tek giriş kapısı)
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

---

## 💻 Kod Akışı

```java
// İlk çağrı - Nesne OLUŞTURULUR
Singleton s1 = Singleton.getInstance("Initial Data");
// → "Singleton instance is being created for the first time..."
// → s1.hashCode() = 12345

// İkinci çağrı - AYNI nesne döner
Singleton s2 = Singleton.getInstance("Different Data");
// → (Hiçbir şey yazmaz, çünkü zaten var)
// → s2.hashCode() = 12345  ← AYNI!

// Üçüncü çağrı - Yine AYNI nesne
Singleton s3 = Singleton.getInstance("Another Data");
// → s3.hashCode() = 12345  ← AYNI!

// KANIT:
System.out.println(s1 == s2);  // true
System.out.println(s2 == s3);  // true
```

---

## ⚡ Hızlı Hatırlatma

```
                     ┌─────────────────────────────┐
   getInstance() ───►│         SINGLETON           │
   getInstance() ───►│                             │◄─── Tek instance
   getInstance() ───►│  instance = new Singleton() │
   getInstance() ───►│                             │
                     └─────────────────────────────┘
                                  │
                     Herkes AYNI nesneyi alır!
```

---

## 🚫 new Singleton() YAPAMAZSIN!

```java
// Bu HATA verir! ❌
Singleton s = new Singleton();
// Error: Singleton() has private access

// Bu DOĞRU! ✅
Singleton s = Singleton.getInstance();
```

**Neden?** Constructor `private` → Sadece sınıfın içinden çağrılabilir!

---

## 🧵 Thread-Safe Singleton (Bu Projede)

```java
public static Singleton getInstance(String data) {
    synchronized(Singleton.class) {  // ← Kilit koy!
        if (instance == null) {
            instance = new Singleton(data);
        }
    }
    return instance;
}
```

**Problem:** İki thread aynı anda `getInstance()` çağırırsa?
**Çözüm:** `synchronized` ile sadece biri girebilir!

---

## ⚡ Double-Checked Locking (Daha Performanslı)

```java
public static Singleton getInstance() {
    if (instance == null) {              // 1. Kontrol (kilitsiz)
        synchronized(Singleton.class) {
            if (instance == null) {      // 2. Kontrol (kilitli)
                instance = new Singleton();
            }
        }
    }
    return instance;
}
```

**Neden 2 kontrol?**
- İlk `if`: Her seferinde lock maliyetinden kaçın
- İkinci `if`: İki thread aynı anda geçerse güvenlik

---

## 🆚 Gerçek Hayat Analogisi

**Devlet Başkanı** 🏛️
- Ülkede sadece TEK bir başkan olabilir
- Herkes "Başkan kim?" diye sorduğunda AYNI kişi gelir
- Yeni başkan "oluşturmak" için seçim gerekir (kontrollü erişim)

**Yazıcı Kuyruğu** 🖨️
- Ofiste tek bir yazıcı var
- Herkes aynı kuyruğa belge gönderiyor
- `PrinterQueue.getInstance()` → Tek kuyruk

---

## 🔑 Kritik Noktalar

| Özellik | Amaç |
|---------|------|
| `private static instance` | Tek nesneyi saklar |
| `private constructor` | Dışarıdan `new` engeller |
| `public static getInstance()` | Tek erişim noktası |
| `synchronized` | Thread güvenliği |

---

## ✅ Avantajları
- **Tek instance garantisi**
- **Global erişim noktası**
- **Lazy initialization** (İlk çağrıda oluşur)
- **Kaynak tasarrufu** (Tek DB connection vs. 1000 tane)

## ❌ Dezavantajları
- **Unit test zorluğu** (Mock'lamak zor)
- **Hidden dependency** (Bağımlılık gizli)
- **Global state** (Anti-pattern sayılabilir)
- **Single Responsibility ihlali** (Hem iş yapıyor hem kendini yönetiyor)

---

## 🆚 Singleton vs Static Class

| Singleton | Static Class |
|-----------|--------------|
| Instance var | Instance yok |
| Interface implement edebilir | Edemez |
| Lazy loading | Eager loading |
| Polymorphism destekler | Desteklemez |
| Test edilebilir (DI ile) | Test zor |

---

## 🔥 Singleton Implementasyon Türleri

```java
// 1. LAZY (Bu projede) - İlk çağrıda oluşur
private static Singleton instance;
public static Singleton getInstance() {
    if (instance == null) instance = new Singleton();
    return instance;
}

// 2. EAGER - Sınıf yüklenince oluşur
private static final Singleton instance = new Singleton();
public static Singleton getInstance() {
    return instance;
}

// 3. ENUM (En güvenli - Java'ya özel)
public enum Singleton {
    INSTANCE;
    public void doSomething() { }
}
```

---

## ⚠️ Uyarı

Singleton **anti-pattern** olarak da görülebilir çünkü:
- Global state yaratır
- Test edilmesi zordur
- Bağımlılıkları gizler

**Alternatif:** Dependency Injection ile tek instance yönetimi