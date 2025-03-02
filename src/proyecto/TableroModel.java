package proyecto;

import java.util.ArrayList;

public class TableroModel {
	
	//ATRIBUTOS
    private static TableroModel miTablero;
	private Casilla[][] tablero;

    // CONSTRUCTORA
    private TableroModel() {
        tablero = new Casilla[11][17];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                tablero[i][j] = new Casilla(i, j);
                if(i==0&&j==0)
                {//Bomberman
                	tablero[i][j].setContent(BomberMan.getBomberman());
                }
                if(i%2!=0 && j%2!=0) 
                {//Bloques duros
                tablero[i][j].setContent(new BloqueDuro(i,j));
                }
                else {
                	
                }
            }
        }
    }

    // METODOS
    public static TableroModel getMiTablero()
    {
    	if (miTablero==null)
    	{
    		miTablero= new TableroModel();
    	}
    	return miTablero;
    }
    
    
    public Casilla getCasilla(int x, int y) {
        return tablero[x][y];
    }

    public void cambiarContent(int x, int y, Contenido newContent) {
        tablero[x][y].setContent(newContent);
    }

}
