package model;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Bombas extends Elemento {
    private int rango;
    private boolean haExplo;
    private Timer timer;
    private boolean pausado = false;

	public Bombas(int px, int py, int rango, String pTipo) {
        super(px,py,pTipo,-1);
        this.rango = rango;
        this.haExplo = false;
        this.timer = new Timer();
        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
        // Temporizador para explotar 
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            	try 
            	{   		
            		if(pausado)
            		{
            			throw new PausadoException();
            		}
            		else
            		{
            			explotar();
            			haExplo = true;

            			//limpia el fuego
            			timer.schedule(new TimerTask() {
            				@Override
            				public void run() {
            					limpiarExplo(getX(), getY(), rango);
            					setX(-1);setY(-1);
            					timer.cancel();
            				}
            			}, 1000); // 1000ms = 1 segundo
            		}
            	}
            	catch (PausadoException pe)
            	{
            		timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            iniciarTemporizador();  // vuelve a intentarlo
                        }
                    }, 200);
            	}
            }
        }, 1000); 
    }

    public void explotar() {
        if (!haExplo) {
            System.out.println("PUM");

            // Explosión central (la posición de la bomba)
            Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), "bombaS");
            Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), "bombaU");
            Gestor.getInstance().getTablero().generarContent(getX(), getY(), "*");
            Gestor.getInstance().getTablero().damage(getX(), getY(), 1, new String[]{"enemigoR","enemigo", "bombermanW", "bombermanN" ,"bloqueB"});

            // Banderas para cada dirección
            boolean derechaActiva = true;
            boolean izquierdaActiva = true;
            boolean abajoActiva = true;
            boolean arribaActiva = true;

            for (int i = 1; i <= rango; i++) {
                // Derecha
                if (derechaActiva && getX() + i < Gestor.getInstance().getTablero().getAncho()) {
                    if (Gestor.getInstance().getTablero().casillaIncluye(getX() + i, getY(), "bloqueD")) {
                        derechaActiva = false;
                    } else {
                        Gestor.getInstance().getTablero().generarContent(getX() + i, getY(), "*");
                        Gestor.getInstance().getTablero().damage(getX() + i, getY(), 1, new String[]{"enemigoR","enemigo","bombermanN", "bombermanW", "bloqueB"});
                    }
                }

                // Izquierda
                if (izquierdaActiva && getX() - i >= 0) {
                    if (Gestor.getInstance().getTablero().casillaIncluye(getX() - i, getY(), "bloqueD")) {
                        izquierdaActiva = false;
                    } else {
                        Gestor.getInstance().getTablero().generarContent(getX() - i, getY(), "*");
                        Gestor.getInstance().getTablero().damage(getX() - i, getY(), 1, new String[]{"enemigoR","enemigo","bombermanN", "bombermanW", "bloqueB"});
                    }
                }

                // Abajo
                if (abajoActiva && getY() + i < Gestor.getInstance().getTablero().getAlto()) {
                    if (Gestor.getInstance().getTablero().casillaIncluye(getX(), getY() + i, "bloqueD")) {
                        abajoActiva = false;
                    } else {
                        Gestor.getInstance().getTablero().generarContent(getX(), getY() + i, "*");
                        Gestor.getInstance().getTablero().damage(getX(), getY() + i, 1, new String[]{"enemigoR","enemigo", "bombermanW","bombermanN", "bloqueB"});
                    }
                }

                // Arriba
                if (arribaActiva && getY() - i >= 0) {
                    if (Gestor.getInstance().getTablero().casillaIncluye(getX(), getY() - i, "bloqueD")) {
                        arribaActiva = false;
                    } else {
                        Gestor.getInstance().getTablero().generarContent(getX(), getY() - i, "*");
                        Gestor.getInstance().getTablero().damage(getX(), getY() - i, 1, new String[]{"enemigoR","enemigo", "bombermanW","bombermanN", "bloqueB"});
                    }
                }
            }
        }
        BomberMan.getMiBomberMan().bombaExploto();
    }


    public void limpiarExplo(int x, int y, int rango) {
        // Limpiar explosión central
        Gestor.getInstance().getTablero().eliminarContent(x, y, "*");

        // Banderas para controlar hasta dónde limpiar en cada dirección
        boolean derechaActiva = true;
        boolean izquierdaActiva = true;
        boolean abajoActiva = true;
        boolean arribaActiva = true;

        for (int i = 1; i <= rango; i++) {
            // Derecha
            if (derechaActiva && x + i < Gestor.getInstance().getTablero().getAncho()) {
                if (Gestor.getInstance().getTablero().casillaIncluye(x + i, y, "bloqueD")) {
                    derechaActiva = false;
                } else {
                    Gestor.getInstance().getTablero().eliminarContent(x + i, y, "*");
                }
            }

            // Izquierda
            if (izquierdaActiva && x - i >= 0) {
                if (Gestor.getInstance().getTablero().casillaIncluye(x - i, y, "bloqueD")) {
                    izquierdaActiva = false;
                } else {
                    Gestor.getInstance().getTablero().eliminarContent(x - i, y, "*");
                }
            }

            // Abajo
            if (abajoActiva && y + i < Gestor.getInstance().getTablero().getAlto()) {
                if (Gestor.getInstance().getTablero().casillaIncluye(x, y + i, "bloqueD")) {
                    abajoActiva = false;
                } else {
                    Gestor.getInstance().getTablero().eliminarContent(x, y + i, "*");
                }
            }

            // Arriba
            if (arribaActiva && y - i >= 0) {
                if (Gestor.getInstance().getTablero().casillaIncluye(x, y - i, "bloqueD")) {
                    arribaActiva = false;
                } else {
                    Gestor.getInstance().getTablero().eliminarContent(x, y - i, "*");
                }
            }
        }
    }

    public boolean getHaExplo() {
        return haExplo;
    }

    public int getRango() { return rango; }
    
    @Override
    public void detener()
    {
    	pausado = true;
    }
    
    @Override
    public void continuar()
    {
    	pausado = false;
    }
}