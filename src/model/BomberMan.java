package model;

public class BomberMan extends Elemento {
    private static BomberMan miBomberMan;
	private int orientacion;
    private BomberMan(String pNombre) {
    	
    	super(0,0,pNombre,3);
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
    
    @Override
    public void getHurt(int pDmg)
    {
    	
    	if(vida>0) 
    	{
    		if(vida - pDmg == 0) {vida = 0;}
    		else {vida = vida - pDmg;}
    		TableroModel.getMiTablero().orientarBomber(x, y, "enllamas");
    		System.out.println("OUCH, tengo "+vida+" y tenia "+(vida+pDmg)+" de vida");
    	}
    	if(vida==0) 
    	{
    		TableroModel.getMiTablero().eliminarContent(x, y, getNombre());
    		TableroModel.getMiTablero().aniadirContent(0, 0, getNombre());
    		x=0;
    		y=0;
    		System.out.println("HE PERDIDO, regreso a 0,0");
    		vida = 3;
    	}
    }
    
    public boolean posibleMoverse(int pX, int pY)
    {
    	boolean moverse = false;
    	
    	if(!TableroModel.getMiTablero().casillaIncluye(pX, pY, nombre)
    			&& !TableroModel.getMiTablero().casillaIncluye(pX, pY, "bloqueD")
    			&& !TableroModel.getMiTablero().casillaIncluye(pX, pY, "bloqueB")
    			&& !TableroModel.getMiTablero().casillaIncluye(pX, pY, "bombaS"))
    	{
    		moverse = true;
    	}
    	
    	return moverse;
    }

    public void mover(int pX, int pY)
    {
    	int nextX = this.x + pX;
    	int nextY = this.y + pY;
    	System.out.println("parametros: "+pX+", "+pY);
    	System.out.println("solicitado moverse a la casilla "+nextX+", "+nextY);
    	
    	if (nextX>=0 && nextY>=0 &&
            	nextX<=16 && nextY<=10)
        {
    	    if (posibleMoverse(nextX,nextY))
    	    {
    	    	if(TableroModel.getMiTablero().casillaIncluye(x, y, "bombaS")) 
    	    	{TableroModel.getMiTablero().aniadirContent(x, y, "bombaS");}
    	    	TableroModel.getMiTablero().eliminarContent(x,y,nombre);
	    		this.x = nextX;
		        this.y = nextY;
    	    	if(pX==0) 
    	    	{
    	    		if(pY==1){cambiarOrientacion(pX,pY);}
    	    		if(pY==-1){cambiarOrientacion(pX,pY);}
    	    	}
    	    	else if(pX==1){cambiarOrientacion(pX,pY);}
    	    	else if(pX==-1){cambiarOrientacion(pX,pY);}
    	    	System.out.println(x+","+y);
    	    }
    	    
    	    else
    	    {
    	    	System.out.print("bloqueado por ");
    	    	TableroModel.getMiTablero().printContent(nextX, nextY);
    	    	if(!TableroModel.getMiTablero().casillaIncluye(x, y, "bombaS")
    	    	    	&& 	!TableroModel.getMiTablero().casillaIncluye(x, y, "*")) {cambiarOrientacion(pX,pY);}
    	    }
    	    	
        }
        else
        {
        	System.out.println("mov erroneo");
        	if(!TableroModel.getMiTablero().casillaIncluye(x, y, "bombaS")
	    	    	&& 	!TableroModel.getMiTablero().casillaIncluye(x, y, "*")) {cambiarOrientacion(pX,pY);}
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
    	if(TableroModel.getMiTablero().casillaIncluye(x, y, "") || TableroModel.getMiTablero().casillaIncluye(x, y, nombre))
    	{
    		TableroModel.getMiTablero().eliminarContent(x, y, nombre);
    		TableroModel.getMiTablero().aniadirContent(x,y,"bomberBomba");
    	}
    	System.out.println("BOMBA");
    }
}
