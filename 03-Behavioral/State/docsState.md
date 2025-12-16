# State Pattern (Durum Deseni)

## 🎯 Ne İşe Yarar?
Bir nesnenin **iç durumuna göre davranışını değiştirmesini** sağlar. Nesne sanki **sınıfını değiştirmiş gibi** farklı davranır. `if-else` veya `switch` cehenneminden kurtarır!

---

## 💡 Neden Kullanılır?
- **Durum Bağımlı Davranış:** Aynı metod farklı durumlarda farklı şeyler yapar
- **if-else'den Kurtulma:** Her durum ayrı sınıfta, kod temiz kalır
- **Kolay Genişleme:** Yeni durum eklemek = yeni sınıf eklemek

---

## 📦 Gerçek Hayat Örneği
> ▶️ **Media Player:** Play butonuna basıyorsun:
> - Film **duruyorsa** → Oynatmaya başlar
> - Film **oynuyorsa** → Duraklatır
> 
> Aynı buton, farklı davranış! İşte State Pattern bu.

---

## 🏗️ Yapı (UML Diagram)

```
┌─────────────────────┐
│    MediaPlayer      │  ◄── Context (Bağlam)
├─────────────────────┤
│ - state: State      │
├─────────────────────┤
│ + setState(State)   │
│ + pressButton()     │──────┐
└─────────────────────┘      │
         │                   │ delegates to
         │ has-a             │
         ▼                   ▼
┌─────────────────────┐
│   <<interface>>     │
│       State         │  ◄── State Interface
├─────────────────────┤
│ + pressButton()     │
└──────────┬──────────┘
           │
     ┌─────┴─────┐
     │           │
     ▼           ▼
┌──────────┐ ┌──────────┐
│ Playing  │ │  Paused  │  ◄── Concrete States
│  State   │ │  State   │
├──────────┤ ├──────────┤
│+pressBtn │ │+pressBtn │
│ →Paused  │ │ →Playing │
└──────────┘ └──────────┘
```

---

## 💻 Kodda Nasıl Çalışır?

### 1. State Interface
Tüm durumların uygulaması gereken ortak arayüz.

```java
public interface State {
    void pressButton(MediaPlayer context);
}
```

### 2. Concrete States (Somut Durumlar)
Her durum kendi davranışını tanımlar ve bir sonraki duruma geçişi yapar.

```java
// Playing durumundayken butona basılırsa
public class PlayingState implements State {
    @Override
    public void pressButton(MediaPlayer context) {
        System.out.println("Button pressed: PAUSING the video.");
        // Change state: Now 'PausedState' is active
        context.setState(new PausedState());
    }
}

// Paused durumundayken butona basılırsa
public class PausedState implements State {
    @Override
    public void pressButton(MediaPlayer context) {
        System.out.println("Button pressed: PLAYING the video.");
        // Change state: Now 'PlayingState' is active
        context.setState(new PlayingState());
    }
}
```

### 3. Context (MediaPlayer)
Mevcut durumu tutar ve işlemi duruma devreder.

```java
public class MediaPlayer {
    private State state;

    public MediaPlayer() {
        // Initial state: Paused
        this.state = new PausedState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void pressButton() {
        // Delegate the action to the current state!
        // MediaPlayer doesn't think "What should I do now?"
        state.pressButton(this);
    }
}
```

### 4. Kullanım (Client)

```java
public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        player.pressButton();  // Paused → Playing
        player.pressButton();  // Playing → Paused
        player.pressButton();  // Paused → Playing
    }
}
```

**Çıktı:**
```
Button pressed: PLAYING the video.
Button pressed: PAUSING the video.
Button pressed: PLAYING the video.
```

---

## 🔑 Anahtar Noktalar (Sınavda Bunları Hatırla!)

| # | Nokta | Açıklama |
|---|-------|----------|
| 1 | **Context** | Durumu tutan ana sınıf (MediaPlayer) |
| 2 | **State Interface** | Tüm durumların ortak arayüzü |
| 3 | **Concrete State** | Her durum ayrı sınıf (PlayingState, PausedState) |
| 4 | **Delegation** | Context işi yapmaz, State'e devreder |
| 5 | **Geçiş** | State kendi içinde `context.setState()` ile durumu değiştirir |

---

## ⚡ State vs if-else Karşılaştırması

### ❌ if-else ile (Kötü Yol)
```java
public void pressButton() {
    if (state.equals("playing")) {
        System.out.println("Pausing...");
        state = "paused";
    } else if (state.equals("paused")) {
        System.out.println("Playing...");
        state = "playing";
    } else if (state.equals("stopped")) {
        // ...
    }
    // Yeni durum eklemek = if eklemek = spagetti kod!
}
```

### ✅ State Pattern ile (İyi Yol)
```java
public void pressButton() {
    state.pressButton(this);  // Tek satır, temiz kod!
}
```

---

## ⚡ Avantajları & Dezavantajları

| Durum | Açıklama |
|-------|----------|
| ✅ **Single Responsibility** | Her durum kendi sınıfında |
| ✅ **Open/Closed** | Yeni durum = yeni sınıf, mevcut koda dokunma |
| ✅ **if-else yok** | Temiz, okunabilir kod |
| ⚠️ **Sınıf Sayısı** | Az durum için fazla sınıf oluşturabilir |

---

## 🆚 Strategy vs State

| Özellik | Strategy | State |
|---------|----------|-------|
| Amaç | Algoritma seçimi | Durum bazlı davranış |
| Kim değiştirir? | Client seçer | State kendini değiştirir |
| Geçiş | Yok | State'ler arası geçiş var |

---

## 🎓 Sınav İpucu

Eğer hocan **"State Pattern nedir?"** diye sorarsa:

> *"Bir nesnenin **iç durumu değiştikçe davranışının da değişmesini** sağlayan **Behavioral (Davranışsal)** tasarım desenidir. Her durum ayrı sınıf olarak modellenir ve Context nesnesi işlemi o anki State'e devreder. Böylece **if-else karmaşasından** kurtuluruz."*

---

💡 **Hızlı Hatırlatma:**
- **Context** = Durumu tutar, işi devreder
- **State** = İşi yapar, geçişi yönetir
- Aynı buton, farklı sonuç → **State Pattern!**