package proyecto;

public class BomberMan extends Contenido{
	
	//ATRIBUTOS
	private static BomberMan miBomberman;
	private int x, y; // Posicion en la cuadricula
	
	//CONSTRUCTORA
	private BomberMan(int startX, int startY)
	{
		super("bomberman");
		x = startX;
		y= startY;
	}
	
	//METODOS
	public int getX() { return x; }
    public int getY() { return y; }
	public static BomberMan getBomberman()
	{
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
