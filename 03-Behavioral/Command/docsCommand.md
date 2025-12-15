# 🎮 Command Pattern

## Tek Cümlede
> **İşlemi (request) bir nesneye dönüştürüp, parametre olarak geçir, sıraya koy veya geri al.**

---

## 🎯 Ne Zaman Kullanılır?
- **Undo/Redo** özelliği eklemek istediğinde
- İşlemleri **kuyruklamak** (queue) veya **loglama** yapmak istediğinde
- **Uzaktan kumanda** gibi düğme-işlem bağlantısı kurulacağında
- İşlemi yapan (Invoker) ile işi yapan (Receiver) arasını ayırmak istediğinde

---

## 🧩 Yapı (UML)

```
┌─────────────────┐       ┌─────────────────┐
│  RemoteControl  │       │     Command     │
│    (Invoker)    │──────►│   (Interface)   │
├─────────────────┤       ├─────────────────┤
│ - lastCommand   │       │ + execute()     │
│ + pressButton() │       │ + undo()        │
│ + pressUndo()   │       └────────▲────────┘
└─────────────────┘                │
                                   │ implements
                    ┌──────────────┴──────────────┐
                    │                             │
            ┌───────┴───────┐             ┌───────┴───────┐
            │LightOnCommand │             │LightOffCommand│
            ├───────────────┤             ├───────────────┤
            │ - light       │             │ - light       │
            │ + execute()   │             │ + execute()   │
            │ + undo()      │             │ + undo()      │
            └───────┬───────┘             └───────┬───────┘
                    │                             │
                    └──────────────┬──────────────┘
                                   │ uses
                                   ▼
                           ┌───────────────┐
                           │     Light     │
                           │  (Receiver)   │
                           ├───────────────┤
                           │ + turnOn()    │
                           │ + turnOff()   │
                           └───────────────┘
```

---

## 🎭 Roller

| Rol | Bu Projede | Görevi |
|-----|-----------|--------|
| **Command** | `Command` | İşlem arayüzü (execute + undo) |
| **ConcreteCommand** | `LightOnCommand`, `LightOffCommand` | Gerçek komut sınıfları |
| **Invoker** | `RemoteControl` | Komutu tetikleyen (düğmeye basan) |
| **Receiver** | `Light` | İşi gerçekten yapan (lamba) |
| **Client** | `Main` | Her şeyi bağlayan kod |

---

## 🔥 Bu Projedeki Senaryo

```
Kumanda (Invoker) ──► Komut Nesnesi ──► Lamba (Receiver)
     │                     │                   │
 pressButton()         execute()           turnOn()
 pressUndo()           undo()              turnOff()
```

**Kilit Nokta:** Kumanda lambanın nasıl çalıştığını bilmiyor! Sadece komutun `execute()` ve `undo()` metodlarını çağırıyor.

---

## 💻 Kod Akışı

```java
// 1. Receiver (İşi yapacak olan)
Light light = new Light();

// 2. Command (İşlem nesnesi - Receiver'ı içine alır)
Command lightOn = new LightOnCommand(light);

// 3. Invoker (Tetikleyici - Komutu içine alır)
RemoteControl remote = new RemoteControl();
remote.setCommand(lightOn);

// 4. Çalıştır!
remote.pressButton();  // → lightOn.execute() → light.turnOn()
remote.pressUndo();    // → lightOn.undo()    → light.turnOff()
```

---

## ⚡ Hızlı Hatırlatma

```
┌────────────┐    setCommand()    ┌────────────┐    execute()    ┌────────────┐
│   Remote   │ ──────────────────►│  Command   │ ──────────────► │   Light    │
│  (Invoker) │                    │  (Object)  │                 │ (Receiver) │
└────────────┘                    └────────────┘                 └────────────┘
      │                                 │
 pressButton()                     Komut bilir:
 pressUndo()                       - Kimi çağıracak (Light)
                                   - Ne yapacak (turnOn/turnOff)
```

---

## 🔄 Undo Nasıl Çalışıyor?

```java
// LightOnCommand
public void execute() { light.turnOn(); }   // İleri git
public void undo() { light.turnOff(); }     // Geri al (tersini yap)

// LightOffCommand  
public void execute() { light.turnOff(); }  // İleri git
public void undo() { light.turnOn(); }      // Geri al (tersini yap)
```

**Her komut kendi tersini bilir!**

---

## 🆚 Gerçek Hayat Analogisi

**Restoran Siparişi** 🍽️
- **Müşteri (Client):** Sipariş veren
- **Garson (Invoker):** Siparişi alan ve mutfağa ileten
- **Sipariş Fişi (Command):** Yazılı komut nesnesi
- **Aşçı (Receiver):** İşi gerçekten yapan

Garson yemek yapmayı bilmez → Sadece fişi mutfağa götürür!

---

## 🎯 Command Pattern'in Süper Gücü

```java
// Komutları listeye ekle (Queue)
List<Command> history = new ArrayList<>();
history.add(lightOnCommand);
history.add(fanOnCommand);
history.add(doorLockCommand);

// Hepsini sırayla çalıştır (Macro)
for (Command cmd : history) {
    cmd.execute();
}

// Hepsini geri al (Undo All)
for (int i = history.size() - 1; i >= 0; i--) {
    history.get(i).undo();
}
```

---

## ✅ Avantajları
- **Undo/Redo** kolayca eklenir
- Invoker ve Receiver birbirini bilmez (Loose Coupling)
- Komutlar **loglanabilir**, **kuyruklanabilir**, **serialize** edilebilir
- Yeni komut eklemek kolay (Open/Closed)

## ❌ Dezavantajları
- Her işlem için ayrı sınıf = Çok fazla sınıf
- Basit işlemler için overkill olabilir

---

## 🆚 Strategy vs Command

| Strategy | Command |
|----------|---------|
| **Nasıl** yapılacağını değiştirir | **Ne** yapılacağını saklar |
| Algoritma seçimi | İşlem nesnesi |
| `sort(comparator)` | `button.setCommand(cmd)` |
| Anlık kullanım | Saklama, kuyruklama, geri alma |