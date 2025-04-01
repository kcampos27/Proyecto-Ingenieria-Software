package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.EventQueue;

public class PantallaTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Prueba de PantallaView");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 530);
            frame.setResizable(false); // Permitir redimensionamiento
            
            // Crear instancia del panel
            JPanel panel = new PantallaView();
            frame.setContentPane(panel);

            frame.setVisible(true);
        });
    }
}