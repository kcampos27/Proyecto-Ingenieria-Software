package vista;

import model.Gestor;
import model.PantallaModel;

import javax.swing.*;
import java.awt.*;

public class PantallaTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("Prueba de PantallaView");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 530);
            frame.setResizable(false); // Permitir redimensionamiento

            // Crear instancia del panel
            PantallaView panel = new PantallaView();
            PantallaModel modelo = PantallaModel.getMiPantalla();
            Gestor gestor = Gestor.getInstance();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}