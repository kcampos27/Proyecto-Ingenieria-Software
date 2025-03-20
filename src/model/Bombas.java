package model;

import java.util.Timer;
import java.util.TimerTask;

public class Bombas {
    private int x;
    private int y;
    private int rango;
    private boolean haExplo;
    private Timer timer;

    public Bombas(int x, int y, int rango) {
        this.x = x;
        this.y = y;
        this.rango = rango;
        this.haExplo = false;
        this.timer = new Timer();
        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
        // Temporizador para explotar después de 3 segundos
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                explotar();
                haExplo = true;

                // Después de 2 segundos limpia el fuego
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        limpiarExplo(x, y, rango);
                        timer.cancel();
                    }
                }, 2000); // 2000ms = 2 segundos
            }
        }, 3000); // 3000ms = 3 segundos
    }

    public void explotar() {
    	System.out.println("PUM");
        TableroModel board = TableroModel.getMiTablero();
        for (int dx = -rango; dx <= rango; dx++) {
            for (int dy = -rango; dy <= rango; dy++) {
                int newX = x + dx;
                int newY = y + dy;
                if (newX >= 0 && newY >= 0 && newX < board.getAncho() && newY < board.getAlto()) {
                    if (!(board.getContent(newX, newY).equals("bloqueD"))) {
                        board.setContent(newX, newY, "*");
                    }
                }
            }
        }
    }

    public void limpiarExplo(int x, int y, int rango) {
        TableroModel board = TableroModel.getMiTablero();
        for (int dx = -rango; dx <= rango; dx++) {
            for (int dy = -rango; dy <= rango; dy++) {
                int newX = x + dx;
                int newY = y + dy;
                if (newX >= 0 && newY >= 0 && newX < board.getAncho() && newY < board.getAlto()) {
                    if (board.getContent(newX, newY).equals("*")) {
                        board.setContent(newX, newY, "");
                    }
                }
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