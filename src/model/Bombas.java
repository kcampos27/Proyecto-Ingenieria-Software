package model;

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
            TableroModel.getMiTablero().eliminarContent(x, y, "bombaS");
            TableroModel.getMiTablero().aniadirContent(x, y, "*");
            TableroModel.getMiTablero().damage(x,y, 1);
            
            // Explosión en las cuatro direcciones (arriba, abajo, izquierda, derecha)
            for (int i = 1; i <= rango; i++) {
                // Explosión hacia la derecha
                if (x + i < TableroModel.getMiTablero().getAncho() && !TableroModel.getMiTablero().casillaIncluye(x + i, y,"bloqueD"))
                {
                	TableroModel.getMiTablero().aniadirContent(x + i, y, "*");
                	TableroModel.getMiTablero().damage(x + i,y, 1);
                }
                // Explosión hacia la izquierda
                if (x - i >= 0 && !TableroModel.getMiTablero().casillaIncluye(x - i, y,"bloqueD")) 
                {
                	TableroModel.getMiTablero().aniadirContent(x - i, y, "*");
                	TableroModel.getMiTablero().damage(x - i,y, 1);
                }
                // Explosión hacia abajo
                if (y + i < TableroModel.getMiTablero().getAlto() && !TableroModel.getMiTablero().casillaIncluye(x, y + i,"bloqueD")) 
                {
                	TableroModel.getMiTablero().aniadirContent(x, y + i, "*");
                	TableroModel.getMiTablero().damage(x,y + i, 1);
                }
                // Explosión hacia arriba
                if (y - i >= 0 && !TableroModel.getMiTablero().casillaIncluye(x, y - i,"bloqueD")) 
                {
                	TableroModel.getMiTablero().aniadirContent(x, y - i, "*");
                	TableroModel.getMiTablero().damage(x,y - 1, 1);
                }
            }
        }
    }

    public void limpiarExplo(int x, int y, int rango) {
        
        // Limpiar explosión central
    	TableroModel.getMiTablero().eliminarContent(x, y, "*");

        // Limpiar explosión en las cuatro direcciones
        for (int i = 1; i <= rango; i++) {
            // Limpiar hacia la derecha
            if (x + i < TableroModel.getMiTablero().getAncho() && TableroModel.getMiTablero().casillaIncluye(x + i, y, "*")) {
            	TableroModel.getMiTablero().eliminarContent(x + i, y, "*");
            }
            // Limpiar hacia la izquierda
            if (x - i >= 0 && TableroModel.getMiTablero().casillaIncluye(x - i, y, "*")) {
            	TableroModel.getMiTablero().eliminarContent(x - i, y, "*");
            }
            // Limpiar hacia abajo
            if (y + i < TableroModel.getMiTablero().getAlto() && TableroModel.getMiTablero().casillaIncluye(x, y + i, "*")) {
            	TableroModel.getMiTablero().eliminarContent(x, y + i, "*");
            }
            // Limpiar hacia arriba
            if (y - i >= 0 && TableroModel.getMiTablero().casillaIncluye(x, y - i, "*")) {
            	TableroModel.getMiTablero().eliminarContent(x, y - i, "*");
            }
        }
    }

    public boolean getHaExplo() {
        return haExplo;
    }

    public int getRango() { return rango; }
}