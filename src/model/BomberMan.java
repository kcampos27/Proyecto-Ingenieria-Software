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

    public void mover(int pX, int pY)
    {
    	TableroModel tablero = TableroModel.getMiTablero();
    	int nextX = this.x + pX;
    	int nextY = this.y + pY;
    	System.out.println("parametros: "+pX+", "+pY);
    	System.out.println("solicitado moverse a la casilla "+nextX+", "+nextY);
    	
    	if (nextX>=0 && nextY>=0 &&
            	nextX<=16 && nextY<=10)
        {
    	    if (tablero.getContent(nextX,nextY).equals(""))
    	    {
    	    	if(!tablero.getContent(x,y).equals("bombaS")) {tablero.cambiarContent(x,y,"");}
    	    	else {tablero.cambiarContent(x, y, "bombaS");}
	    		this.x = nextX;
		        this.y = nextY;
    	    	switch(pX) 
    	    	{
    	    		case 0:
    	    			switch (pY)
    	    			{
    	    				case 0:
    	    					break;
    	    				case 1:
    	    				{
    	    					tablero.moverBomber(x, y, "down");
    	    					break;
    	    				}
    	    				case -1:
    	    				{
    	    					tablero.moverBomber(x, y, "up");
    	    					break;
    	    				}
    	    			}
    	    			break;
    	    		case 1:
    	    			tablero.moverBomber(x, y, "right");
    	    			break;
    	    		case -1:
    	    		{
    	    			tablero.moverBomber(x, y, "left");
    	    			break;
    	    		}
    	    	}
    	    	System.out.println(x+","+y);
    	    }
    	    else
    	    {
    	    	System.out.println("bloqueado por "+tablero.getContent(nextX,nextY));
    	    }
    	    	
        }
        else
        {
        	System.out.println("mov erroneo");
        }
    }
    public void cambiarTipo(String pTipo)
    {
    	nombre = pTipo;
    }
    public void soltarBomba()
    {
    	TableroModel tablero = TableroModel.getMiTablero();
    	if(tablero.getContent(x,y).equals("") || tablero.getContent(x,y).equals("bombermanW") )
    	{
        	tablero.cambiarContent(x,y,"bomberBomba");
    	}
    	System.out.println("BOMBA");
    }
}
