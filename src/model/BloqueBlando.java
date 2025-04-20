package model;

public class BloqueBlando extends Bloque{

	public BloqueBlando(int pX, int pY, String pNombre)
	{
		super(pX, pY, pNombre,1);
	}
	
	@Override
    public void getHurt(int pDmg)
    {
    	if(getVida()>0) 
    	{
    		if(getVida() - pDmg == 0) {matar();}
    		else {restarVida(pDmg);}
    	}
    	if(getVida()==0) 
    	{
    		Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), getNombre());
    		setX(-1);
    		setY(-1);
    	}
    }
}
