package model;

public abstract class Elemento {
	
	String nombre;
	
	public Elemento(String pNombre)
	{
		nombre = pNombre;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public static Elemento escogerTipoElemento(int px, int py, String ptipo)
	{
		Elemento elem = null;
		
            switch (ptipo) {
                case "bloqueB" -> elem = new BloqueBlando(ptipo);
                case "bloqueD" -> elem = new BloqueDuro(ptipo);
                case "bombermanW" -> elem = BomberMan.getMiBomberMan();
                case "*" -> elem = new Explosion("*");
                case "bombaS" -> elem = new BombaSuper(px,py);
                default -> {}
            }
		
		return elem;
	}
	
}
