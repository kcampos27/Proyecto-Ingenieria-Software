package proyecto;

public class BomberMan{
	
	//ATRIBUTOS
	private static BomberMan miBomberman;
	private int x, y; // Posicion en la cuadricula
	private String nombre;
	
	//CONSTRUCTORA
	private BomberMan(int startX, int startY, String pnombre)
	{
		x = startX;
		y= startY;
		nombre = pnombre;
	}
	
	//METODOS
	public int getX() { return x; }
    public int getY() { return y; }
    public String getName() {return nombre;}
	public static BomberMan getBomberman()
	{
		if (miBomberman==null)
		{
			miBomberman = new BomberMan(0,0,"bomberman.W");
		}
		return miBomberman;
	}
	public void mover(int dx, int dy, int maxCols, int maxRows) {
        int nuevoX = x + dx;
        int nuevoY = y + dy;

        // Evita que salga del tablero
        if (nuevoX >= 0 && nuevoX < maxCols) x = nuevoX;
        if (nuevoY >= 0 && nuevoY < maxRows) y = nuevoY;
    }

}
