![CI](https://github.com/maxim618/AudioPlayer/actions/workflows/ci.yml/badge.svg)
# PLAYMY 

Простой аудиоплеер на **JavaFX**.  
Поддерживает воспроизведение треков и управление плейлистом.

##  Возможности
- Воспроизведение аудиофайлов (**MP3, WAV**)
- Управление воспроизведением: **Play / Pause / Stop**
- Перемотка и выбор треков
- Регулировка громкости
- Отображение информации о треке (название, артист, альбом)
- Поддержка обложек (если есть в файле)

##  Запуск проекта

### Требования
- **Java 17+**
- **Maven** 3.6+

### Сборка и запуск
```bash
mvn clean javafx:run
```
### Windows/Linux/Mac:
```bash
mvn clean javafx:run -Djavafx.platform=win
````
```bash
mvn clean javafx:run -Djavafx.platform=linux
```
```bash
mvn clean javafx:run -Djavafx.platform=mac
```
###  Лицензия

- Проект распространяется под лицензией MIT

