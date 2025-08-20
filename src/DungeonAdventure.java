import java.util.Scanner;
import java.util.Random;

public class DungeonAdventure {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static int playerHealth = 100;
    private static int playerGold = 0;
    private static boolean hasKey = false;
    private static boolean gameRunning = true;
    private static int currentRoomType = 0;
    private static int roomsExplored = 0;
    private static int monstersDefeated = 0;
    
    public static void main(String[] args) {
        displayTitle();
        gameLoop();
        scanner.close();
    }
    
    public static void displayTitle() {
        System.out.println("\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
        System.out.println("\u2551    \u2694\uFE0F  DUNGEON MAYHAM  \u2694\uFE0F    \u2551");
        System.out.println("\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
        System.out.println("\nWelcome to the chaos, brave adventurer!");
        System.out.println("Survive the dungeon mayhem, collect treasures \uD83D\uDCB0, and find the key \uD83D\uDDDD\uFE0F!");
        System.out.println("Face monsters \uD83D\uDC7E, avoid traps \u26A0\uFE0F, and discover magic potions \uD83E\uDDEA!");
        System.out.println("\nPress ENTER to begin...");
        scanner.nextLine();
    }
    
    public static void gameLoop() {
        while (gameRunning && playerHealth > 0) {
            displayStatus();
            displayRoom();
            handleChoice();
        }
        
        if (playerHealth <= 0) {
            gameOver();
        }
    }
    
    public static void displayStatus() {
        System.out.println("\n\u2665\uFE0F Health: " + playerHealth + " | \uD83D\uDCB0 Gold: " + playerGold + 
                          " | Key: " + (hasKey ? "\uD83D\uDDDD\uFE0F" : "\u274C") + 
                          " | Rooms: " + roomsExplored + " | Monsters Defeated: " + monstersDefeated);
        System.out.println("\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500");
    }
    
    public static void displayRoom() {
        roomsExplored++;
        currentRoomType = random.nextInt(5);
        
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
    
    public static void emptyRoom() {
        System.out.println("\n\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
        System.out.println("\u2591                           \u2591");
        System.out.println("\u2591     You enter an         \u2591");
        System.out.println("\u2591     empty chamber...     \u2591");
        System.out.println("\u2591                           \u2591");
        System.out.println("\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591\u2591");
        
        if (!hasKey && random.nextInt(10) == 0) {
            System.out.println("\n\u2728 You found the DUNGEON KEY! \uD83D\uDDDD\uFE0F");
            hasKey = true;
        }
        
        System.out.println("\n1. Continue exploring");
        System.out.println("2. Try to escape" + (hasKey ? " \uD83D\uDDDD\uFE0F" : " (Need key!)"));
    }
    
    public static void treasureRoom() {
        System.out.println("\n\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728");
        System.out.println("\u2728                           \u2728");
        System.out.println("\u2728  \uD83D\uDCB0  TREASURE ROOM!  \uD83D\uDCB0  \u2728");
        System.out.println("\u2728     Gold scattered       \u2728");
        System.out.println("\u2728     everywhere!          \u2728");
        System.out.println("\u2728                           \u2728");
        System.out.println("\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728");
        
        int goldFound = random.nextInt(30) + 10;
        playerGold += goldFound;
        System.out.println("\nYou collected " + goldFound + " gold! \uD83D\uDCB0");
        
        System.out.println("\n1. Continue exploring");
        System.out.println("2. Try to escape" + (hasKey ? " \uD83D\uDDDD\uFE0F" : " (Need key!)"));
    }
    
    public static void monsterRoom() {
        System.out.println("\n\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25");
        System.out.println("\uD83D\uDD25                           \uD83D\uDD25");
        System.out.println("\uD83D\uDD25    A wild monster        \uD83D\uDD25");
        System.out.println("\uD83D\uDD25    appears! \uD83D\uDC7E            \uD83D\uDD25");
        System.out.println("\uD83D\uDD25                           \uD83D\uDD25");
        System.out.println("\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25\uD83D\uDD25");
        
        System.out.println("\n1. Fight the monster \u2694\uFE0F");
        System.out.println("2. Try to run away \uD83C\uDFC3");
        System.out.println("3. Try to escape dungeon" + (hasKey ? " \uD83D\uDDDD\uFE0F" : " (Need key!)"));
    }
    
    public static void trapRoom() {
        System.out.println("\n\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F");
        System.out.println("\u26A0\uFE0F                           \u26A0\uFE0F");
        System.out.println("\u26A0\uFE0F     TRAP ROOM!            \u26A0\uFE0F");
        System.out.println("\u26A0\uFE0F     Spikes everywhere!     \u26A0\uFE0F");
        System.out.println("\u26A0\uFE0F                           \u26A0\uFE0F");
        System.out.println("\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F\u26A0\uFE0F");
        
        int damage = random.nextInt(20) + 5;
        playerHealth -= damage;
        System.out.println("\nOuch! You took " + damage + " damage from the traps!");
        
        if (playerHealth > 0) {
            System.out.println("\n1. Carefully continue exploring");
            System.out.println("2. Try to escape" + (hasKey ? " \uD83D\uDDDD\uFE0F" : " (Need key!)"));
        }
    }
    
    public static void potionRoom() {
        System.out.println("\n\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA");
        System.out.println("\uD83E\uDDEA                           \uD83E\uDDEA");
        System.out.println("\uD83E\uDDEA    MAGIC POTION ROOM!      \uD83E\uDDEA");
        System.out.println("\uD83E\uDDEA    Mysterious potions       \uD83E\uDDEA");
        System.out.println("\uD83E\uDDEA    bubble and glow!         \uD83E\uDDEA");
        System.out.println("\uD83E\uDDEA                           \uD83E\uDDEA");
        System.out.println("\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA\uD83E\uDDEA");
        
        int potionType = random.nextInt(3);
        switch (potionType) {
            case 0:
                int healing = random.nextInt(30) + 20;
                playerHealth = Math.min(100, playerHealth + healing);
                System.out.println("\nYou drank a healing potion! Restored " + healing + " health! \u2665\uFE0F");
                break;
            case 1:
                int goldBonus = random.nextInt(40) + 20;
                playerGold += goldBonus;
                System.out.println("\nYou drank a gold transmutation potion! Gained " + goldBonus + " gold! \uD83D\uDCB0");
                break;
            case 2:
                int damage = random.nextInt(15) + 5;
                playerHealth -= damage;
                System.out.println("\nOops! You drank a poison potion! Lost " + damage + " health! \u2620\uFE0F");
                break;
        }
        
        if (playerHealth > 0) {
            System.out.println("\n1. Continue exploring");
            System.out.println("2. Try to escape" + (hasKey ? " \uD83D\uDDDD\uFE0F" : " (Need key!)"));
        }
    }
    
    public static void handleChoice() {
        System.out.print("\nChoose your action: ");
        String input = scanner.nextLine();
        
        try {
            int choice = Integer.parseInt(input);
            
            switch (choice) {
                case 1:
                    if (getCurrentRoomType() == 2) {
                        fightMonster();
                    } else {
                        System.out.println("\nYou venture deeper into the dungeon...");
                    }
                    break;
                case 2:
                    if (getCurrentRoomType() == 2) {
                        if (random.nextBoolean()) {
                            System.out.println("\nYou successfully ran away!");
                        } else {
                            System.out.println("\nThe monster caught you!");
                            int damage = random.nextInt(15) + 10;
                            playerHealth -= damage;
                            System.out.println("You took " + damage + " damage!");
                        }
                    } else {
                        tryEscape();
                    }
                    break;
                case 3:
                    if (getCurrentRoomType() == 2) {
                        tryEscape();
                    }
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
        }
    }
    
    public static int getCurrentRoomType() {
        return currentRoomType;
    }
    
    public static void fightMonster() {
        System.out.println("\n\u2694\uFE0F BATTLE! \u2694\uFE0F");
        
        if (random.nextInt(3) == 0) {
            System.out.println("You defeated the monster!");
            monstersDefeated++;
            int goldReward = random.nextInt(25) + 15;
            playerGold += goldReward;
            System.out.println("You earned " + goldReward + " gold! \uD83D\uDCB0");
        } else {
            System.out.println("The monster fought back!");
            int damage = random.nextInt(25) + 10;
            playerHealth -= damage;
            System.out.println("You took " + damage + " damage!");
        }
    }
    
    public static void tryEscape() {
        if (hasKey) {
            victory();
        } else {
            System.out.println("\nThe exit is locked! You need to find the key first! \uD83D\uDDDD\uFE0F");
        }
    }
    
    public static void victory() {
        System.out.println("\n\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728");
        System.out.println("\u2728                                       \u2728");
        System.out.println("\u2728         \uD83C\uDF89 CONGRATULATIONS! \uD83C\uDF89         \u2728");
        System.out.println("\u2728                                       \u2728");
        System.out.println("\u2728    You escaped the dungeon alive!    \u2728");
        System.out.println("\u2728                                       \u2728");
        System.out.println("\u2728    Final Gold: " + playerGold + " \uD83D\uDCB0              \u2728");
        System.out.println("\u2728    Final Health: " + playerHealth + " \u2665\uFE0F            \u2728");
        System.out.println("\u2728                                       \u2728");
        System.out.println("\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728\u2728");
        gameRunning = false;
    }
    
    public static void gameOver() {
        System.out.println("\n\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F");
        System.out.println("\u2620\uFE0F                           \u2620\uFE0F");
        System.out.println("\u2620\uFE0F        GAME OVER!         \u2620\uFE0F");
        System.out.println("\u2620\uFE0F                           \u2620\uFE0F");
        System.out.println("\u2620\uFE0F   You perished in the     \u2620\uFE0F");
        System.out.println("\u2620\uFE0F   depths of the dungeon   \u2620\uFE0F");
        System.out.println("\u2620\uFE0F                           \u2620\uFE0F");
        System.out.println("\u2620\uFE0F   Gold collected: " + playerGold + "      \u2620\uFE0F");
        System.out.println("\u2620\uFE0F                           \u2620\uFE0F");
        System.out.println("\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F\u2620\uFE0F");
    }
}