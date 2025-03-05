package proyecto;

public class Explosion {

	public static void explotar(TableroModel tablero, int x, int y, int rango) {
        char[][] board = TableroModel.getMiTablero();
        for (int dx = -rango; dx <= rango; dx++) { //Da el fuego en el -X y +X segun el rango
            for (int dy = -rango; dy <= rango; dy++) { // Da el fuego en el -Y y +Y segun el rango
                int newX = x + dx; // Nueva posición en X.
                int newY = y + dy; // Nueva posición en Y.

                if (newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length) {
                    if (board[newX][newY] != 'X') { // PREGUNTAR Asegura que no afecta bloques duros.
                        board[newX][newY] = '*'; // Marca la celda con fuego.
                    }
                }
            }
        }
	}
   
	public static void explosionUltra(TableroModel tablero,int x,int y, int rango) {
		char[][] board = TableroModel.getMiTablero();
		for(int dx= -rango; dx<= rango;dx++) {
			for(int dy= -rango; dy<= rango;dy++) {
				int newX = x + dx;
				int newY= y + dy;
				if(newX >= 0 && newY >= 0 && newX<board.length && newY<board[0].length) {
					if(board[newX][newY]!='X') {
						board[newX][newY] = '*';
					}
				}	
			}	
		}	
	}	
	
	
	public static void limpiarExplo(TableroModel tablero, int x, int y, int rango) {
            char[][] board = TableroModel.getMiTablero();
            for (int dx = -rango; dx <= rango; dx++) {
                for (int dy = -rango; dy <= rango; dy++) {
                    int newX = x + dx;
                    int newY = y + dy;
if (newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length) {
                        if (board[newX][newY] == '*') {
                            board[newX][newY] = ' '; // Limpia la celda.
                        }
                    }
                }
            }
	    }  
}
