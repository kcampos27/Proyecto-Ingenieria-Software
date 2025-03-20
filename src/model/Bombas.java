package model;

public  class Bombas {
	private int x; //Coordenadas de x de la bomba
	private int y; //Coordenadas de y de la bomba
	private int rango; //Rango de la bomba
	private boolean haExplo; // Ha explotado o no
	private int timer; // Temporizador (en ciclos de 1 segundo) para la explosión.
	private int duracionExplo; // Duración de la explosión (en ciclos de 1 segundo).


	public Bombas (int x, int y, int rango) { //Constructora 
		this.x = x;
		this.y = y;
		this.rango = rango;
		this.haExplo = false;
		this.timer = 3; // Explota después de 3 ciclos (3 segundos).
	    this.duracionExplo = 2; // El fuego dura 2 ciclos (2 segundos).
	}
	public int getX() {
		return x;
		
	}
	public int getY() {
		return y;
	}
	public int getRango() {
		return rango;
	}
	public boolean getHaExplo() {
		return haExplo;
	}
	
	public void explotada() { //Marca la bomba como explotada
		this.haExplo = true;
		
	}
	
	public  void explotar() {
        TableroModel board = TableroModel.getMiTablero();
        for (int dx = -rango; dx <= rango; dx++) { //Da el fuego en el -X y +X segun el rango
            for (int dy = -rango; dy <= rango; dy++) { // Da el fuego en el -Y y +Y segun el rango
                int newX = x + dx; // Nueva posición en X.
                int newY = y + dy; // Nueva posición en Y.
                if (newX >= 0 && newY >= 0 && newX < board.getAncho() && newY < board.getAlto()) {
                    if (!(board.getContent(newX, newY).equals("bloqueD"))) { // PREGUNTAR Asegura que no afecta bloques duros.
                        board.setContent(newX,newY,"*"); // Marca la celda con fuego.
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
                            board.setContent(newX, newY, ""); // Limpia la celda.
                        }
                    }
                }
            }
	    }   
	public void update() {
	    if (timer > 0) {
	        timer--; // Reduce el temporizador hasta que explote.
	    } else if (!haExplo) {
	        explotada(); // Marca la bomba como explotada.
	        explotar(); // Aplica la explosión al tablero
	    } else if (duracionExplo > 0) {
	        duracionExplo--; // Reduce la duración del fuego.
	    } else {
	        limpiarExplo(x, y, rango); // Limpia el fuego.
	    }
	}
	
}
