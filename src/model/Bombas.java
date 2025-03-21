package model;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Bombas extends Elemento {
    private int x;
    private int y;
    private int rango;
    private boolean haExplo;
    private Timer timer;

    @SuppressWarnings("deprecation")
	public Bombas(int px, int py, int rango, String pTipo) {
        super(pTipo);
    	this.x = px;
        this.y = py;
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
            TableroModel board = TableroModel.getMiTablero();
            
            // Explosión central (la posición de la bomba)
            board.cambiarContent(x, y, "*");

            // Explosión en las cuatro direcciones (arriba, abajo, izquierda, derecha)
            for (int i = 1; i <= rango; i++) {
                // Explosión hacia la derecha
                if (x + i < board.getAncho() && !board.getContent(x + i, y).equals("bloqueD")) {
                    board.cambiarContent(x + i, y, "*");
                }
                // Explosión hacia la izquierda
                if (x - i >= 0 && !board.getContent(x - i, y).equals("bloqueD")) {
                    board.cambiarContent(x - i, y, "*");
                }
                // Explosión hacia abajo
                if (y + i < board.getAlto() && !board.getContent(x, y + i).equals("bloqueD")) {
                    board.cambiarContent(x, y + i, "*");
                }
                // Explosión hacia arriba
                if (y - i >= 0 && !board.getContent(x, y - i).equals("bloqueD")) {
                    board.cambiarContent(x, y - i, "*");
                }
            }
        }
    }

    public void limpiarExplo(int x, int y, int rango) {
        TableroModel board = TableroModel.getMiTablero();
        
        // Limpiar explosión central
        board.cambiarContent(x, y, "");

        // Limpiar explosión en las cuatro direcciones
        for (int i = 1; i <= rango; i++) {
            // Limpiar hacia la derecha
            if (x + i < board.getAncho() && board.getContent(x + i, y).equals("*")) {
                board.cambiarContent(x + i, y, "");
            }
            // Limpiar hacia la izquierda
            if (x - i >= 0 && board.getContent(x - i, y).equals("*")) {
                board.cambiarContent(x - i, y, "");
            }
            // Limpiar hacia abajo
            if (y + i < board.getAlto() && board.getContent(x, y + i).equals("*")) {
                board.cambiarContent(x, y + i, "");
            }
            // Limpiar hacia arriba
            if (y - i >= 0 && board.getContent(x, y - i).equals("*")) {
                board.cambiarContent(x, y - i, "");
            }
        }
    }

    public boolean getHaExplo() {
        return haExplo;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getRango() { return rango; }
}