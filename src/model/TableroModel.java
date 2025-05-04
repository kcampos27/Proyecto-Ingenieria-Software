package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public abstract class TableroModel extends Observable {
	
	//ATRIBUTOS
	private Casilla[][] tablero;
    private Random random;
    private int alto = 11;
    private int ancho = 17;
    private Timer timer = new Timer();
    private boolean hayBomba = false;
    private int enemigos = 0;
    private int enemigosR = 0;

    // CONSTRUCTORA
    public TableroModel() {
    	
    }

    // METODOS
    public void inicializar()
    {
        tablero = new Casilla[ancho][alto];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                tablero[j][i] = new Casilla(j, i);
                inicializarPantalla(j,i);
            }
        }
    }
    
    abstract  void inicializarPantalla(int pJ, int pI);

    public synchronized void generarContent(int x, int y, String newContent) {
        switch (newContent) {
            case "bomberBomba" -> {//Bomberman pone la bomba
                tablero[x][y].crearContent("bombaS");
                tablero[x][y].crearContent(BomberMan.getMiBomberMan().getNombre());
                setChanged();
                notifyObservers(new Object[] {x,y,"bomberBomba","add",1});
            }
            case "blackwithbomb1" -> {//Bomberman Negro pone la bomba
                tablero[x][y].crearContent("bombaU");
                tablero[x][y].crearContent(BomberMan.getMiBomberMan().getNombre());
                setChanged();
                notifyObservers(new Object[] {x,y,"blackwithbomb1","add",1});
            }
            case "bombaS" -> {//Para cuando el bomberman se va de la casilla donde deja bomba
                setChanged();
                notifyObservers(new Object[] {x,y,"bombaS","add",1});
            }
            case "bombaU" -> {//Para cuando el bomberman Negro se va de la casilla donde deja bomba
                setChanged();
                notifyObservers(new Object[] {x,y,"bombaU","add",1});
            }
            default -> {
                tablero[x][y].crearContent(newContent);
                System.out.println("Aniadiendo contenido a [" + x + "][" + y + "]: " + newContent);
                setChanged();
                notifyObservers(new Object[] {x,y,newContent,"add",1});
            }
        }
    
}
    
    public synchronized void aniadirContent(int x, int y, Elemento newContent) {
        if(newContent.getNombre().equals("bombaS"))
    	{//Para cuando el bomberman se va de la casilla donde deja bomba
            tablero[x][y].addContent(newContent);
            if (!(tablero[x][y].estaContent("bombermanW")||tablero[x][y].estaContent("bombermanN"))){
                setChanged();
                notifyObservers(new Object[]{x, y, "bombaS", "add", 1});
            }
        }
        else if(newContent.getNombre().equals("bombaU"))
        {//Para cuando el bomberman negro se va de la casilla donde deja bomba
            tablero[x][y].addContent(newContent);
            if (!(tablero[x][y].estaContent("bombermanW")||tablero[x][y].estaContent("bombermanN"))) {
                setChanged();
                notifyObservers(new Object[]{x, y, "bombaU", "add", 1});
            }
        }
    	else
    	{
    		tablero[x][y].addContent(newContent);
            System.out.println("Aniadiendo contenido a [" + x + "][" + y + "]: " + newContent.getNombre());
            setChanged();
            notifyObservers(new Object[] {x,y,newContent.getNombre(),"add",1});
        }
    }
        
    
    public synchronized void eliminarContent(int x, int y, String newContent)
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
    
    public void actualizarItem(String pAccion, String pItem, int pCantidad) 
	{
		setChanged();
        notifyObservers(new Object[] {pItem,pCantidad,pAccion});
	}
    
    public void PantallaPausa(String pAccion)
    {
    	setChanged();
    	notifyObservers(new Object[] {pAccion});
    }
    
    public void cerrar()
    {
    	setChanged();
    	notifyObservers(new Object[] {"cerrar"});
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
        HashSet<String> targets = Arrays.stream(pTargets)
                .collect(Collectors.toCollection(HashSet::new));
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
    		
    		}, 0000);//cooldown
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
	
	protected Casilla getCasilla(int pX, int pY)
	{
		return tablero[pX][pY];
	}
	
	protected Random getRandom()
	{
		if(random == null) {random = new Random();}
		return random;
	}
	
	public void pausar(String pSituacion, boolean pantalla)
	{
		Arrays.stream(tablero).flatMap(a -> Arrays.stream(a)).forEach(a -> a.pause());
		if(pantalla)
		{
			if(pSituacion.equals("pause"))
			{
				PantallaPausa("pausar");
			}
			else if(pSituacion.equals("lose"))
			{
				PantallaPausa("perder");
			}
			else if(pSituacion.equals("win"))
			{
				PantallaPausa("ganar");
			}
		}
	}
	
	public void seguir()
	{
		Arrays.stream(tablero).flatMap(a -> Arrays.stream(a)).forEach(a -> a.goOn());
		PantallaPausa("continuar");
	}
	
	public void sumarEnemigos(int pCantidad)
	{
		if(enemigos + pCantidad >= 0)
		{
			enemigos = enemigos + pCantidad;
		}
		else {enemigos = 0;}
	}
	
	public void comprobarVictoria()
	{
		if(enemigos == 0) {pausar("win",true);}
	}
  
}
