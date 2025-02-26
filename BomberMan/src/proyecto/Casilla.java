package proyecto;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Casilla {
	
   //ATRIBUTOS
    private int x;
    private int y;
    private String contenido; 

    // CONSTRUCTORA
    public Casilla(int x, int y) {
        this.x = x;
        this.y = y;
        this.contenido = "libre";  
    }

    // METODOS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getEstado() {
        return contenido;
    }

    public void setEstado(String estado) {
        this.contenido = estado;
    }
}

