# 🔌 Adapter Pattern

## Tek Cümlede
> **Uyumsuz iki arayüzü birbirine bağlayan "çevirmen" sınıf.**

---

## 🎯 Ne Zaman Kullanılır?
- Eski bir sistemi (legacy) yeni kodla entegre etmek istediğinde
- 3rd party bir kütüphane senin arayüzünle uyumsuz olduğunda
- Farklı formatlardaki verileri (XML ↔ JSON gibi) dönüştürmen gerektiğinde

---

## 🧩 Yapı (UML)

```
┌─────────────────┐         ┌─────────────────┐
│   IJsonParser   │◄────────│  XmlToJsonAdapter│
│   (Target)      │         │    (Adapter)     │
├─────────────────┤         ├─────────────────┤
│ + parseJson()   │         │ - xmlReader     │
└─────────────────┘         │ + parseJson()   │
                            └────────┬────────┘
                                     │ uses
                                     ▼
                            ┌─────────────────┐
                            │  OldXmlReader   │
                            │   (Adaptee)     │
                            ├─────────────────┤
                            │ + readXml()     │
                            └─────────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **Target** | `IJsonParser` | Client'ın beklediği arayüz |
| **Adaptee** | `OldXmlReader` | Uyumsuz eski sistem |
| **Adapter** | `XmlToJsonAdapter` | İkisini bağlayan çevirmen |
| **Client** | `Main` | Target arayüzünü kullanan kod |

---

## 🔥 Bu Projedeki Senaryo

```
Uygulama (JSON istiyor) ──► Adapter ──► Eski Sistem (XML anlıyor)
```

1. **Problem:** Yeni uygulama JSON ile çalışıyor, eski sistem sadece XML anlıyor
2. **Çözüm:** Adapter, JSON'u alıp XML'e çeviriyor ve eski sisteme gönderiyor

---

## 💻 Kod Akışı

```java
// Client JSON gönderiyor
adapter.parseJson("{ \"customer\": \"Altug\" }");

// Adapter içinde:
// 1. JSON alınır
// 2. XML'e çevrilir: "<xml>{ \"customer\": \"Altug\" }</xml>"
// 3. Eski sisteme gönderilir: xmlReader.readXml(convertedXml)
```

---

## ⚡ Hızlı Hatırlatma

```
┌──────────┐      ┌──────────┐      ┌──────────┐
│  Client  │ ───► │ Adapter  │ ───► │ Adaptee  │
│  (Yeni)  │      │(Çevirmen)│      │  (Eski)  │
└──────────┘      └──────────┘      └──────────┘
     │                 │                  │
   JSON            Dönüştür             XML
```

---

## 🆚 Gerçek Hayat Analogisi

**Priz Adaptörü** 🔌
- Sen: Türk fişli laptop (Client)
- Adaptör: Seyahat adaptörü (Adapter)  
- Priz: Amerikan prizi (Adaptee)

Laptop değişmez, priz değişmez → Adaptör ikisini bağlar!

---

## ✅ Avantajları
- Single Responsibility: Dönüşüm mantığı ayrı sınıfta
- Open/Closed: Eski kodu değiştirmeden yeni sistemle entegre
- Eski sistemler çöpe gitmez, yeniden kullanılır

## ❌ Dezavantajları
- Ekstra sınıf = Ekstra karmaşıklık
- Bazen doğrudan refactor daha mantıklı olabilir