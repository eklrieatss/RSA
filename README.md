# RSA – Rivest–Shamir–Adleman

Eine Java-Anwendung zur Veranschaulichung des asymmetrischen RSA-Verschlüsselungsverfahrens. Über eine grafische Benutzeroberfläche lassen sich Schlüssel berechnen, Texte in numerische Zeichenwerte umwandeln sowie Nachrichten ver- und entschlüsseln.

Das Projekt dient dem Verständnis der mathematischen Grundlagen von RSA und ist für Lern- und Demonstrationszwecke gedacht.

## Funktionen

- Berechnung eines RSA-Schlüsselpaars aus zwei manuell eingegebenen Primzahlen
- Berechnung des Moduls `N`, der eulerschen Phi-Funktion `φ(N)` und der Exponenten `e` und `d`
- Umwandlung von Klartext in dezimale Zeichenwerte und zurück
- Verschlüsselung mit einem öffentlichen Schlüssel
- Entschlüsselung mit einem privaten Schlüssel
- Manuelle Eingabe bereits vorhandener Schlüssel
- Darstellung von Klartext, Zeichenwerten und verschlüsselten Zahlen in getrennten Textfeldern

## Technologien

- **Java 17** – Programmiersprache und Standardbibliothek
- **Java Swing** – grafische Benutzeroberfläche
- **BigInteger** – modulare Potenzierung bei der Ver- und Entschlüsselung
- **IntelliJ IDEA GUI Designer** – Aufbau der Benutzeroberfläche
- **Maven** – vorhandene Projektkonfiguration (`1.0-SNAPSHOT`)

## Installation und Ausführung

### Voraussetzungen

- JDK 17
- IntelliJ IDEA mit Unterstützung für den Swing GUI Designer
- Git (optional zum Klonen)

### Projekt starten

1. Repository klonen:

   ```bash
   git clone https://github.com/eklrieatss/RSA.git
   cd RSA
   ```

2. Das Projekt in IntelliJ IDEA öffnen und JDK 17 als Project SDK einstellen.
3. Sicherstellen, dass `src` als Sources Root eingerichtet ist und der IntelliJ-Build die GUI-Form `Menu.form` verarbeitet.
4. Die Klasse `Main` öffnen und die `main`-Methode ausführen.

**Hinweis:** Die aktuelle `pom.xml` verwendet nicht die abweichende Quellstruktur unter `src/` und enthält keine Konfiguration zur Verarbeitung der GUI-Form. Ein eigenständig ausführbares JAR über `mvn package` ist daher nicht eingerichtet. Verwende für dieses Projekt den IntelliJ-Build (nicht einen an Maven delegierten Build).

## Bedienung

### 1. Schlüssel berechnen

1. Zwei unterschiedliche Primzahlen in die Felder `p` und `q` eingeben.
2. Über die Schaltfläche `->` die Werte `N`, `φ(N)` und `e` berechnen.
3. Auf `umrechnen` klicken, um `d` und die beiden Schlüssel zu berechnen.

Die Schlüssel werden in folgendem Format dargestellt:

| Schlüssel | Format |
| --- | --- |
| Öffentlicher Schlüssel | `e,N` |
| Privater Schlüssel | `d,N` |

Die Schlüssel können alternativ direkt in die entsprechenden Felder eingegeben werden.

### 2. Nachricht verschlüsseln

1. Den Klartext in das obere Textfeld eingeben.
2. Auf `In Ascii` klicken, um den Text in numerische Zeichenwerte umzuwandeln.
3. Auf `kodieren` klicken, um die Zeichenwerte mit dem öffentlichen Schlüssel zu verschlüsseln.
4. Die verschlüsselten Zahlen erscheinen im unteren Textfeld.

### 3. Nachricht entschlüsseln

1. Die verschlüsselten Zahlen, durch einzelne Leerzeichen getrennt, in das untere Textfeld eingeben.
2. Den passenden privaten Schlüssel eintragen.
3. Auf `dekodieren` klicken. Die entschlüsselten Zeichenwerte erscheinen im mittleren Textfeld.
4. Über `in Klar` die Zeichenwerte wieder in Klartext umwandeln.

## Funktionsweise

### Schlüsselerzeugung

Aus zwei unterschiedlichen Primzahlen `p` und `q` wird zunächst der RSA-Modul berechnet:

$$N = p \cdot q$$

Anschließend wird die eulersche Phi-Funktion bestimmt:

$$\varphi(N) = (p-1) \cdot (q-1)$$

Die Anwendung sucht, beginnend bei `e = 5`, einen zu `φ(N)` teilerfremden Exponenten. Der größte gemeinsame Teiler wird mit dem euklidischen Algorithmus bestimmt.

$$\operatorname{ggT}(e, \varphi(N)) = 1$$

Der private Exponent `d` wird durch schrittweises Ausprobieren bestimmt, bis folgende Bedingung erfüllt ist:

$$e \cdot d \equiv 1 \pmod{\varphi(N)}$$

### Verschlüsselung und Entschlüsselung

Jede Java-`char`-Einheit des Klartexts wird zunächst in einen dezimalen Zahlenwert `m` umgewandelt. Die Schaltfläche heißt zwar `In Ascii`, technisch handelt es sich um UTF-16-Codeeinheiten, die für ASCII-Zeichen mit deren ASCII-Werten übereinstimmen.

Die Verschlüsselung erfolgt für jeden Wert einzeln:

$$c = m^e \bmod N$$

Die Entschlüsselung erfolgt mit dem privaten Exponenten:

$$m = c^d \bmod N$$

Für beide Berechnungen verwendet das Programm `BigInteger.modPow()`.

## Beispiel

| Parameter | Wert |
| --- | ---: |
| `p` | 17 |
| `q` | 19 |
| `N` | 323 |
| `φ(N)` | 288 |
| `e` | 5 |
| `d` | 173 |
| Öffentlicher Schlüssel | `5,323` |
| Privater Schlüssel | `173,323` |

Das Zeichen `A` hat den dezimalen Zeichenwert `65`. Mit dem obigen Schlüsselpaar ergibt sich folgender Ablauf:

```text
Klartext:         A
Zeichenwert:      65
Verschlüsselt:    12
Entschlüsselt:    65
Klartext:         A
```

## Projektstruktur

```text
RSA/
├── .idea/          # IntelliJ-Projekteinstellungen
├── src/
│   ├── Main.java   # Einstiegspunkt der Anwendung
│   ├── Menu.java   # Programmlogik und Ereignisverarbeitung
│   └── Menu.form   # Aufbau der grafischen Benutzeroberfläche
├── .gitignore      # Ausschluss des Maven-Build-Verzeichnisses
├── RSA.iml         # IntelliJ-Modulkonfiguration
└── pom.xml         # Maven-Projektkonfiguration
```

## Einschränkungen

- Die Primzahlen werden manuell eingegeben und nicht auf Gültigkeit geprüft.
- Die Schlüsselberechnung verwendet `int` und ist daher auf kleine Zahlen beschränkt; bei größeren Werten können Überläufe auftreten.
- Der private Exponent wird durch Ausprobieren statt durch eine effiziente modulare Inversenberechnung bestimmt.
- Jeder zu verschlüsselnde Zeichenwert muss kleiner als `N` sein.
- Ungültige Eingaben werden nicht systematisch abgefangen.
- Die Verschlüsselung erfolgt zeichenweise und ohne kryptografisches Padding (beispielsweise OAEP).

**Sicherheitshinweis:** Diese Implementierung dient ausschließlich der Demonstration des RSA-Verfahrens. Sie ist nicht für die Verschlüsselung sensibler Daten oder den produktiven Einsatz geeignet.
