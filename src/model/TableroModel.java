package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class TableroModel extends Observable {
	
	//ATRIBUTOS
	private Casilla[][] tablero;
    private Random random;
    private int alto = 11;
    private int ancho = 17;
    private static TableroModel miTablero;
    // CONSTRUCTORA
    private TableroModel() {
       
    }

    // METODOS
    public void inicializar()
    {
        tablero = new Casilla[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                tablero[i][j] = new Casilla(i, j);
                inicializarClassic(i,j);
            }
        }
    }
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
        	tablero[pI][pJ].setContent("bomberman.W.");
        }
    	else if(pI%2!=0 && pJ%2!=0) 
        {//Bloques duros
        tablero[pI][pJ].setContent("bloqueD");
        }
        else if( (pI == 1 && pJ == 1) 
    			|| (pI == 1 && pJ == 0) || (pI == 0 && pJ == 1)){
        tablero[pI][pJ].setContent("");
        }
        else if((pI==0 && pJ == 2) || (pI==2 && pJ==0))
        {
        	tablero[pI][pJ].setContent("bloqueB");
        }
        else {
        	
        	if (p <= 33)  
        	{ 
                tablero[pI][pJ].setContent("bloqueB"); 
            } 
        	else if (p > 33 && p<=44) 
        	{ 
                tablero[pI][pJ].setContent("enemigo");
            } 
        	else 
            {
                tablero[pI][pJ].setContent("");
            }
        }
    	setChanged();
        notifyObservers(new Object[] {pI,pJ,tablero[pI][pJ].getContent()});
        System.out.println(tablero[pI][pJ].getContent());
    }
    
    public Casilla getCasilla(int x, int y) {
        return tablero[x][y];
    }

    public void cambiarContent(int x, int y, String newContent) {
        tablero[x][y].setContent(newContent);
        setChanged();
        notifyObservers();
    }
    public int getAncho()
    {
    	return this.ancho;
    }
    
    public int getAlto()
    {
    	return this.alto;
    }
}
