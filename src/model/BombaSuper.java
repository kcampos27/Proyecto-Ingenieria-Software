package model;

public class BombaSuper extends Bombas {

	public BombaSuper(int px, int py) { //son las coordenadas de la bomba y num de celda que destruye
		super(px,py,1,"bombaS");
	}
	/* public Bombas crearBombas() {
		BomberMan bomberman = BomberMan.getMiBomberMan();
		int bombX=bomberman.getX();
		int bombY=bomberman.getY();
		BombaSuper bomba = new BombaSuper(bombX,bombY);
		return bomba;
	} */
}

