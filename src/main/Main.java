package main;

import javax.swing.JFrame;

import model.TableroModel;
import vista.TableroView;

public class Main {
	
    public static void main(String[] args) {
        
    	TableroView vista = new TableroView();
    	TableroModel modelo= TableroModel.getMiTablero();
    	modelo.inicializar();
        JFrame frame = new JFrame("Tablero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(vista);
        frame.setVisible(true);
       
    }
}
