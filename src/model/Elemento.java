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
		
		if(ptipo == "bloqueB")
		{elem = new BloqueBlando(px,py,ptipo);}
		
		else if(ptipo == "bloqueD")
		{elem = new BloqueDuro(px,py,ptipo);}
		
		else if(ptipo == "bombermanW")
		{elem = BomberMan.getMiBomberMan();}
		
		else if(ptipo == "*")
		{elem = new Explosion("*");}
		
		return elem;
	}
	
}
