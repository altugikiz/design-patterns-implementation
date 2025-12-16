# Iterator Pattern (Yineleyici Deseni)

## 🎯 Ne İşe Yarar?
Bir koleksiyonun (liste, array, vb.) **içindeki elemanları tek tek dolaşmak** için kullanılır. Koleksiyonun iç yapısını bilmene gerek kalmadan elemanları sırayla gezebilirsin.

---

## 💡 Neden Kullanılır?
- **Tek Sorumluluk Prensibi (SRP):** Koleksiyon sadece veri tutar, gezinme işi Iterator'a aittir.
- **İç Yapıyı Gizler:** Array mi, List mi, Tree mi? Önemli değil, hepsi aynı şekilde gezilir.
- **Tek Bir Arayüz:** Farklı koleksiyonlar için aynı gezinme yöntemini kullanırsın.

---

## 📦 Gerçek Hayat Örneği
> 🍽️ **Restoran Örneği:** Bir restoranda garson menüyü okurken, menünün kitap mı yoksa tablet mi olduğu önemli değildir. Garson sadece "sonraki yemek" diye ilerler. İşte Iterator bu mantıkla çalışır!

---

## 🏗️ Yapı (UML Diagram)

```
┌─────────────────┐         ┌─────────────────┐
│   <<interface>> │         │   <<interface>> │
│    Container    │         │     Iterator    │
├─────────────────┤         ├─────────────────┤
│ +getIterator()  │         │ +hasNext()      │
└────────┬────────┘         │ +next()         │
         │                  └────────┬────────┘
         │ implements                │ implements
         ▼                           ▼
┌─────────────────┐         ┌─────────────────┐
│ NameRepository  │────────>│  NameIterator   │
├─────────────────┤ creates ├─────────────────┤
│ -names[]        │ (inner) │ -index          │
│ +getIterator()  │         │ +hasNext()      │
└─────────────────┘         │ +next()         │
                            └─────────────────┘
```

---

## 💻 Kodda Nasıl Çalışır?

### 1. Arayüzler (Interfaces)
Önce standart arayüzlerimizi tanımlıyoruz.

```java
// Gezgin Arayüzü
public interface Iterator {
    boolean hasNext(); // Daha eleman var mı?
    Object next();     // Bir sonraki elemanı getir
}

// Koleksiyon Arayüzü
public interface Container {
    Iterator getIterator(); // Bana bir gezgin ver
}
```

### 2. Gerçek Koleksiyon ve Inner Class
`NameRepository` sınıfı veriyi tutar ve içinde Iterator'ı implement eden özel bir sınıf barındırır.

```java
public class NameRepository implements Container {
    // Veri kaynağımız (Veritabanı, List veya Array olabilir)
    public String[] names = { "Altug", "Daryl", "Negan", "Rick" };
    
    @Override
    public Iterator getIterator() {
        return new NameIterator(); // İç sınıf döndürür
    }
    
    // İç sınıf (Inner Class) - Sadece NameRepository'nin içini bilir
    private class NameIterator implements Iterator {
        int index;
        
        @Override
        public boolean hasNext() {
            // Eğer index dizinin uzunluğundan küçükse daha eleman var demektir
            return index < names.length;
        }
        
        @Override
        public Object next() {
            if (this.hasNext()) {
                return names[index++]; // Elemanı döndür ve index'i 1 arttır
            }
            return null;
        }
    }
}
```

### 3. Kullanım (Client)
Kullanıcı, arka planda array mi yoksa list mi olduğunu bilmez. Sadece `hasNext()` ve `next()` kullanır.

```java
public class Main {
    public static void main(String[] args) {
        NameRepository repo = new NameRepository();
        
        // Gezgini alıyoruz
        Iterator iter = repo.getIterator();
        
        // Gezgin bitene kadar dön
        while (iter.hasNext()) {
            String name = (String) iter.next();
            System.out.println("Name: " + name);
        }
    }
}
```

**Çıktı:**
```
Name: Altug
Name: Daryl
Name: Negan
Name: Rick
```

---

## 🔑 Anahtar Noktalar (Sınavda Bunları Hatırla!)

| # | Nokta | Açıklama |
|---|-------|----------|
| 1 | **İki Temel Metot** | `hasNext()` (var mı?) ve `next()` (getir) |
| 2 | **İç Sınıf (Inner Class)** | Iterator genelde koleksiyonun içinde private inner class olarak yazılır |
| 3 | **Index Takibi** | Iterator, nerede kaldığını (state) kendi içinde tutar |
| 4 | **Soyutlama** | Client, array mi list mi bilmez |
| 5 | **Java Karşılığı** | `java.util.Iterator` (Biz mantığı anlamak için sıfırdan yazdık) |

---

## ⚡ Avantajları & Dezavantajları

| Durum | Açıklama |
|-------|----------|
| ✅ **Gizlilik** | Koleksiyonun iç yapısını dışarıdan gizler (Encapsulation) |
| ✅ **Esneklik** | Aynı koleksiyonu farklı şekillerde gezebilirsin (ters, 2'şer atlayarak vb.) |
| ✅ **Temiz Kod** | Single Responsibility Principle'a uygundur |
| ⚠️ **Maliyet** | Basit koleksiyonlar için gereksiz karmaşıklık yaratabilir |

---

## 🎓 Sınav İpucu

Eğer hocan **"Iterator Pattern nedir?"** diye sorarsa:

> *"Bir koleksiyonun elemanlarını, **koleksiyonun iç yapısını (array, list vb.) bilmeden** sırayla dolaşmak için kullanılan **Behavioral (Davranışsal)** tasarım desenidir. `hasNext()` ve `next()` metodlarıyla gezinme sağlar."*

---

💡 **Hızlı Hatırlatma:** Java'daki `for-each` döngüsünü düşün. O aslında arka planda bu deseni kullanır!