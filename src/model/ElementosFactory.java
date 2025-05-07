package model;

public class ElementosFactory {
	
	private static ElementosFactory miFactory;
	
	private ElementosFactory()
	{}
	public static ElementosFactory getFactory()
	{
		if(miFactory == null) {miFactory = new ElementosFactory();}
		return miFactory;
	}
	
	public Elemento fabricarElemento(int pX, int pY, String pTipo)
	{
		Elemento elem = null;
		switch (pTipo){
        case "bloqueB" -> elem = new BloqueBlando(pX,pY,pTipo);
        case "bloqueD" -> elem = new BloqueDuro(pX,pY,pTipo);
        case "bombermanW" -> elem = BomberMan.getMiBomberMan();
		case "bombermanN" -> elem = BomberMan.getMiBomberMan();
        case "*" -> elem = new Explosion(pX,pY,"*");
        case "bombaS" -> elem = new BombaSuper(pX,pY);
		case "bombaU" -> elem = new BombaUltra(pX,pY);
        case "enemigo" -> elem = new EnemigoNormal(pX,pY,pTipo);
        case "enemigoR" -> elem = new EnemigoConRango(pX,pY,pTipo);
        case "enemigoV" -> elem = new EnemigoVeloz(pX,pY,pTipo);
        case "+" -> elem = new Explosion(pX,pY,"+");

        default -> {}
		}
		return elem;
	}

}
