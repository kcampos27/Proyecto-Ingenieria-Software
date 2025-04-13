package model;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Bombas extends Elemento {
    private int rango;
    private boolean haExplo;
    private Timer timer;

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
                explotar();
                haExplo = true;

                //limpia el fuego
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        limpiarExplo(x, y, rango);
                        x=-1;y=-1;
                        timer.cancel();
                    }
                }, 1000); // 1000ms = 1 segundo
            }
        }, 1000); 
    }

    public void explotar() {
        if (!haExplo) {
            System.out.println("PUM");
            
            
            // Explosión central (la posición de la bomba)
            Gestor.getInstance().getTablero().eliminarContent(x, y, "bombaS");
            Gestor.getInstance().getTablero().generarContent(x, y, "*");
            Gestor.getInstance().getTablero().damage(x,y, 1, new String[] {"enemigo","bombermanW","bloqueB"});
            
            // Explosión en las cuatro direcciones (arriba, abajo, izquierda, derecha)
            for (int i = 1; i <= rango; i++) {
                // Explosión hacia la derecha
                if (x + i < Gestor.getInstance().getTablero().getAncho() && !Gestor.getInstance().getTablero().casillaIncluye(x + i, y,"bloqueD"))
                {
                	Gestor.getInstance().getTablero().generarContent(x + i, y, "*");
                	Gestor.getInstance().getTablero().damage(x + i,y, 1, new String[] {"enemigo","bombermanW","bloqueB"});
                }
                // Explosión hacia la izquierda
                if (x - i >= 0 && !Gestor.getInstance().getTablero().casillaIncluye(x - i, y,"bloqueD")) 
                {
                	Gestor.getInstance().getTablero().generarContent(x - i, y, "*");
                	Gestor.getInstance().getTablero().damage(x - i,y, 1, new String[] {"enemigo","bombermanW","bloqueB"});
                }
                // Explosión hacia abajo
                if (y + i < Gestor.getInstance().getTablero().getAlto() && !Gestor.getInstance().getTablero().casillaIncluye(x, y + i,"bloqueD")) 
                {
                	Gestor.getInstance().getTablero().generarContent(x, y + i, "*");
                	Gestor.getInstance().getTablero().damage(x,y + i, 1, new String[] {"enemigo","bombermanW","bloqueB"});
                }
                // Explosión hacia arriba
                if (y - i >= 0 && !Gestor.getInstance().getTablero().casillaIncluye(x, y - i,"bloqueD")) 
                {
                	Gestor.getInstance().getTablero().generarContent(x, y - i, "*");
                	Gestor.getInstance().getTablero().damage(x,y - 1, 1, new String[] {"enemigo","bombermanW","bloqueB"});
                }
            }
        }
    }

    public void limpiarExplo(int x, int y, int rango) {
        
        // Limpiar explosión central
    	Gestor.getInstance().getTablero().eliminarContent(x, y, "*");

        // Limpiar explosión en las cuatro direcciones
        for (int i = 1; i <= rango; i++) {
            // Limpiar hacia la derecha
            if (x + i < Gestor.getInstance().getTablero().getAncho() && Gestor.getInstance().getTablero().casillaIncluye(x + i, y, "*")) {
            	Gestor.getInstance().getTablero().eliminarContent(x + i, y, "*");
            }
            // Limpiar hacia la izquierda
            if (x - i >= 0 && Gestor.getInstance().getTablero().casillaIncluye(x - i, y, "*")) {
            	Gestor.getInstance().getTablero().eliminarContent(x - i, y, "*");
            }
            // Limpiar hacia abajo
            if (y + i < Gestor.getInstance().getTablero().getAlto() && Gestor.getInstance().getTablero().casillaIncluye(x, y + i, "*")) {
            	Gestor.getInstance().getTablero().eliminarContent(x, y + i, "*");
            }
            // Limpiar hacia arriba
            if (y - i >= 0 && Gestor.getInstance().getTablero().casillaIncluye(x, y - i, "*")) {
            	Gestor.getInstance().getTablero().eliminarContent(x, y - i, "*");
            }
        }
    }

    public boolean getHaExplo() {
        return haExplo;
    }

    public int getRango() { return rango; }
}