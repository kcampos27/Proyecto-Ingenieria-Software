package proyecto;

public class Bloque{
	
	private boolean esBlando;
	private String  nombre;
	private int x;
	private int y;
	
	public Bloque(boolean pDureza, String pnombre,int px, int py)
	{
		esBlando = pDureza;
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
