package bomberman.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public GamePanel() {
        this.setPreferredSize(new Dimension(800, 600)); // Tamaño de la ventana
        this.setBackground(Color.BLACK); // Fondo negro
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("¡Bienvenido a BomberMan!", 350, 300);
    }
}
