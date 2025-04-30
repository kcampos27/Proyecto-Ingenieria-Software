package model;

public abstract class Elemento {
	
	private String nombre;
	private int vida;
	private int x, y;
	
	public Elemento(int px, int py, String pNombre, int pHP)
	{
		nombre = pNombre;
		vida = pHP;
		x=px;
		y=py;
	}
	
	public String getNombre(){return nombre;}
	
	public abstract void getHurt(int pDmg);
	
	public void detener(){}
	
	public void continuar(){}
	
	protected void setX(int pX)
	{
		x = pX;
	}
	
	protected void setY(int pY)
	{
		y = pY;
	}
	
	protected int getX()
	{
		return x;
	}
	
	protected int getY()
	{
		return y;
	}
	
	protected int getVida()
	{
		return vida;
	}
	
	protected void matar() {
		vida = 0;
	}
	
	protected void restarVida(int pDmg)
	{
		vida = vida - pDmg;
	}
	
	protected void setVida(int pMax)
	{
		vida = pMax;
	}
	
	protected void setNombre(String pN)
	{
		nombre = pN;
	}
}
