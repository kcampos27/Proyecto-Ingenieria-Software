package main;

import javax.swing.JFrame;

import model.Gestor;
import model.PantallaModel;
import model.TableroModel;
import vista.PantallaView;
import vista.TableroView;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame("PantallaView");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 530);
            frame.setResizable(false); // Permitir redimensionamiento
            frame.setLocationRelativeTo(frame);
            
            // Crear instancia del panel
            PantallaView panel = new PantallaView();
            PantallaModel.getMiPantalla();
            Gestor.getInstance();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
        
    }
}
