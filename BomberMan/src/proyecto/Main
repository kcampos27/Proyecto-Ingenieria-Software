package proyecto;

import javax.swing.JFrame;

public class Main {
	
    public static void main(String[] args) {
        TableroModel modelo = new TableroModel();
        TableroView vista = new TableroView(modelo);
        TableroController controlador = new TableroController(modelo, vista);

        JFrame frame = new JFrame("Tablero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(vista);
        frame.setVisible(true);
        
       
    }
}
