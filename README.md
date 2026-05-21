# 🐍 Java Snake Game

Klasik Yılan oyununun Java Swing ile geliştirilmiş masaüstü versiyonu.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)

---

## 📸 Oyun Görünümü

| Oyun İçi | Oyun Bitti |
|:---------:|:----------:|
| ![Oyun İçi](screenshots/screenshot_gameplay.png) | ![Oyun Bitti](screenshots/screenshot_game_over.png) |

---

## 🎮 Nasıl Oynanır?

| Tuş | Eylem |
|-----|-------|
| ⬅️ Sol Ok | Yılanı sola çevir |
| ➡️ Sağ Ok | Yılanı sağa çevir |
| ⬆️ Yukarı Ok | Yılanı yukarı çevir |
| ⬇️ Aşağı Ok | Yılanı aşağı çevir |
| `R` | Oyunu yeniden başlat |

**Kurallar:**
- Yılan her yem yediğinde skor **+10** artar ve kuyruğu uzar.
- Yılan duvara veya kendi vücuduna çarparsa oyun biter.
- Ters yönde hareket edilemez (örneğin sağa giderken sola dönülemez).

---

## 🏗️ Proje Yapısı

```
java-snake-game/
│
├── MySnakeGame.java   # Giriş noktası — pencereyi ve yılanı başlatır
├── AnaPencere.java    # Ana uygulama penceresi (Singleton JFrame)
├── Yilan.java         # Oyun motoru — hareket, çarpışma, skor, render
├── Kutu.java          # Yılanın her bir parçasını temsil eden bileşen
├── Yem.java           # Rastgele konumlandırılan yem nesnesi
└── Yon.java           # Yön sabitlerini tutan enum (SOL, SAG, YUKARI, ASAGI)
```

### Sınıf Açıklamaları

- **`MySnakeGame`** — `main` metodu burada. `AnaPencere` singleton'ını alır ve `Yilan` bileşenini pencereye ekleyerek oyunu başlatır.
- **`AnaPencere`** — Singleton tasarım deseniyle oluşturulan `JFrame` penceresi.
- **`Yilan`** — Oyunun kalbi. `KeyListener` ile klavye girişlerini dinler, `javax.swing.Timer` (150ms) ile oyun döngüsünü yönetir. Hareket, yem tüketimi, kuyruk büyümesi, çarpışma kontrolü ve `paint()` ile özel çizim işlemleri burada gerçekleşir.
- **`Kutu`** — Hem yılanın başını hem de her kuyruk parçasını temsil eder. Konum ve yön bilgisi taşır.
- **`Yem`** — Izgara hizalamalı (20px) rastgele konumda beliren yem. Yılan parçalarıyla çakışmayacak şekilde konumlandırılır.
- **`Yon`** — `SOL`, `SAG`, `YUKARI`, `ASAGI` değerlerini içeren enum.

---

## ⚙️ Gereksinimler

- **Java 8** veya daha yüksek bir sürüm

---

## 🚀 Kurulum ve Çalıştırma

### 1. Repoyu klonlayın

```bash
git clone https://github.com/magcabay/java-snake-game.git
cd java-snake-game
```

### 2. Derleyin

```bash
javac *.java
```

### 3. Çalıştırın

```bash
java MySnakeGame
```

---

## 🛠️ Teknik Detaylar

- **GUI Framework:** Java Swing (`JLabel`, `JFrame`)
- **Oyun Döngüsü:** `javax.swing.Timer` — 150ms aralıklarla tetiklenir (~6.7 FPS)
- **Çizim:** `Graphics2D` ile özel `paint()` override — oyun alanı çerçevesi, skor yazısı ve oyun sonu ekranı
- **Izgara Sistemi:** 20×20 piksel hücreler
- **Sınır Algılama:** 10px iç kenarlık ile duvar çarpışması
- **Tasarım Deseni:** `AnaPencere` için Singleton
