package model;

public class BomberMan extends Elemento {
    private static BomberMan miBomberMan;
	private int x, y;
	private Bombas bomba;
    private BomberMan(String pNombre) {
    	
    	super(pNombre);
        this.x = 0;
        this.y = 0;
    }

    public static BomberMan getMiBomberMan()
    {
    	if (miBomberMan==null)
    	{
    		miBomberMan= new BomberMan("bomberman.W.");
    	}
    	return miBomberMan;
    }
    public int getX() { return x; }
    public int getY() { return y; }
    
    public void cambiarTipo(String pTipo)
    {
    	nombre = pTipo;
    }

    public void mover(int pMX, int pMY) {
    	TableroModel tablero = TableroModel.getMiTablero();
    	int xPrev=x;
    	int yPrev=y;
        if (x+pMX>=0 && y+pMY>=0 &&
        	x+pMX<=17 && y+pMY<=11)
        {
	    	if (tablero.getContent(x+pMX,y+pMY).equals(""))
	    	{
	    		tablero.setContent(x,y,"");
	    		x = x+pMX;
		        y = y+pMY;
		        tablero.setContent(x,y,"bombermanW");
		        System.out.println(x+","+y);
	    	}
	    	else
	    	{
	    		System.out.println("bloqueado por "+tablero.getContent(x+pMX, y+pMY));
	    	}
        }
        else
        {
        	System.out.println("mov erroneo");
        }
        int xPos=x;
        int yPos=y;
    	setChanged();
    	int[] dato=new int[] {xPrev,yPrev,xPos,yPos};
    	notifyObservers(dato);
    }
    
    public void soltarBomba()
    {
    	System.out.println("BOMBA");
    }
}
