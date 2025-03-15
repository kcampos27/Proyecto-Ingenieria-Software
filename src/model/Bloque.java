package model;

public class Bloque extends Elemento{
	
	private String  nombre;
	private int x;
	private int y;
	
	public Bloque(String pnombre,int px, int py)
	{
		x = px;
		y = py;
		nombre = pnombre;
	}
	public String getName()
	{
		return nombre;
	}

	public void destruir()
	{
		
	}
}
