package model;
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
        this.contenido = null;  
    }

    // METODOS
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String getContent()
    {
    	return contenido;
    }
    public void setContent(String newContent) {
    	contenido = newContent;	
    }
}


