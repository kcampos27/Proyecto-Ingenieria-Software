package model;

public abstract class Elemento {
	
	protected String nombre;
	protected int vida;
	protected int x, y;
	
	public Elemento(int px, int py, String pNombre, int pHP)
	{
		nombre = pNombre;
		vida = pHP;
		x=px;
		y=py;
	}
	
	public String getNombre(){return nombre;}
	
	public abstract void getHurt(int pDmg);

}
