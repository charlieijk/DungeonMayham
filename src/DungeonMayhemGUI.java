import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class DungeonMayhemGUI extends JFrame {
    
    private Random random = new Random();
    private int playerHealth = 100;
    private int playerGold = 0;
    private boolean hasKey = false;
    private boolean gameRunning = true;
    private int currentRoomType = 0;
    private int roomsExplored = 0;
    private int monstersDefeated = 0;
    
    private JLabel statusLabel;
    private JTextArea roomDisplay;
    private JButton button1, button2, button3;
    private JPanel buttonPanel;
    
    public DungeonMayhemGUI() {
        setTitle("⚔️ DUNGEON MAYHEM ⚔️");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        initializeComponents();
        showWelcomeScreen();
        
        setVisible(true);
    }
    
    private void initializeComponents() {
        setLayout(new BorderLayout());
        
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.DARK_GRAY);
        statusLabel.setForeground(Color.WHITE);
        add(statusLabel, BorderLayout.NORTH);
        
        roomDisplay = new JTextArea();
        roomDisplay.setFont(new Font("Monospaced", Font.PLAIN, 16));
        roomDisplay.setEditable(false);
        roomDisplay.setBackground(Color.BLACK);
        roomDisplay.setForeground(Color.GREEN);
        roomDisplay.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JScrollPane scrollPane = new JScrollPane(roomDisplay);
        add(scrollPane, BorderLayout.CENTER);
        
        buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        
        button1.setFont(new Font("Arial", Font.BOLD, 12));
        button2.setFont(new Font("Arial", Font.BOLD, 12));
        button3.setFont(new Font("Arial", Font.BOLD, 12));
        
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        updateStatusDisplay();
    }
    
    private void showWelcomeScreen() {
        roomDisplay.setText(
            "╔════════════════════════════════════╗\n" +
            "║    ⚔️  DUNGEON MAYHEM  ⚔️    ║\n" +
            "╚════════════════════════════════════╝\n\n" +
            "Welcome to the chaos, brave adventurer!\n\n" +
            "Survive the dungeon mayhem, collect treasures 💰,\n" +
            "and find the key 🗝️!\n\n" +
            "Face monsters 👾, avoid traps ⚠️,\n" +
            "and discover magic potions 🧪!\n\n" +
            "Click 'Start Adventure' to begin your journey..."
        );
        
        button1.setText("Start Adventure");
        button1.addActionListener(e -> startGame());
        button2.setText("");
        button2.setEnabled(false);
        button3.setText("Exit");
        button3.addActionListener(e -> System.exit(0));
    }
    
    private void startGame() {
        gameRunning = true;
        button2.setEnabled(true);
        displayRoom();
    }
    
    private void updateStatusDisplay() {
        statusLabel.setText(String.format(
            "❤️ Health: %d | 💰 Gold: %d | Key: %s | Rooms: %d | Monsters Defeated: %d",
            playerHealth, playerGold, 
            hasKey ? "🗝️" : "❌", 
            roomsExplored, monstersDefeated
        ));
    }
    
    private void displayRoom() {
        if (!gameRunning || playerHealth <= 0) {
            if (playerHealth <= 0) {
                gameOver();
            }
            return;
        }
        
        roomsExplored++;
        currentRoomType = random.nextInt(5);
        updateStatusDisplay();
        
        clearButtonListeners();
        
        switch (currentRoomType) {
            case 0:
                emptyRoom();
                break;
            case 1:
                treasureRoom();
                break;
            case 2:
                monsterRoom();
                break;
            case 3:
                trapRoom();
                break;
            case 4:
                potionRoom();
                break;
        }
    }
    
    private void clearButtonListeners() {
        for (ActionListener al : button1.getActionListeners()) {
            button1.removeActionListener(al);
        }
        for (ActionListener al : button2.getActionListeners()) {
            button2.removeActionListener(al);
        }
        for (ActionListener al : button3.getActionListeners()) {
            button3.removeActionListener(al);
        }
    }
    
    private void emptyRoom() {
        roomDisplay.setText(
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
            "▓                            ▓\n" +
            "▓     You enter an           ▓\n" +
            "▓     empty chamber...       ▓\n" +
            "▓                            ▓\n" +
            "▓    The air is stale and    ▓\n" +
            "▓    dust covers the floor   ▓\n" +
            "▓                            ▓\n" +
            "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n"
        );
        
        if (!hasKey && random.nextInt(10) == 0) {
            roomDisplay.append("\n✨ You found the DUNGEON KEY! 🗝️\n");
            hasKey = true;
            updateStatusDisplay();
        }
        
        button1.setText("Continue Exploring");
        button1.addActionListener(e -> displayRoom());
        
        button2.setText(hasKey ? "Escape 🗝️" : "Try to Escape (Need key!)");
        button2.addActionListener(e -> tryEscape());
        
        button3.setText("Rest (Restore 5 Health)");
        button3.addActionListener(e -> {
            playerHealth = Math.min(100, playerHealth + 5);
            updateStatusDisplay();
            roomDisplay.append("You rest briefly and feel slightly better...\n");
        });
    }
    
    private void treasureRoom() {
        int goldFound = random.nextInt(30) + 10;
        playerGold += goldFound;
        updateStatusDisplay();
        
        roomDisplay.setText(
            "✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\n" +
            "✨                                        ✨\n" +
            "✨  💰  TREASURE ROOM!  💰  ✨\n" +
            "✨     Gold scattered               ✨\n" +
            "✨     everywhere!                  ✨\n" +
            "✨                                        ✨\n" +
            "✨  Coins glitter in the torchlight ✨\n" +
            "✨                                        ✨\n" +
            "✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\n\n" +
            "You collected " + goldFound + " gold! 💰"
        );
        
        button1.setText("Continue Exploring");
        button1.addActionListener(e -> displayRoom());
        
        button2.setText(hasKey ? "Escape 🗝️" : "Try to Escape (Need key!)");
        button2.addActionListener(e -> tryEscape());
        
        button3.setText("Search for More");
        button3.addActionListener(e -> {
            int bonusGold = random.nextInt(10) + 1;
            playerGold += bonusGold;
            updateStatusDisplay();
            roomDisplay.append("\nYou found " + bonusGold + " more gold hidden in the corners!\n");
        });
    }
    
    private void monsterRoom() {
        roomDisplay.setText(
            "🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥\n" +
            "🔥                                        🔥\n" +
            "🔥    A wild monster                🔥\n" +
            "🔥    appears! 👾                    🔥\n" +
            "🔥                                        🔥\n" +
            "🔥  It snarls and shows its fangs!  🔥\n" +
            "🔥  What will you do?                🔥\n" +
            "🔥                                        🔥\n" +
            "🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥🔥"
        );
        
        button1.setText("Fight Monster ⚔️");
        button1.addActionListener(e -> fightMonster());
        
        button2.setText("Try to Run Away 🏃");
        button2.addActionListener(e -> runAway());
        
        button3.setText(hasKey ? "Escape Dungeon 🗝️" : "Try to Escape (Need key!)");
        button3.addActionListener(e -> tryEscape());
    }
    
    private void trapRoom() {
        int damage = random.nextInt(20) + 5;
        playerHealth -= damage;
        updateStatusDisplay();
        
        roomDisplay.setText(
            "⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️\n" +
            "⚠️                                        ⚠️\n" +
            "⚠️     TRAP ROOM!                    ⚠️\n" +
            "⚠️     Spikes everywhere!           ⚠️\n" +
            "⚠️                                        ⚠️\n" +
            "⚠️  The floor is covered with        ⚠️\n" +
            "⚠️  dangerous spike traps!           ⚠️\n" +
            "⚠️                                        ⚠️\n" +
            "⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️\n\n" +
            "Ouch! You took " + damage + " damage from the traps!"
        );
        
        if (playerHealth > 0) {
            button1.setText("Carefully Continue");
            button1.addActionListener(e -> displayRoom());
            
            button2.setText(hasKey ? "Escape 🗝️" : "Try to Escape (Need key!)");
            button2.addActionListener(e -> tryEscape());
            
            button3.setText("Disarm Traps");
            button3.addActionListener(e -> {
                if (random.nextBoolean()) {
                    roomDisplay.append("\nYou successfully disarmed some traps!\n");
                } else {
                    int moreDamage = random.nextInt(10) + 1;
                    playerHealth -= moreDamage;
                    updateStatusDisplay();
                    roomDisplay.append("\nYou triggered more traps! Lost " + moreDamage + " health!\n");
                }
            });
        } else {
            gameOver();
        }
    }
    
    private void potionRoom() {
        roomDisplay.setText(
            "🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪\n" +
            "🧪                                        🧪\n" +
            "🧪    MAGIC POTION ROOM!            🧪\n" +
            "🧪    Mysterious potions             🧪\n" +
            "🧪    bubble and glow!               🧪\n" +
            "🧪                                        🧪\n" +
            "🧪  Three potions sit on a shelf:   🧪\n" +
            "🧪  Red, Blue, and Green             🧪\n" +
            "🧪                                        🧪\n" +
            "🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪🧪"
        );
        
        button1.setText("Drink Red Potion");
        button1.addActionListener(e -> drinkPotion(0));
        
        button2.setText("Drink Blue Potion");
        button2.addActionListener(e -> drinkPotion(1));
        
        button3.setText("Drink Green Potion");
        button3.addActionListener(e -> drinkPotion(2));
    }
    
    private void drinkPotion(int potionChoice) {
        int effect = random.nextInt(3);
        
        switch (effect) {
            case 0:
                int healing = random.nextInt(30) + 20;
                playerHealth = Math.min(100, playerHealth + healing);
                roomDisplay.append("\n\nYou drank a healing potion! Restored " + healing + " health! ❤️");
                break;
            case 1:
                int goldBonus = random.nextInt(40) + 20;
                playerGold += goldBonus;
                roomDisplay.append("\n\nYou drank a gold transmutation potion! Gained " + goldBonus + " gold! 💰");
                break;
            case 2:
                int damage = random.nextInt(15) + 5;
                playerHealth -= damage;
                roomDisplay.append("\n\nOops! You drank a poison potion! Lost " + damage + " health! ☠️");
                break;
        }
        
        updateStatusDisplay();
        
        if (playerHealth > 0) {
            clearButtonListeners();
            button1.setText("Continue Exploring");
            button1.addActionListener(e -> displayRoom());
            
            button2.setText(hasKey ? "Escape 🗝️" : "Try to Escape (Need key!)");
            button2.addActionListener(e -> tryEscape());
            
            button3.setText("Rest");
            button3.addActionListener(e -> {
                playerHealth = Math.min(100, playerHealth + 3);
                updateStatusDisplay();
                roomDisplay.append("\nYou rest and recover 3 health...");
            });
        } else {
            gameOver();
        }
    }
    
    private void fightMonster() {
        roomDisplay.append("\n\n⚔️ BATTLE! ⚔️\n");
        
        if (random.nextInt(3) == 0) {
            roomDisplay.append("You defeated the monster!\n");
            monstersDefeated++;
            int goldReward = random.nextInt(25) + 15;
            playerGold += goldReward;
            roomDisplay.append("You earned " + goldReward + " gold! 💰");
            
            updateStatusDisplay();
            
            clearButtonListeners();
            button1.setText("Continue Exploring");
            button1.addActionListener(e -> displayRoom());
            
            button2.setText(hasKey ? "Escape 🗝️" : "Try to Escape (Need key!)");
            button2.addActionListener(e -> tryEscape());
            
            button3.setText("Search Monster");
            button3.addActionListener(e -> {
                int bonusGold = random.nextInt(10) + 1;
                playerGold += bonusGold;
                updateStatusDisplay();
                roomDisplay.append("\nYou found " + bonusGold + " more gold on the monster!\n");
            });
        } else {
            roomDisplay.append("The monster fought back!\n");
            int damage = random.nextInt(25) + 10;
            playerHealth -= damage;
            roomDisplay.append("You took " + damage + " damage!");
            
            updateStatusDisplay();
            
            if (playerHealth > 0) {
                clearButtonListeners();
                button1.setText("Continue Exploring");
                button1.addActionListener(e -> displayRoom());
                
                button2.setText(hasKey ? "Escape 🗝️" : "Try to Escape (Need key!)");
                button2.addActionListener(e -> tryEscape());
                
                button3.setText("Fight Again");
                button3.addActionListener(e -> fightMonster());
            } else {
                gameOver();
            }
        }
    }
    
    private void runAway() {
        if (random.nextBoolean()) {
            roomDisplay.append("\n\nYou successfully ran away!");
            clearButtonListeners();
            button1.setText("Continue Exploring");
            button1.addActionListener(e -> displayRoom());
            
            button2.setText(hasKey ? "Escape 🗝️" : "Try to Escape (Need key!)");
            button2.addActionListener(e -> tryEscape());
            
            button3.setText("Rest");
            button3.addActionListener(e -> {
                playerHealth = Math.min(100, playerHealth + 5);
                updateStatusDisplay();
                roomDisplay.append("\nYou catch your breath and recover 5 health...");
            });
        } else {
            roomDisplay.append("\n\nThe monster caught you!");
            int damage = random.nextInt(15) + 10;
            playerHealth -= damage;
            roomDisplay.append("\nYou took " + damage + " damage!");
            
            updateStatusDisplay();
            
            if (playerHealth > 0) {
                clearButtonListeners();
                button1.setText("Fight Monster ⚔️");
                button1.addActionListener(e -> fightMonster());
                
                button2.setText("Try to Run Again 🏃");
                button2.addActionListener(e -> runAway());
                
                button3.setText(hasKey ? "Escape Dungeon 🗝️" : "Try to Escape (Need key!)");
                button3.addActionListener(e -> tryEscape());
            } else {
                gameOver();
            }
        }
    }
    
    private void tryEscape() {
        if (hasKey) {
            victory();
        } else {
            roomDisplay.append("\n\nThe exit is locked! You need to find the key first! 🗝️");
        }
    }
    
    private void victory() {
        gameRunning = false;
        roomDisplay.setText(
            "✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨\n" +
            "✨                                        ✨\n" +
            "✨         🎉 CONGRATULATIONS! 🎉         ✨\n" +
            "✨                                        ✨\n" +
            "✨    You escaped the dungeon alive!    ✨\n" +
            "✨                                        ✨\n" +
            "✨    Final Gold: " + String.format("%-16s", playerGold + " 💰") + "✨\n" +
            "✨    Final Health: " + String.format("%-14s", playerHealth + " ❤️") + "✨\n" +
            "✨    Rooms Explored: " + String.format("%-11s", roomsExplored) + "✨\n" +
            "✨    Monsters Defeated: " + String.format("%-8s", monstersDefeated) + "✨\n" +
            "✨                                        ✨\n" +
            "✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨"
        );
        
        clearButtonListeners();
        button1.setText("Play Again");
        button1.addActionListener(e -> restartGame());
        
        button2.setText("");
        button2.setEnabled(false);
        
        button3.setText("Exit");
        button3.addActionListener(e -> System.exit(0));
    }
    
    private void gameOver() {
        gameRunning = false;
        roomDisplay.setText(
            "☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️\n" +
            "☠️                                        ☠️\n" +
            "☠️        GAME OVER!                 ☠️\n" +
            "☠️                                        ☠️\n" +
            "☠️   You perished in the             ☠️\n" +
            "☠️   depths of the dungeon           ☠️\n" +
            "☠️                                        ☠️\n" +
            "☠️   Gold collected: " + String.format("%-12s", playerGold) + "☠️\n" +
            "☠️   Rooms explored: " + String.format("%-12s", roomsExplored) + "☠️\n" +
            "☠️   Monsters defeated: " + String.format("%-9s", monstersDefeated) + "☠️\n" +
            "☠️                                        ☠️\n" +
            "☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️☠️"
        );
        
        clearButtonListeners();
        button1.setText("Play Again");
        button1.addActionListener(e -> restartGame());
        
        button2.setText("");
        button2.setEnabled(false);
        
        button3.setText("Exit");
        button3.addActionListener(e -> System.exit(0));
    }
    
    private void restartGame() {
        playerHealth = 100;
        playerGold = 0;
        hasKey = false;
        gameRunning = true;
        currentRoomType = 0;
        roomsExplored = 0;
        monstersDefeated = 0;
        
        button2.setEnabled(true);
        updateStatusDisplay();
        showWelcomeScreen();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DungeonMayhemGUI();
        });
    }
}