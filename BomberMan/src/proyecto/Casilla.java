package proyecto;
import javax.swing.JPanel;

public class Casilla extends JPanel
{
	//ATRIBUTOS
	private int x;
	private int y;
	
	//CONSTRUCTORA
	public Casilla (int pX, int pY)
	{
		super();
		x=pX;
		y=pY;
	}
	
	//METODOS
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
