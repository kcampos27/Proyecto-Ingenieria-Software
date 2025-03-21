package model;

import java.util.Observable;

public abstract class Elemento extends Observable {
	
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
		
		if(ptipo.equals("bloqueB"))
		{elem = new BloqueBlando(px,py,ptipo);}
		
		else if(ptipo.equals("bloqueD"))
		{elem = new BloqueDuro(px,py,ptipo);}
		
		else if(ptipo.equals("bombermanW") )
		{elem = BomberMan.getMiBomberMan();}
		
		else if(ptipo.equals("*"))
		{elem = new Explosion("*");}
		
		else if(ptipo.equals("bombaS"))
		{elem = new BombaSuper(px,py);}
		
		return elem;
	}
	
}
