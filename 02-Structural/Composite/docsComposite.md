# Composite Pattern (Bileşik Desen)

## 🎯 Ne İşe Yarar?
**Ağaç yapısındaki nesneleri** (tek parça ve grup) **aynı şekilde** işlemeni sağlar. Tek bir dosya ile içinde 100 dosya olan klasörü aynı metodla (`showDetails()`) çağırabilirsin.

---

## 💡 Neden Kullanılır?
- **Ağaç Yapısı:** Hiyerarşik veri yapıları için idealdir (dosya sistemi, organizasyon şeması, menüler)
- **Tekil = Grup:** Client, tekil nesne mi yoksa grup mu olduğunu bilmek zorunda değil
- **Recursion (Özyineleme):** Bir klasör içindeki tüm dosyaları otomatik olarak dolaşır

---

## 📦 Gerçek Hayat Örneği
> 📁 **Dosya Sistemi:** Bilgisayarında bir klasöre sağ tıklayıp "Özellikler" dediğinde, o klasörün içindeki tüm dosya ve alt klasörlerin toplam boyutunu görürsün. Sistem her bir parçayı tek tek dolaşır ama sen sadece "ana klasöre bak" dersin.

---

## 🏗️ Yapı (UML Diagram)

```
                    ┌───────────────────────┐
                    │  FileSystemComponent  │  ◄── Component (Ortak Arayüz)
                    │      <<abstract>>     │
                    ├───────────────────────┤
                    │ # name: String        │
                    │ + showDetails()       │
                    └───────────┬───────────┘
                                │
              ┌─────────────────┴─────────────────┐
              │                                   │
              ▼                                   ▼
      ┌───────────────┐                 ┌─────────────────┐
      │     File      │                 │     Folder      │
      │    (Leaf)     │                 │   (Composite)   │
      ├───────────────┤                 ├─────────────────┤
      │ + showDetails │                 │ - children[]    │
      └───────────────┘                 │ + addComponent  │
                                        │ + removeComponent│
                                        │ + showDetails   │
                                        └─────────────────┘
                                                │
                                                │ contains
                                                ▼
                                        FileSystemComponent
                                        (File veya Folder)
```

---

## 💻 Kodda Nasıl Çalışır?

### 1. Component (Ortak Soyut Sınıf)
Hem dosya hem klasör için ortak arayüzü tanımlar.

```java
public abstract class FileSystemComponent {
    protected String name;

    public FileSystemComponent(String name) {
        this.name = name;
    }

    // Common action for both files and folders
    public abstract void showDetails();
}
```

### 2. Leaf (Yaprak - Tekil Nesne)
En alt seviye, çocuğu olmayan nesneler. Burada `File` sınıfı.

```java
public class File extends FileSystemComponent {

    public File(String name) {
        super(name);
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}
```

### 3. Composite (Bileşik - Grup Nesnesi)
İçinde başka nesneler barındırabilen sınıf. Burada `Folder`.

```java
public class Folder extends FileSystemComponent {
    
    // A folder can contain files or other folders
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void addComponent(FileSystemComponent component) {
        children.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        children.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        
        // RECURSION: Call the same method for each child component
        for (FileSystemComponent component : children) {
            component.showDetails();
        }
    }
}
```

### 4. Kullanım (Client)

```java
public class Main {
    public static void main(String[] args) {
        // Create files (Leaf)
        FileSystemComponent file1 = new File("Notes.txt");
        FileSystemComponent file2 = new File("Image.jpg");
        FileSystemComponent file3 = new File("Homework.docx");

        // Create folders (Composite)
        Folder mainFolder = new Folder("MyDocuments");
        Folder subFolder = new Folder("LectureNotes");

        // Build the tree structure
        subFolder.addComponent(file1);
        subFolder.addComponent(file3);

        mainFolder.addComponent(file2);
        mainFolder.addComponent(subFolder);  // Folder inside folder!

        // Just call on the top-level folder, it handles the rest
        mainFolder.showDetails();
    }
}
```

**Çıktı:**
```
Folder: MyDocuments
File: Image.jpg
Folder: LectureNotes
File: Notes.txt
File: Homework.docx
```

---

## 🔑 Anahtar Noktalar (Sınavda Bunları Hatırla!)

| # | Nokta | Açıklama |
|---|-------|----------|
| 1 | **3 Rol** | Component (soyut), Leaf (yaprak), Composite (bileşik) |
| 2 | **Ağaç Yapısı** | Part-Whole (parça-bütün) ilişkisi kurar |
| 3 | **Recursion** | Composite, children üzerinde döngüyle aynı metodu çağırır |
| 4 | **Tek Arayüz** | Client hem Leaf hem Composite'i aynı şekilde kullanır |
| 5 | **List<Component>** | Composite içinde Component listesi tutar (hem Leaf hem Composite alabilir) |

---

## ⚡ Avantajları & Dezavantajları

| Durum | Açıklama |
|-------|----------|
| ✅ **Basitlik** | Client, tekil mi grup mu ayırt etmeden çalışır |
| ✅ **Esneklik** | Yeni Leaf veya Composite tipi eklemek kolay |
| ✅ **Recursion** | Karmaşık ağaç yapılarını kolayca dolaşır |
| ⚠️ **Genel Arayüz** | Bazı metodlar Leaf için anlamsız olabilir (örn: `addComponent`) |

---

## 🆚 Leaf vs Composite

| Özellik | Leaf (File) | Composite (Folder) |
|---------|-------------|-------------------|
| Çocuk var mı? | ❌ Hayır | ✅ Evet |
| `add/remove` | Yok | Var |
| `showDetails()` | Sadece kendini gösterir | Kendini + çocukları gösterir |

---

## 🎓 Sınav İpucu

Eğer hocan **"Composite Pattern nedir?"** diye sorarsa:

> *"Nesneleri **ağaç yapısında** düzenleyerek, **tekil nesneler (Leaf)** ile **grupları (Composite)** aynı arayüzle işlemeye yarayan **Structural (Yapısal)** tasarım desenidir. Örnek: Dosya sistemi - bir dosya ve bir klasör aynı `showDetails()` metoduyla çağrılır."*

---

💡 **Hızlı Hatırlatma:** 
- **Leaf** = Çocuğu yok, iş yapan en küçük birim
- **Composite** = Çocukları var, onları yönetir ve işi onlara devreder