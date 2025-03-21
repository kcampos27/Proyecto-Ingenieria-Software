package model;

public class BomberMan extends Elemento {
    private static BomberMan miBomberMan;
	private int x, y;
    private BomberMan(String pNombre) {
    	
    	super(pNombre);
        this.x = 0;
        this.y = 0;
    }

    public static BomberMan getMiBomberMan()
    {
    	if (miBomberMan==null)
    	{
    		miBomberMan= new BomberMan("bombermanW");
    	}
    	return miBomberMan;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int pX) { x = pX; }
    public void setY(int pY) { y = pY; }
    
    public void cambiarTipo(String pTipo)
    {
    	nombre = pTipo;
    }
    public void soltarBomba()
    {
    	System.out.println("BOMBA");
    }
}
