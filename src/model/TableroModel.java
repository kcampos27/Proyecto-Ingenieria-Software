package model;

import java.util.HashSet;
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
        	generarContent(pJ,pI,"bombermanW");
        	tablero[pJ][pI].imprimirContent();
        }
    	else if(pI%2!=0 && pJ%2!=0) 
        {//Bloques duros
    		generarContent(pJ,pI,"bloqueD");
    		tablero[pJ][pI].imprimirContent();
        }
    	//NO OCURRE NADA
        else if( (pI == 1 && pJ == 1) 
    			|| (pI == 1 && pJ == 0) || (pI == 0 && pJ == 1)){
        	generarContent(pJ,pI,"");
        }
        else if((pI==0 && pJ == 2) || (pI==2 && pJ==0))
        {
        	generarContent(pJ,pI,"bloqueB");
        	tablero[pJ][pI].imprimirContent();
        }
        else 
        {
        	if (p <= 33)  
        	{ 
                generarContent(pJ,pI,"bloqueB");
                tablero[pJ][pI].imprimirContent();
            } 
        	else if (p > 33 && p<=40) 
        	{ 
                generarContent(pJ,pI,"enemigo");
            	tablero[pJ][pI].imprimirContent();
            } 
        	else 
            {
                generarContent(pJ,pI,"");
                tablero[pJ][pI].imprimirContent();
            }
        }
    	System.out.println("");
    }

    public void generarContent(int x, int y, String newContent) {
        switch (newContent) {
            case "bomberBomba" -> {//Bomberman pone la bomba
                tablero[x][y].crearContent("bombaS");
                tablero[x][y].crearContent(BomberMan.getMiBomberMan().getNombre());
                setChanged();
                notifyObservers(new Object[] {x,y,"bomberBomba","add",1});
            }
            case "bombaS" -> {//Para cuando el bomberman se va de la casilla donde deja bomba
                setChanged();
                notifyObservers(new Object[] {x,y,"bombaS","add",1});
            }
            default -> {
                tablero[x][y].crearContent(newContent);
                System.out.println("Aniadiendo contenido a [" + x + "][" + y + "]: " + newContent);
                setChanged();
                notifyObservers(new Object[] {x,y,newContent,"add",1});
            }
        }
    
}
    
    public void aniadirContent(int x, int y, Elemento newContent) {
           
    	if(newContent.getNombre().equals("bomberBomba")){//Bomberman pone la bomba
    		tablero[x][y].crearContent("bombaS");
               tablero[x][y].addContent(newContent);
                    setChanged();
                    notifyObservers(new Object[] {x,y,"bomberBomba","add",1});
        }
    	else if(newContent.getNombre().equals("bombaS"))
    	{//Para cuando el bomberman se va de la casilla donde deja bomba
                    setChanged();
                    notifyObservers(new Object[] {x,y,"bombaS","add",1});
        }
    	else{
                    tablero[x][y].addContent(newContent);
                    System.out.println("Aniadiendo contenido a [" + x + "][" + y + "]: " + newContent.getNombre());
                    setChanged();
                    notifyObservers(new Object[] {x,y,newContent.getNombre(),"add",1});
        }
    }
        
    
    public void eliminarContent(int x, int y, String newContent)
    {
    	tablero[x][y].removeContent(newContent);
    	setChanged();
        notifyObservers(new Object[] {x,y,newContent,"del",1});
    }
    
    public void orientarBomber(int x, int y, String orientacion) {
        if(!casillaIncluye(x,y,BomberMan.getMiBomberMan().getNombre()))
        {tablero[x][y].addContent(BomberMan.getMiBomberMan());}
        else 
        {
        	setChanged();
            notifyObservers(new Object[] {x,y,BomberMan.getMiBomberMan().getNombre(),"del",1});
        }
        setChanged();
        notifyObservers(new Object[] {x,y,orientacion,"add",1});
    }
    
    public boolean casillaIncluye(int px, int py, String pCont)
    {
    	return tablero[px][py].estaContent(pCont);
    }
    
    public boolean existe(String pCont) 
    { 
    	boolean esta = false;
    	int i=0,j=0;
    	while (i < alto && !esta) {
    		while (j < ancho && !esta) 
    		{
    			if(casillaIncluye(j,i,pCont))
    			{esta = true;}
    			j++;
    		}
    		i++;
    	}
    	return esta;
    }
    
    public void damage(int pX, int pY, int pDmg, String[] pTargets)
    {
    	HashSet<String> targets = new HashSet<String>();
    	for(String s : pTargets)
    	{
    		targets.add(s);
    	}
    	tablero[pX][pY].damageElems(pDmg, targets);
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
    		
    		}, 1000);
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
    
    public void printContent(int pX, int pY)
    {
    	tablero[pX][pY].imprimirContent();;
    }
  
}
