package model;

public class Explosion extends Elemento {

	protected Explosion(String pNombre) {
		super(pNombre);
	}

	public static void limpiarExplo(int x, int y, int rango) {
		TableroModel board = TableroModel.getMiTablero();
            for (int dx = -rango; dx <= rango; dx++) {
                for (int dy = -rango; dy <= rango; dy++) {
                    int newX = x + dx;
                    int newY = y + dy;
                    if (newX >= 0 && newY >= 0 && newX < board.getAncho() && newY < board.getAlto()) {
                        if (board.getContent(newX, newY).equals("*")) {
                            board.cambiarContent(newX, newY, ""); // Limpia la celda.
                        }
                    }
                }
            }
	    }  
}

