![Java](https://img.shields.io/badge/Java-17-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-21-orange)
![Build](https://img.shields.io/badge/build-Maven-blue)
[![CI](https://github.com/maxim618/PlayMy/actions/workflows/ci.yml/badge.svg)](https://github.com/maxim618/PlayMy/actions/workflows/ci.yml)
![Version](https://img.shields.io/badge/version-1.0.0-brightgreen)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

# PlayMy - JavaFX Audio Player

<p align="center">
  <img src="docs/screenshot.png" width="800"/>
</p>

Desktop аудиоплеер, реализованный на **Java 17 + JavaFX 21**  
Проект демонстрирует чистую архитектуру, разделение слоёв и production-сборку через `jlink + jpackage`.

---

##  Release

**Current version:** `1.0.0`

Скачать portable Windows build:
 https://github.com/maxim618/PlayMy/releases

### Distribution includes:
- Native Windows launcher
- Custom Java runtime (jlink)
- App-image packaging (jpackage)
- Versioned artifact: `PlayMy-1.0.0-windows.zip`

---

##  Features

- Воспроизведение **MP3 / WAV**
- Play / Pause / Stop
- Перемотка
- Регулировка громкости
- Управление плейлистом
- Отображение метаданных (название, автор, альбом)
- Поддержка обложек (если присутствуют в файле)

---

## Architecture

Проект построен по принципу разделения ответственности между слоями.

### UI Layer (JavaFX)
- FXML + контроллеры
- Только взаимодействие с пользователем
- Без бизнес-логики

### Player Logic
- Инкапсуляция `Media` и `MediaPlayer`
- Управление состоянием воспроизведения
- Обработка смены треков

### Playlist Management
- Управление списком треков
- Навигация
- Хранение текущего состояния

### Metadata Handling
- Чтение метаданных аудиофайлов
- Работа с embedded обложками

---

##  Tech Stack

- Java 17
- JavaFX 21 (external modules)
- Maven
- jlink (custom runtime)
- jpackage (native packaging)
- GitHub Actions (CI)

---

##  Development

Run in development mode:

mvn clean javafx:run

## License

- MIT License