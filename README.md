![Java](https://img.shields.io/badge/Java-17-orange)
![JavaFX](https://img.shields.io/badge/JavaFX-21-orange)
![Build](https://img.shields.io/badge/build-Maven-blue)
[![CI](https://github.com/maxim618/AudioPlayer/actions/workflows/ci.yml/badge.svg)](https://github.com/maxim618/AudioPlayer/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

# PLAYMY - JavaFX Audio Player

Простой аудиоплеер на **JavaFX**.  
Поддерживает воспроизведение треков и управление плейлистом.

##  Возможности
- Воспроизведение аудиофайлов (**MP3, WAV**)
- Управление воспроизведением: **Play / Pause / Stop**
- Перемотка и выбор треков
- Регулировка громкости
- Отображение информации о треке (название, артист, альбом)
- Поддержка обложек (если есть в файле)

### Tech Stack

- Java 17
- JavaFX 21 (used with Java 17 runtime)
- Maven
## Architecture

Проект построен по принципу разделения ответственности между слоями UI, логики и данных.

### Основные компоненты

- **UI layer (JavaFX)**
    - Отвечает за отображение интерфейса и обработку пользовательских действий
    - Использует FXML и контроллеры
    - Не содержит бизнес-логики воспроизведения

- **Player logic**
    - Инкапсулирует работу с `Media` и `MediaPlayer`
    - Управляет состоянием воспроизведения (play / pause / stop)
    - Обрабатывает смену треков и перемотку

- **Playlist management**
    - Управление списком треков
    - Навигация по плейлисту
    - Хранение информации о текущем треке

- **Metadata handling**
    - Чтение метаданных аудиофайлов (название, артист, альбом)
    - Работа с обложками, если они присутствуют в файле

### Architectural decisions

- JavaFX используется как UI-фреймворк из-за нативной поддержки мультимедиа
- JavaFX 21 подключён как внешние модули и используется с Java 17 runtime
- Логика плеера отделена от UI для упрощения тестирования и расширения функциональности
- Maven применяется как инструмент сборки и управления зависимостями


### Сборка и запуск

```bash
mvn clean javafx:run
```
### Платформы

- **Windows**
```bash
  mvn clean javafx:run -Djavafx.platform=win
```
- **Linux**
```bash
  mvn clean javafx:run -Djavafx.platform=linux
```

- **macOS**
```bash
  mvn clean javafx:run -Djavafx.platform=mac
```

###  Лицензия

- Проект распространяется под лицензией MIT

