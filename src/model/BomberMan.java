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
		System.out.println("");
    	if(vida>0) 
    	{
    		if(vida - pDmg == 0) {vida = 0;}
    		else {vida = vida - pDmg;}
    		Gestor.getInstance().getTablero().orientarBomber(x, y, "enllamas");
    		System.out.println("OUCH, tengo "+vida+" y tenia "+(vida+pDmg)+" de vida");
    	}
    	if(vida==0) 
    	{
    		Gestor.getInstance().getTablero().eliminarContent(x, y, getNombre());
    		Gestor.getInstance().getTablero().aniadirContent(0, 0, this);
    		x=0;
    		y=0;
    		System.out.println("HE PERDIDO, regreso a 0,0");
    		vida = 3;
    	}
		System.out.println("");
    }
    
    public boolean posibleMoverse(int pX, int pY)
    {
    	boolean moverse = false;
    	
    	if(!Gestor.getInstance().getTablero().casillaIncluye(pX, pY, nombre)
    			&& !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueD")
    			&& !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueB")
    			&& !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bombaS"))
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
    	    	if(Gestor.getInstance().getTablero().casillaIncluye(x, y, "bombaS")) 
    	    	{Gestor.getInstance().getTablero().generarContent(x, y, "bombaS");}
    	    	Gestor.getInstance().getTablero().eliminarContent(x,y,nombre);
	    		this.x = nextX;
		        this.y = nextY;
    	    	if(pX==0) 
    	    	{
    	    		if(pY==1){cambiarOrientacion(pX,pY);}
    	    		else if(pY==-1){cambiarOrientacion(pX,pY);}
    	    	}
    	    	else if(pX==1){cambiarOrientacion(pX,pY);}
    	    	else if(pX==-1){cambiarOrientacion(pX,pY);}
    	    	System.out.println(x+","+y);
    	    	
    	    	if(Gestor.getInstance().getTablero().casillaIncluye(nextX, nextY, "enemigo")
    	    			|| Gestor.getInstance().getTablero().casillaIncluye(nextX, nextY, "*"))//Si nos chocamos con un enemigo o explosion, recibimos danio
    	    	{Gestor.getInstance().getTablero().damage(nextX, nextY, 1, new String[] {"bombermanW"});}
    	    }
    	    
    	    else
    	    {
    	    	System.out.print("bloqueado por ");
    	    	Gestor.getInstance().getTablero().printContent(nextX, nextY);
    	    	if(!Gestor.getInstance().getTablero().casillaIncluye(x, y, "bombaS")
    	    	    	&& 	!Gestor.getInstance().getTablero().casillaIncluye(x, y, "*")) {cambiarOrientacion(pX,pY);}
    	    }
    	    	
        }
        else
        {
        	System.out.println("mov erroneo");
        	if(!Gestor.getInstance().getTablero().casillaIncluye(x, y, "bombaS")
	    	    	&& 	!Gestor.getInstance().getTablero().casillaIncluye(x, y, "*")) {cambiarOrientacion(pX,pY);}
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
    	
    	Gestor.getInstance().getTablero().orientarBomber(x, y, newDir);
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
    	if(Gestor.getInstance().getTablero().casillaIncluye(x, y, "") || Gestor.getInstance().getTablero().casillaIncluye(x, y, nombre))
    	{
    		Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
    		nombre = "bomberBomba";
    		Gestor.getInstance().getTablero().aniadirContent(x,y,this);
    		nombre = "bombermanW";
    	}
    	System.out.println("BOMBA");
    }
}
