package model;

public abstract class Bombas {
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
	public void update(TableroModel  ) {
	    if (timer > 0) {
	        timer--; // Reduce el temporizador hasta que explote.
	    } else if (!haExplo) {
	        explotada(); // Marca la bomba como explotada.
	        explotar(tablero); // Aplica la explosión al tablero
	    } else if (duracionExplo > 0) {
	        duracionExplo--; // Reduce la duración del fuego.
	    } else {
	        Explosion.limpiarExplo(x, y, rango); // Limpia el fuego.
	    }
	}
	// hay que poner este metodo cuando este el tablero hecho
	 public abstract void explotar(TableroModel tablero);
}
