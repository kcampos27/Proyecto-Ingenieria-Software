package model;

public class BomberMan extends Elemento {
    private static BomberMan miBomberMan;
	private int x, y;
	private Bombas bomba;
    private BomberMan() {
        this.x = 0;
        this.y = 0;
    }

    public static BomberMan getMiBomberMan()
    {
    	if (miBomberMan==null)
    	{
    		miBomberMan= new BomberMan();
    	}
    	return miBomberMan;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    public void mover(int pMX, int pMY) {
    	TableroModel tablero = TableroModel.getMiTablero();
        if (x+pMX>=0 && y+pMY>=0 &&
        	x+pMX<=17 && y+pMY<=11)
        {
	    	if (tablero.getCasilla(y+pMY,x+pMX).getContent()=="")
	    	{
	    		tablero.getCasilla(y,x).setContent("");
	    		x = x+pMX;
		        y = y+pMY;
		        System.out.println(x+","+y);
	    	}
	    	else
	    	{
	    		System.out.println("bloqueado por caja");
	    	}
        }
        else
        {
        	System.out.println("mov erroneo");
        }
    	
    }
    
    public void soltarBomba()
    {
    	System.out.println("BOMBA");
    }
}
