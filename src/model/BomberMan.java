package model;

public class BomberMan extends Elemento {
    private static BomberMan miBomberMan;
	private int x, y;
	private int orientacion;
    private BomberMan(String pNombre) {
    	
    	super(pNombre);
        this.x = 0;
        this.y = 0;
        orientacion=0;
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
    	    	if(pX==0) 
    	    	{
    	    			if(pY==1)
    	    				{
    	    					tablero.cambiarContent(x, y, "bombermanW");
    	    					cambiarOrientacion(pX,pY);
    	    				}
    	    				if(pY==-1)
    	    				{
    	    					tablero.cambiarContent(x, y, "bombermanW");
    	    					cambiarOrientacion(pX,pY);
    	    				}
    	    	}
    	    	else if(pX==1) 
    	    	{
    	    			tablero.cambiarContent(x, y, "bombermanW");
    	    			cambiarOrientacion(pX,pY);
    	    	}
    	    	else if(pX==-1)
    	    	{
    	    		tablero.cambiarContent(x, y, "bombermanW");
    	    		cambiarOrientacion(pX,pY);
    	    	}
    	    	System.out.println(x+","+y);

    	    }
    	    
    	    else
    	    {
    	    	System.out.println("bloqueado por "+tablero.getContent(nextX,nextY));
    	    	if(!TableroModel.getMiTablero().getContent(x,y).equals("bombaS")
    	    	    	&& 	!TableroModel.getMiTablero().getContent(x,y).equals("*")) {cambiarOrientacion(pX,pY);}
    	    }
    	    	
        }
        else
        {
        	System.out.println("mov erroneo");
        	if(!TableroModel.getMiTablero().getContent(x,y).equals("bombaS")
	    	    	&& 	!TableroModel.getMiTablero().getContent(x,y).equals("*")) {cambiarOrientacion(pX,pY);}
        }
    }
    
    private void cambiarOrientacion(int pX, int pY)
    {
    	String newDir=nombre;
    	if(pX==0) {
    		if (pY==1){
    			if(orientacion == 0) 
    			{newDir = "down"; 
    			 sigOrientacion();}	
    			else if(orientacion == 1)
    			{newDir = "down2";
    			 sigOrientacion();}
    			 else if(orientacion == 2)
     			{newDir = "down3";
     			 sigOrientacion();}
     			else if(orientacion == 3)
    			{newDir = "down4";
    			 sigOrientacion();}
    		}
    		else if(pY==-1) {
    			if(orientacion == 0) 
    			{newDir = "up"; 
    			 sigOrientacion();}	
    			else if(orientacion == 1)
    			{newDir = "up3";
    			 sigOrientacion();}
    			 else if(orientacion == 2)
     			{newDir = "up4";
     			 sigOrientacion();}
     			else if(orientacion == 3)
    			{newDir = "up";
    			 sigOrientacion();}
    		}
    		
    		else if(pY== 0) {newDir = nombre;}
    	}
    	else if (pX==1) {
    		if(orientacion == 0) 
			{newDir = "right"; 
			 sigOrientacion();}	
			else if(orientacion == 1)
			{newDir = "right4";
			 sigOrientacion();}
			 else if(orientacion == 2)
 			{newDir = "right3";
 			 sigOrientacion();}
 			else if(orientacion == 3)
			{newDir = "right5";
			 sigOrientacion();}
    	}
    	
    	else if(pX==-1){
    		
    		if(orientacion == 0) 
			{newDir = "left"; 
			 sigOrientacion();}	
			else if(orientacion == 1)
			{newDir = "left4";
			 sigOrientacion();}
			 else if(orientacion == 2)
 			{newDir = "left3";
 			 sigOrientacion();}
 			else if(orientacion == 3)
			{newDir = "left5";
			 sigOrientacion();}
    	}
    	
    	TableroModel.getMiTablero().orientarBomber(x, y, newDir);
    }
    private void sigOrientacion()
    {
    	orientacion++;
    	if(orientacion>=4)
    	{
    		orientacion=0;
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
