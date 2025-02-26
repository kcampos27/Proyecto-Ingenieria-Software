package proyecto;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class TableroView extends JPanel {
	
	//ATRIBUTOS
    private TableroModel modelo;
    private int ancho = 17;
    private int alto = 11;

    // CONSTRUCTORA
    public TableroView(TableroModel modelo) {
        this.modelo = modelo;
        setLayout(new GridLayout(alto, ancho, 1, 1));
        inicializarVista();
    }

    // METODOS
    private void inicializarVista() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                Casilla casilla = modelo.getCasilla(i, j);
                JLabel lbl = new JLabel(casilla.getX() + "," + casilla.getY());
                lbl.setOpaque(true);
                lbl.setBackground(Color.ORANGE);
                lbl.setHorizontalAlignment(JLabel.CENTER);
                lbl.setForeground(new Color(0, 0, 0, 0));
                add(lbl);
            }
        }
    }

    
}
