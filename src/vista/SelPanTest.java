package vista;

import javax.swing.*;
import java.awt.*;

public class SelPanTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Prueba de SelectorPantallasView");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 530);
            frame.setResizable(true); // Permitir redimensionamiento

            // Crear instancia del panel
            JPanel panel = new SelectorPantallasView();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}