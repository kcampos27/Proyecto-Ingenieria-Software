package model;

import java.util.Timer;
import java.util.TimerTask;

public class EnemigoConRango extends Enemigo {
	
	private boolean cargado = false;
	private boolean explotando = false;

    public EnemigoConRango(int pX, int pY, String pName) {
        super(pX, pY, pName,2);
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
						if(!estaPausado() && !cargado) 
						{
							coords = randomcoords();
							mover(coords[0],coords[1]);
						}
						else if(cargado)
						{
							//infligir daño en casillas adyacentes
							cargado = false;
							explotando = true;
							infligirDmgEnRango();
							// Limpiar las explosiones
							getTimer().schedule(new TimerTask()
							{
								@Override
								public void run() 
								{
					                limpiarExplosiones();
					                explotando = false;
								}}, 1000);
						}
					}
				};
				setTimer(new Timer());
				getTimer().scheduleAtFixedRate(timerTask, 0, 2000);//Movimiento cada 2 segundos
			}
		
		}, 1000);
    	
	}
    
    @Override
    public synchronized void mover(int pX, int pY) {
        int nextX = getX() + pX;
        int nextY = getY() + pY;

        if (nextX >= 0 && nextY >= 0 && nextX <= 16 && nextY <= 10) {
            if (posibleMoverse(nextX, nextY)) {
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
               
                //Daniar a bomberman
    	    	if(Gestor.getInstance().getTablero().casillaIncluye(getX(), getY(), BomberMan.getMiBomberMan().getNombre()))
    	    	{Gestor.getInstance().getTablero().damage(getX(), getY(), 1, new String[] {BomberMan.getMiBomberMan().getNombre()});}
    	    	
                // Recibir daño si toca una explosión
                if (Gestor.getInstance().getTablero().casillaIncluye(getX(), getY(), "*")) {
                    Gestor.getInstance().getTablero().damage(getX(), getY(), 1, new String[]{"enemigoR"});
                }

                System.out.println("");
                System.out.println("______________________________________________");
                //Se carga si se ha movido
				cargado = true;
            }
            
        }
    }
    @Override
	public void getHurt(int pDmg) 
	{
		if(getVida()>0) 
    	{
    		if(getVida() - pDmg >= 0) {matar();}
    		else {restarVida(pDmg);}
    	}
    	if(getVida() == 0) 
    	{
    		try {
    			//el enemigo no puede morir si no se ha borrado su explosion
    			if(explotando) {throw new EnemigoExplotandoException();}
    			Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), getNombre());
    			setX(-1);
    			setY(-1);
    			getTimer().cancel();
    			Gestor.getInstance().getTablero().sumarEnemigos(-1);
    			Gestor.getInstance().getTablero().comprobarVictoria();
    		}
    		catch(EnemigoExplotandoException eee)
    		{
    			//reintentar hasta que se pueda matar al enemigo	
    			getTimer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                    	getHurt(pDmg);
                    }
                }, 200);
    		}
    	}
	}

    private void infligirDmgEnRango() {
        // Coordenadas de las casillas adyacentes
        int[][] direcciones = {
            {0, 1},  // Arriba
            {0, -1}, // Abajo
            {-1, 0}, // Izquierda
            {1, 0}   // Derecha
        };

        for (int[] dir : direcciones) {
            int targetX = getX() + dir[0];
            int targetY = getY() + dir[1];

            if (targetX >= 0 && targetY >= 0 && targetX <= 16 && targetY <= 10) {
                // Verificar si hay bloques duros, blandos o enemigos
                if (!Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, "bloqueD") &&
                    !Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, "bloqueB") &&
                    !Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, "enemigo")) {

                    // Colocar el ícono de daño
                    Gestor.getInstance().getTablero().generarContent(targetX, targetY, "+");

                    // Daño únicamente a Bomberman
                    if (Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, BomberMan.getMiBomberMan().getNombre())) {
                        Gestor.getInstance().getTablero().damage(targetX, targetY, 1, new String[]{BomberMan.getMiBomberMan().getNombre()});
                        System.out.println("Enemigo inflige daño a Bomberman en la casilla (" + targetX + ", " + targetY + ").");
                    }
                }
            }
        }
    }

    private void limpiarExplosiones() {
        // Coordenadas de las casillas adyacentes
        int[][] direcciones = {
            {0, 1},  // Arriba
            {0, -1}, // Abajo
            {-1, 0}, // Izquierda
            {1, 0}   // Derecha
        };

        for (int[] dir : direcciones) {
            int targetX = getX() + dir[0];
            int targetY = getY() + dir[1];

            if (targetX >= 0 && targetY >= 0 && targetX <= 16 && targetY <= 10) {
                // Eliminar explosiones previas
                Gestor.getInstance().getTablero().eliminarContent(targetX, targetY, "+");
            }
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

