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
        tablero = new Casilla[ancho][alto];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                tablero[j][i] = new Casilla(j, i);
                inicializarClassic(j,i);
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
    
    public  void inicializarClassic(int pJ, int pI)
    {
    	random = new Random();
    	int p = random.nextInt(100);
    	
    	if(pI==0&&pJ==0)
        {//Bomberman
        	System.out.println("BOMBERMAN");
        	tablero[pJ][pI].setContent("bombermanW");
        	
        	System.out.println(tablero[pJ][pI].getContent());
        }
    	else if(pI%2!=0 && pJ%2!=0) 
        {//Bloques duros
        tablero[pJ][pI].setContent("bloqueD");
        }
        else if( (pI == 1 && pJ == 1) 
    			|| (pI == 1 && pJ == 0) || (pI == 0 && pJ == 1)){
        tablero[pJ][pI].setContent("");
        }
        else if((pI==0 && pJ == 2) || (pI==2 && pJ==0))
        {
        	tablero[pJ][pI].setContent("bloqueB");
        }
        else 
        {
        	if (p <= 33)  
        	{ 
                tablero[pJ][pI].setContent("bloqueB"); 
            } 
        	else if (p > 33 && p<=44) 
        	{ 
                tablero[pJ][pI].setContent("enemigo");
            } 
        	else 
            {
                tablero[pJ][pI].setContent("");
            }
        }
    	setChanged();
        notifyObservers(new Object[] {pJ,pI,tablero[pJ][pI].getContent()});
    }

    public void cambiarContent(int x, int y, String newContent) {
        tablero[x][y].setContent(newContent);
        setChanged();
        notifyObservers(new Object[] {x,y,tablero[x][y].getContent()});
    }
    public int getAncho()
    {
    	return this.ancho;
    }
    
    public int getAlto()
    {
    	return this.alto;
    }
    public boolean doesHurt(int pX, int pY)
    {
    	return tablero[pX][pY].getHurt();
    }
    
    public String getContent(int pX, int pY)
    {
    	return tablero[pX][pY].getContent();
    }
    public void cambiarBomber(int x, int y) {
        tablero[x][y].setContent("bombermanW");
    }
}
