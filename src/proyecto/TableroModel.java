package proyecto;

import java.util.ArrayList;
import java.util.Random;

public class TableroModel {
	
	//ATRIBUTOS
    private static TableroModel miTablero;
	private Casilla[][] tablero;
    private Random random;

    // CONSTRUCTORA
    private TableroModel() {
        tablero = new Casilla[11][17];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 17; j++) {
                tablero[i][j] = new Casilla(i, j);
                inicializarClassic(i,j);
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
    
    public  void inicializarClassic(int pI, int pJ)
    {
    	random = new Random();
    	int p = random.nextInt(100);
    	
    	if(pI==0&&pJ==0)
        {//Bomberman
        	tablero[pI][pJ].setContent(BomberMan.getBomberman().getName());
        }
        if(pI%2!=0 && pJ%2!=0) 
        {//Bloques duros
        tablero[pI][pJ].setContent(new BloqueDuro(pI,pJ).getName());

        }
        else {
        	
        	if (p < 33) { 
                tablero[pI][pJ].setContent(new BloqueBlando(pI,pJ).getName()); 
            } else if (p < 33) { 
                tablero[pI][pJ].setContent(new Enemigo("enemigo",pI,pJ).getName());
            } else {
                tablero[pI][pJ].setContent("");
            }
        	
        }
    }
    
    public Casilla getCasilla(int x, int y) {
        return tablero[x][y];
    }

    public void cambiarContent(int x, int y, String newContent) {
        tablero[x][y].setContent(newContent);
    }

}
