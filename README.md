# Dungeon Mayhem

A text-based dungeon crawler adventure game written in Java, featuring both console and GUI versions.

## Description

Dungeon Mayhem is an exciting roguelike dungeon crawler where you explore mysterious chambers, fight monsters, collect treasures, and search for the legendary dungeon key to escape alive. Face various challenges including monster encounters, deadly traps, treasure rooms, and mysterious potions!

## Features

- **Dual Play Modes**: Choose between a classic console version or a modern GUI interface
- **Random Room Generation**: Every playthrough is unique with procedurally generated rooms
- **Multiple Room Types**:
  - Empty chambers (chance to find the dungeon key)
  - Treasure rooms (collect gold)
  - Monster encounters (fight or flee)
  - Trap rooms (avoid spike traps)
  - Potion rooms (healing, gold, or poison effects)
- **Combat System**: Battle monsters with risk/reward mechanics
- **Health Management**: Manage your health through potions, rest, and avoiding damage
- **Gold Collection**: Accumulate wealth throughout your adventure
- **Win Condition**: Find the dungeon key and escape with your treasures

## Game Mechanics

- **Health**: Start with 100 HP. Reach 0 and it's game over!
- **Gold**: Collect gold from treasure rooms and defeated monsters
- **Dungeon Key**: Required to escape the dungeon (10% chance to find in empty rooms)
- **Monster Battles**: 33% chance to defeat monsters and earn rewards
- **Traps**: Deal 5-25 damage randomly
- **Potions**: Random effects - healing (20-50 HP), gold bonus (20-60 gold), or poison (5-20 damage)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) to run the compiled game

### Installation

1. Clone this repository:
   ```bash
   git clone <repository-url>
   cd DungeonMayham
   ```

2. Compile the Java files:
   ```bash
   javac src/*.java -d bin
   ```

### Running the Game

#### Using the Game Launcher (Recommended)

Run the launcher to choose between GUI and console versions:

```bash
java -cp bin GameLauncher
```

You'll be presented with three options:
- **GUI Version**: Modern graphical interface with buttons and visual effects
- **Console Version**: Classic text-based gameplay
- **Exit**: Close the launcher

#### Running Directly

**GUI Version:**
```bash
java -cp bin DungeonMayhemGUI
```

**Console Version:**
```bash
java -cp bin DungeonAdventure
```

## How to Play

### Console Version

1. Launch the game and press ENTER to begin
2. Read the room description
3. Choose your action by entering the corresponding number:
   - `1` - Continue exploring (or Fight in monster rooms)
   - `2` - Try to escape (or Run away in monster rooms)
   - `3` - Escape dungeon (only available in monster rooms)

### GUI Version

1. Click "Start Adventure" to begin
2. Read the room description in the main text area
3. Click one of the three action buttons at the bottom
4. Monitor your status (Health, Gold, Key, Rooms, Monsters) at the top

## Project Structure

```
DungeonMayham/
├── src/
│   ├── GameLauncher.java       # Main launcher to choose game mode
│   ├── DungeonAdventure.java   # Console version implementation
│   ├── DungeonMayhemGUI.java   # GUI version implementation
│   ├── BankMenu.java           # Additional game component
│   └── GameTest.java           # Testing utilities
├── bin/                        # Compiled class files
└── README.md                   # This file
```

## Game Statistics

Track your performance with these stats:
- **Health**: Current and maximum HP
- **Gold**: Total gold collected
- **Key Status**: Whether you've found the dungeon key
- **Rooms Explored**: Total number of rooms visited
- **Monsters Defeated**: Combat victories

## Tips for Success

1. **Explore carefully**: More rooms mean more chances to find the key
2. **Manage your health**: Don't push your luck when health is low
3. **Fight strategically**: Monster battles are risky but rewarding
4. **Use potions wisely**: They can help or harm - it's a gamble!
5. **Rest when safe**: In GUI mode, use rest opportunities to recover health
6. **Search thoroughly**: After defeating monsters or finding treasure, search for bonus gold

## Development

### Building from Source

The project uses standard Java compilation. No external dependencies are required.

```bash
# Compile all source files
javac src/*.java -d bin

# Run the game launcher
java -cp bin GameLauncher
```

### Testing

Run the game test suite:

```bash
java -cp bin GameTest
```

## Technical Details

- **Language**: Java
- **GUI Framework**: Java Swing
- **Design Pattern**: Object-oriented with event-driven GUI
- **Random Generation**: Uses `java.util.Random` for procedural content

## License

This is an educational project for college coursework.

## Author

Created as part of a college programming course.

## Acknowledgments

- Inspired by classic dungeon crawler and roguelike games
- Text-based adventure game traditions
