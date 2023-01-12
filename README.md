# Incremental Game

## Voraussetzungen

Die Java Version, die benutzt wurde ist Java 15.

Zum gemeinsamen Entwickeln wurde [Maven](https://maven.apache.org/) verwendet, um externe Bibliotheken in das Projekt einzubinden. Deshalb müssen folgende Schritte unternommen werden, um das Programm ausführen zu können

### Eclipse

  1. Rechtsklick auf `pom.xml`
  2. `Run as > 2 Maven build ...`
  3. Bei Goals `clean javafx:run` eintragen

### Terminal 

  1. In den Projektordner navigieren
  2. `mvn clean javafx:run` ausführen 

## Projektfokus

Der Fokus des Projektes lag darauf, ein einfaches Spiel zu entwickeln, dessen Logik unabhängig von der darüberliegenden GUI ist. Diese Unabhängigkeit soll vor allem dadurch gezeigt werden, dass während des Spiels das Thema gewechselt werden kann, ohne dass sich etwas am Spielzustand ändert. Dazu sollte es möglich sein, den aktuellen Spielstand automatisch nach einem Zeitintervall und beim Beenden automatisch zu speichern und nach erneutem Start automatisch zu laden.

Die nötigen Dateien findet man im Projektordner `config/`.

## Funktionsumfang

### Data

  - Spielstand in `config/savegame.json`
  - Themem in `config/themes.json`
  - Texte in `config/labels.json`

### Businesslogik

  - automatisches Speichern und Laden des Spielstandes
  - Zurücksetzen des Spielstandes
  - konfigurierbarer Text und Themes
  - Kauf von Upgrades durch Währung
  - einmalige Generierung von Währung durch Methodenaufruf
  - sekündliche automatische Generierung von Währung

### Controller

  - Weiterleiten von Nutzerinteraktion von GUI zu Businesslogik
  - Binden von Text und Bildern der GUI an Konfiguration
  - Binden von Text der GUI an Businesslogik

### GUI 

  - Anzeige von Fehlermeldungen und Bestätigungen
  - animierter Wechsel zwischen Szenen
  - Darstellung der Businesslogik durch Bilder und Texte
  - Wechsel des Themas / der grafischen Aufbereitung
