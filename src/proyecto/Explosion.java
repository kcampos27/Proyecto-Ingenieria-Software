package proyecto;

public class Explosion {

	public static void explotar(TableroModel tablero, int x, int y, int rango) {
        char[][] board = TableroModel.getMiTablero();
        for (int dx = -rango; dx <= rango; dx++) { // Mueve en el eje X.
            for (int dy = -rango; dy <= rango; dy++) { // Mueve en el eje Y.
                int newX = x + dx; // Nueva posición en X.
                int newY = y + dy; // Nueva posición en Y.

                if (newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length) {
                    if (board[newX][newY] != 'X') { // Asegura que no afecta bloques duros.
                        board[newX][newY] = '*'; // Marca la celda con fuego.
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
