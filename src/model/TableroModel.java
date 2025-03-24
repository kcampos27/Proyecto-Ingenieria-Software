package model;

import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("deprecation")
public class TableroModel extends Observable {
	
	//ATRIBUTOS
	private Casilla[][] tablero;
    private Random random;
    private int alto = 11;
    private int ancho = 17;
    private static TableroModel miTablero;
    private Timer timer = new Timer();
    private boolean hayBomba = false;
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
        	cambiarContent(pJ,pI,"bombermanW");
        	System.out.println(tablero[pJ][pI].getContent());
        }
    	else if(pI%2!=0 && pJ%2!=0) 
        {//Bloques duros
    		cambiarContent(pJ,pI,"bloqueD");
        	System.out.println(tablero[pJ][pI].getContent());
        }
        else if( (pI == 1 && pJ == 1) 
    			|| (pI == 1 && pJ == 0) || (pI == 0 && pJ == 1)){
        cambiarContent(pJ,pI,"");
        }
        else if((pI==0 && pJ == 2) || (pI==2 && pJ==0))
        {
        	cambiarContent(pJ,pI,"bloqueB");
        	System.out.println(tablero[pJ][pI].getContent());
        }
        else 
        {
        	if (p <= 33)  
        	{ 
                cambiarContent(pJ,pI,"bloqueB");
            	System.out.println(tablero[pJ][pI].getContent());
            } 
        	else if (p > 33 && p<=44) 
        	{ 
                cambiarContent(pJ,pI,"enemigo");
            	System.out.println(tablero[pJ][pI].getContent());
            } 
        	else 
            {
                cambiarContent(pJ,pI,"");
            	System.out.println(tablero[pJ][pI].getContent());
            }
        }
    }

    public void cambiarContent(int x, int y, String newContent) {
        tablero[x][y].setContent(newContent);
        setChanged();
        notifyObservers(new Object[] {x,y,tablero[x][y].getContent()});
    }
    
    public void crearBomba()
    {
    	if(!hayBomba)
    	{
    		BomberMan.getMiBomberMan().soltarBomba();
    		hayBomba=true;
    		timer.schedule(new TimerTask() 
    		{
    			@Override
    			public void run()
    			{
    				hayBomba=false;
    			}
    		
    		}, 2000);
    	}
    }
    
    public void moverBomberman(int pX, int pY)
    {
    	BomberMan.getMiBomberMan().mover(pX, pY);
    }
    
    public int getAncho()
    {
    	return this.ancho;
    }
    
    public int getAlto()
    {
    	return this.alto;
    } 
    
    public String getContent(int pX, int pY)
    {
    	return tablero[pX][pY].getContent();
    }
  
}
