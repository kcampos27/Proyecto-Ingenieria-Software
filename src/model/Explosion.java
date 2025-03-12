package model;

public class Explosion {

	public static void explotar(int x, int y, int rango) {
        TableroModel board = TableroModel.getMiTablero();
        for (int dx = -rango; dx <= rango; dx++) { //Da el fuego en el -X y +X segun el rango
            for (int dy = -rango; dy <= rango; dy++) { // Da el fuego en el -Y y +Y segun el rango
                int newX = x + dx; // Nueva posición en X.
                int newY = y + dy; // Nueva posición en Y.
				Casilla unaCasilla = board.getCasilla(newX, newY);
                if (newX >= 0 && newY >= 0 && newX < board.getAncho() && newY < board.getAlto()) {
                    if (unaCasilla.getContent()!="BloqueD") { // PREGUNTAR Asegura que no afecta bloques duros.
                        unaCasilla.setContent("*"); // Marca la celda con fuego.
                    }
                }
            }
        }
	}
   
	public static void explosionUltra(int x,int y, int rango) {
		TableroModel board = TableroModel.getMiTablero();
		for(int dx= -rango; dx<= rango;dx++) {
			for(int dy= -rango; dy<= rango;dy++) {
				int newX = x + dx;
				int newY= y + dy;
				Casilla unaCasilla = board.getCasilla(newX, newY);
				if(newX >= 0 && newY >= 0 && newX < board.getAncho() && newY < board.getAlto()) {
					if(unaCasilla .getContent()!="BloqueD") { //Pregunta si hay un bloque duro
						unaCasilla.setContent("*");
					}
				}	
			}	
		}	
	
	}	
	
	
	
	public static void limpiarExplo(int x, int y, int rango) {
		TableroModel board = TableroModel.getMiTablero();
            for (int dx = -rango; dx <= rango; dx++) {
                for (int dy = -rango; dy <= rango; dy++) {
                    int newX = x + dx;
                    int newY = y + dy;
    				Casilla unaCasilla = board.getCasilla(newX, newY);
                    if (newX >= 0 && newY >= 0 && newX < board.getAncho() && newY < board.getAlto()) {
                        if (unaCasilla.getContent() == "*") {
                            unaCasilla.setContent("") ; // Limpia la celda.
                        }
                    }
                }
            }
	    }  

}
