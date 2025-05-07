package model;

import java.util.Timer;
import java.util.TimerTask;

public class EnemigoVeloz extends Enemigo{

	public EnemigoVeloz(int pX, int pY, String pName) {
		super(pX, pY, pName, 1);
	}
	
	@Override
	public void movimiento()
	{
		getTimer().schedule(new TimerTask() //delay 1 segundo antes de empezar a moverse, evitar que no se hayan generado casillas
		{
			@Override
			public void run()
			{
				
				//System.out.println("enemigo: me muevo desde "+x+","+y);
				TimerTask timerTask = new TimerTask() {
					int[] coords;
					@Override
					public void run() {
						if(!estaPausado()) 
						{
							coords = randomcoords();
							mover(coords[0],coords[1]);
						}
					}
				};
				setTimer(new Timer());
				getTimer().scheduleAtFixedRate(timerTask, 0, 650);//Movimiento cada 0.65 segundos
			}
		
		}, 1000);
    	
	}
	
	public synchronized void mover(int pX, int pY)
	{
		int nextX = getX() + pX;
    	int nextY = getY() + pY;
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
    	    	Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), getNombre());
    	    	setX(nextX);
    	    	setY(nextY);
    	    	if(pX==0) 
    	    	{
    	    		if(pY==0){Gestor.getInstance().getTablero().aniadirContent(getX(), getY(), this);}
    	    		if(pY==1){Gestor.getInstance().getTablero().aniadirContent(getX(), getY(), this);}
    	    		else if(pY==-1){Gestor.getInstance().getTablero().aniadirContent(getX(), getY(), this);}
    	    	}
    	    	else if(pX==1){Gestor.getInstance().getTablero().aniadirContent(getX(), getY(), this);}
    	    	else if(pX==-1){Gestor.getInstance().getTablero().aniadirContent(getX(), getY(), this);}
    	    	
    	    	//Daniar a bomberman: HACEN 2 DE DMG!!!
    	    	if(Gestor.getInstance().getTablero().casillaIncluye(getX(), getY(), BomberMan.getMiBomberMan().getNombre()))
    	    	{Gestor.getInstance().getTablero().damage(getX(), getY(), 2, new String[] {BomberMan.getMiBomberMan().getNombre()});}
    	    	//Recibir danio si toca explosion
    	    	if(Gestor.getInstance().getTablero().casillaIncluye(getX(), getY(), "*"))
    	    	{Gestor.getInstance().getTablero().damage(getX(), getY(), 1, new String[] {"enemigoV"});}
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
	@Override
    public boolean posibleMoverse(int pX, int pY) {
        boolean moverse = true;

        // Este tipo de enemigos no se mueven hacia explosiones
        if (Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "*")	||
        	Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "+")) 
        {
            moverse = false;
            //System.out.println("Movimiento bloqueado: casilla (" + pX + ", " + pY + ") contiene un obstáculo.");
        }

        // Regla general de movimiento del enemigo
        return moverse && super.posibleMoverse(pX, pY);
    }
	

}
