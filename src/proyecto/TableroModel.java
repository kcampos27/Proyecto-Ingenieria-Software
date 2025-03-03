package proyecto;

import java.util.ArrayList;

public class TableroModel {
	
	//ATRIBUTOS
    private Casilla[][] tablero;

    // CONSTRUCTORA
    public TableroModel() {
        tablero = new Casilla[11][17];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                tablero[i][j] = new Casilla(i, j);
                if(i==0&&j==0)
                {//Bomberman
                	tablero[i][j].setContent("");
                }
                if(i%2!=0 && j%2!=0) 
                {//Bloques duros
                tablero[i][j].setContent("");
                }
                else {
                	
                }
            }
        }
    }

    // METODOS
    public Casilla getCasilla(int x, int y) {
        return tablero[x][y];
    }

    public void cambiarContent(int x, int y, String newContent) {
        tablero[x][y].setContent(newContent);
    }

}
