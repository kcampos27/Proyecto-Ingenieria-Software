package model;

import java.awt.Point;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class PantallaPausaModel extends Observable
{
	private static PantallaPausaModel miPausa;
	
	private PantallaPausaModel()
	{
	}
	
	public static PantallaPausaModel getPausa()
	{
		if(miPausa == null)
		{
			miPausa = new PantallaPausaModel();
		}
		return miPausa;
	}
	
	public void cerrar()
	{
		setChanged();
		notifyObservers(new Object[] {"continuar"});
	}
	
}
