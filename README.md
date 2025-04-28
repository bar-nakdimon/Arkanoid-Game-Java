# Arkanoid-Game-Java

A 2D Arkanoid-style game developed in Java using Object-Oriented Programming (OOP) principles.
The game includes custom 2D physics, collision detection, event-driven gameplay, real-time rendering, and user interaction via keyboard.

![image](https://github.com/user-attachments/assets/aab32895-c1ee-4b27-9f67-a31524044dc6)


## Features
- Custom 2D physics engine (geometry and collision detection)
- Modular and event-driven game architecture
- Dynamic ball and block interactions
- Score tracking system
- Real-time graphics rendering
- Keyboard-controlled paddle movement
- Object-oriented design (inheritance, interfaces, encapsulation)

## Technologies
- Java 17+
- Object-Oriented Programming (OOP)
- biuoop-1.4.jar (Bar-Ilan University custom library for GUI and keyboard input)

## Requirements
- Java JDK 17 or later
- `biuoop-1.4.jar` (provided through Bar-Ilan University's course resources)

## Setup Instructions

1. Download `biuoop-1.4.jar` from the course site.
2. Place `biuoop-1.4.jar` in the root directory of the project.
3. Open a terminal or PowerShell in the project folder.

### Compile
```bash
javac -cp "biuoop-1.4.jar;." GameMain\*.java Sprite\*.java Geometry\*.java Collision\*.java Hit\*.java
