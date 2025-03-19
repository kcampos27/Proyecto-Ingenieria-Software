package model;

public abstract class Elemento {
	
	String nombre;
	
	public Elemento(String pNombre)
	{
		nombre = pNombre;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public static Elemento escogerTipoElemento(int px, int py, String ptipo)
	{
		Elemento elem = null;
		
		if(ptipo == "bloqueB")
		{elem = new BloqueBlando(px,py,ptipo);}
		
		else if(ptipo == "bloqueD")
		{elem = new BloqueDuro(px,py,ptipo);}
		
		else if(ptipo == "bomberman.W.")
		{elem = BomberMan.getMiBomberMan();}
		
		return elem;
	}
	
}
