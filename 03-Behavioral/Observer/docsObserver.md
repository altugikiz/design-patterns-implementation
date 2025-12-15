# 🔔 Observer Pattern

## Tek Cümlede
> **Bir nesnede değişiklik olduğunda, bağlı tüm nesnelere otomatik haber veren "abone-yayıncı" sistemi.**

---

## 🎯 Ne Zaman Kullanılır?
- Bir nesne değiştiğinde diğerlerinin **otomatik güncellenmesi** gerektiğinde
- **Bildirim sistemi** (YouTube, Twitter, Newsletter) kurulacağında
- Nesneler arası **gevşek bağlantı** (loose coupling) istediğinde
- **Event-driven** mimari kurarken

---

## 🧩 Yapı (UML)

```
┌─────────────────┐              ┌─────────────────┐
│     Subject     │              │    Observer     │
│   (Interface)   │              │   (Interface)   │
├─────────────────┤              ├─────────────────┤
│ + subscribe()   │              │ + update()      │
│ + unsubscribe() │              └────────▲────────┘
│ + notify()      │                       │
└────────▲────────┘                       │ implements
         │                                │
         │ implements              ┌──────┴──────┐
         │                         │  Subscriber │
┌────────┴────────┐                │ (Concrete)  │
│  YoutubeChannel │                ├─────────────┤
│   (Concrete)    │                │ - name      │
├─────────────────┤                │ + update()  │
│ - subscribers[] │◄───────────────└─────────────┘
│ - lastVideo     │    registers
│ + uploadVideo() │
│ + notify()      │
└─────────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **Subject** | `Subject` | Yayıncı arayüzü |
| **ConcreteSubject** | `YoutubeChannel` | Gerçek yayıncı (kanal) |
| **Observer** | `Observer` | Abone arayüzü |
| **ConcreteObserver** | `Subscriber` | Gerçek abone (kullanıcı) |

---

## 🔥 Bu Projedeki Senaryo (YouTube)

```
┌──────────────────┐
│  YoutubeChannel  │ ──── uploadVideo() ────┐
│ "The Walking Dead"│                        │
└────────┬─────────┘                         ▼
         │                          notifySubscribers()
         │                                   │
    subscribers[]                            │
         │              ┌────────────────────┼────────────────────┐
         ▼              ▼                    ▼                    ▼
   ┌──────────┐   ┌──────────┐         ┌──────────┐         ┌──────────┐
   │  Daryl   │   │   Rick   │         │  Negan   │         │   ...    │
   │ update() │   │ update() │         │ update() │         │ update() │
   └──────────┘   └──────────┘         └──────────┘         └──────────┘
```

**Kilit Nokta:** Kanal abonelerin kim olduğunu bilmiyor! Sadece `update()` metodlarını çağırıyor.

---

## 💻 Kod Akışı

```java
// 1. Subject (Yayıncı) oluştur
YoutubeChannel channel = new YoutubeChannel("The Walking Dead");

// 2. Observer'lar (Aboneler) oluştur
Subscriber user1 = new Subscriber("Daryl Dixon");
Subscriber user2 = new Subscriber("Rick Grimes");

// 3. Aboneleri kaydet
channel.subscribe(user1);  // → subscribers.add(user1)
channel.subscribe(user2);  // → subscribers.add(user2)

// 4. Video yükle → Otomatik bildirim!
channel.uploadVideo("Who killed Glenn?");
// → notifySubscribers()
//   → user1.update("Who killed Glenn?")
//   → user2.update("Who killed Glenn?")
```

---

## ⚡ Hızlı Hatırlatma

```
        SUBJECT                           OBSERVERS
   ┌───────────────┐                  ┌───────────────┐
   │    Channel    │   notify() ───►  │  Subscriber 1 │  "Hey, new video!"
   │               │   ─────────────► │  Subscriber 2 │  "Hey, new video!"
   │  subscribers[]│   ─────────────► │  Subscriber 3 │  "Hey, new video!"
   └───────────────┘                  └───────────────┘
         │
    uploadVideo()
    ─────────────
    1. this.lastVideo = title
    2. notifySubscribers()  ← Zili çaldır!
```

---

## 🔄 Subscribe / Unsubscribe Akışı

```java
// Abone ol
channel.subscribe(user);
// → subscribers.add(user)
// → "New subscriber added!"

// Abonelikten çık
channel.unsubscribe(user);
// → subscribers.remove(user)
// → "A subscriber has left :("

// Artık bildirim almaz!
channel.uploadVideo("New Episode");
// → user.update() ÇAĞRILMAZ!
```

---

## 🆚 Gerçek Hayat Analogisi

**YouTube Abonelik Sistemi** 🔔
- **Kanal (Subject):** Video yükler, abonelere haber verir
- **Abone (Observer):** Bildirim alır, izlemeye başlar
- **Zil butonu:** `subscribe()` metodu
- **Bildirim:** `update()` metodu

Kanal her abonenin telefonuna tek tek mesaj atmıyor → Sadece "yeni video var" diyor, sistem herkese iletiyor!

---

## 🎯 Push vs Pull Model

```java
// PUSH (Bu projede kullanılan)
// Subject veriyi Observer'a gönderiyor
void update(String videoTitle) {
    System.out.println("New video: " + videoTitle);
}

// PULL (Alternatif)
// Observer veriyi Subject'ten çekiyor
void update(Subject subject) {
    String title = subject.getLastVideo();
    System.out.println("New video: " + title);
}
```

---

## ✅ Avantajları
- **Loose Coupling:** Subject, Observer'ların detaylarını bilmez
- **Open/Closed:** Yeni observer eklemek kolay
- **Broadcast:** Bir değişiklik herkese ulaşır
- Runtime'da abone ekle/çıkar

## ❌ Dezavantajları
- Bildirim sırası garanti değil
- Çok fazla observer = Performans sorunu
- Döngüsel bağımlılık riski (A → B → A)
- Memory leak (unsubscribe unutulursa)

---

## 🆚 Observer vs Pub/Sub

| Observer | Pub/Sub |
|----------|---------|
| Subject, Observer'ları biliyor | Broker (aracı) var |
| Doğrudan bağlantı | Tamamen ayrık |
| `subject.subscribe(observer)` | `broker.subscribe("topic", handler)` |
| Senkron | Genellikle asenkron |

---

## 🔑 Kritik Kod

```java
// YoutubeChannel.java - En önemli metod
public void notifySubscribers() {
    for (Observer observer : subscribers) {
        observer.update(lastVideoTitle);  // ← HERKESİ TEK TEK BİLGİLENDİR
    }
}
```

**Bu döngü Observer Pattern'in kalbi!**