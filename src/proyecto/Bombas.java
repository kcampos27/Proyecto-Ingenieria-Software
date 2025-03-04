package codigoBombas;

public class Bombas {
	private int x; //Coordenadas de x de la bomba
	private int y; //Coordenadas de y de la bomba
	private int rango; //Rango de la bomba
	private boolean haExplo; // Ha explotado o no


public Bombas (int x, int y, int rango) { //Constructora 
	this.x = x;
	this.y = y;
	this.rango = rango;
	this.haExplo = false;
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
// hay que poner este metodo cuando este el tablero hecho
// public abstract void ExploTab (Tablero tablero);
}
