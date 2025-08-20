import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        String[] options = {"GUI Version", "Console Version", "Exit"};
        
        int choice = JOptionPane.showOptionDialog(
            null,
            "Choose how you want to play Dungeon Mayhem:",
            "⚔️ Dungeon Mayhem Launcher ⚔️",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        switch (choice) {
            case 0:
                SwingUtilities.invokeLater(() -> new DungeonMayhemGUI());
                break;
            case 1:
                DungeonAdventure.main(args);
                break;
            case 2:
            default:
                System.exit(0);
                break;
        }
    }
}