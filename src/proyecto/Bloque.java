package proyecto;

public class Bloque extends Contenido{
	
	private boolean esBlando;
	
	public Bloque(boolean pDureza, int x, int y)
	{
		super("Bloque " +x+y);
		esBlando = pDureza;
	}

}
