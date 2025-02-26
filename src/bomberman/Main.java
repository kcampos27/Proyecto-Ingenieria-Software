package bomberman;

import javax.swing.*;
import bomberman.view.GamePanel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("BomberMan");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
