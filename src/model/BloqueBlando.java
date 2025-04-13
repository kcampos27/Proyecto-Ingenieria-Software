package model;

public class BloqueBlando extends Bloque{

	public BloqueBlando(int pX, int pY, String pNombre)
	{
		super(pX, pY, pNombre,1);
	}
	
	@Override
    public void getHurt(int pDmg)
    {
    	if(vida>0) 
    	{
    		if(vida - pDmg == 0) {vida = 0;}
    		else {vida = vida - pDmg;}
    	}
    	if(vida==0) 
    	{
    		Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
    		x=-1;
    		y=-1;
    	}
    }
}
