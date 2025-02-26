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
            }
        }
    }

    // METODOS
    public Casilla getCasilla(int x, int y) {
        return tablero[x][y];
    }

    public void cambiarEstado(int x, int y, String nuevoEstado) {
        tablero[x][y].setEstado(nuevoEstado);
    }

}
