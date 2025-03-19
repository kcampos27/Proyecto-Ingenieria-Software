package model;

public abstract class Bloque extends Elemento{
	
	private int x;
	private int y;
	
	public Bloque(int px, int py, String pNombre)
	{
		super(pNombre);
		x = px;
		y = py;
	}

}
