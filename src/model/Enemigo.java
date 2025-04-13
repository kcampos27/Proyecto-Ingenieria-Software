package model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Enemigo extends Elemento{
	
	private Timer timer;
	
	public Enemigo(int pX, int pY,String pName)
	{
		super(pX, pY, pName, 1);
		timer = new Timer();
		movimiento();
	}
	
	public String getName()
	{
		return nombre;
	}
	
	public void movimiento()
	{
		timer.schedule(new TimerTask() //delay 1 segundo antes de empezar a moverse, evitar que no se hayan generado casillas
		{
			@Override
			public void run()
			{
				
				//System.out.println("enemigo: me muevo desde "+x+","+y);
				TimerTask timerTask = new TimerTask() {
					int[] coords;
					@Override
					public void run() {
						coords = randomcoords();
						mover(coords[0],coords[1]);
					}
				};
				timer = new Timer();
				timer.scheduleAtFixedRate(timerTask, 0, 1000);//Movimiento cada segundo
				
			}
		
		}, 1000);
    	
	}
	
	public synchronized void mover(int pX, int pY)
	{
		int nextX = this.x + pX;
    	int nextY = this.y + pY;
    	//System.out.println("");
    	//System.out.println("enemigo: parametros: "+pX+", "+pY);
    	//System.out.println("enemigo: solicitado moverse a la casilla "+nextX+", "+nextY);
    	
    	if (nextX>=0 && nextY>=0 &&
            	nextX<=16 && nextY<=10)
        {
    	    if (posibleMoverse(nextX,nextY))
    	    {
    	    	System.out.println("");
				System.out.println("______________________________________________");
    	    	Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
    	    	x = nextX;
    	    	y = nextY;
    	    	if(pX==0) 
    	    	{
    	    		//if(pY==0){Gestor.getInstance().getTablero().aniadirContent(x, y, this);}
    	    		if(pY==1){Gestor.getInstance().getTablero().aniadirContent(x, y, this);}
    	    		else if(pY==-1){Gestor.getInstance().getTablero().aniadirContent(x, y, this);}
    	    	}
    	    	else if(pX==1){Gestor.getInstance().getTablero().aniadirContent(x, y, this);}
    	    	else if(pX==-1){Gestor.getInstance().getTablero().aniadirContent(x, y, this);}
    	    	
    	    	//Daniar a bomberman
    	    	if(Gestor.getInstance().getTablero().casillaIncluye(x, y, BomberMan.getMiBomberMan().getNombre()))
    	    	{Gestor.getInstance().getTablero().damage(x, y, 1, new String[] {"bombermanW"});}
    	    	//Recibir danio si toca explosion
    	    	if(Gestor.getInstance().getTablero().casillaIncluye(x, y, "*"))
    	    	{Gestor.getInstance().getTablero().damage(x, y, 1, new String[] {"enemigo"});}
    	    	//System.out.println(nextX+","+nextY);
    	    	System.out.println("");
				System.out.println("______________________________________________");
    	    }
    	    
    	    else
    	    {//System.out.print("bloqueado por ");
    	    	//Gestor.getInstance().getTablero().printContent(nextX, nextY);
    	    }
    	    	
        }
        else
        {//System.out.println("mov erroneo");
        }
    }
	
	public int[] randomcoords()
	{
		int[] coords = new int[2];
		coords[0] = 1; coords[1] = 0;
		
		Random r = new Random();
		int dir = r.nextInt(4);
		
		if(dir == 0)//up
		{ coords[0] = 0; coords[1] = 1; }
		else if(dir == 1)//down
		{ coords[0] = 0; coords[1] = -1; }
		else if(dir == 2)//left
		{ coords[0] = -1; coords[1] = 0; }
		else if(dir == 3)//right
		{ coords[0] = 1; coords[1] = 0; }
		
		return coords;
	}
	
	public boolean posibleMoverse(int pX, int pY)
    {
    	boolean moverse = false;
    	
    	if(!Gestor.getInstance().getTablero().casillaIncluye(pX, pY, nombre)
    			&& !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueD")
    			&& !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueB")
    			&& !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bombaS")
    			&& !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "enemigo"))
    	{
    		moverse = true;
    	}
    	
    	return moverse;
    }

	@Override
	public void getHurt(int pDmg) 
	{
		if(vida>0) 
    	{
    		if(vida - pDmg == 0) {vida = 0;}
    		else {vida = vida - pDmg;}
    	}
    	if(vida==0) 
    	{
    		Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
    		x=-1;
    		y=-1;
    		timer.cancel();
    	}
	}
	
	public void stop()
	{
		timer.cancel();
		Gestor.getInstance().getTablero().eliminarContent(x,y,nombre);
		x=-1;
		y=-1;
	}
}
