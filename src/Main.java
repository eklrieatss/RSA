import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu();
            menu.setVisible(true);
            menu.setSize(1600, 550);
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu.setTitle("Rivest–Shamir–Adleman Verschlüsselung");
        });
    }
}
