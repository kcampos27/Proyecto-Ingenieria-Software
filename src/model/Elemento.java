package model;

public abstract class Elemento {
	
	protected String nombre;
	protected int vida;
	protected int x, y;
	
	public Elemento(int px, int py, String pNombre, int pHP)
	{
		nombre = pNombre;
		vida = pHP;
		x=px;
		y=py;
	}
	
	public String getNombre(){return nombre;}
	
	public abstract void getHurt(int pDmg);

	public static Elemento escogerTipoElemento(int px, int py, String ptipo)
	{
		Elemento elem = null;
		
            switch (ptipo) {
                case "bloqueB" -> elem = new BloqueBlando(px,py,ptipo);
                case "bloqueD" -> elem = new BloqueDuro(px,py,ptipo);
                case "bombermanW" -> elem = BomberMan.getMiBomberMan();
                case "*" -> elem = new Explosion(px,py,"*");
                case "bombaS" -> elem = new BombaSuper(px,py);
                default -> {}
            }
		
		return elem;
	}
	
}
