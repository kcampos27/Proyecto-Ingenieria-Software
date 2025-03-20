package model;

public class BombaSuper extends Bombas {

	public BombaSuper(int x, int y) { //son las coordenadas de la bomba y num de celda que destruye
		super(x,y,1,"bombaS");
	}
	public Bombas crearBombas() {
		BomberMan bomberman = BomberMan.getMiBomberMan();
		int bombX=bomberman.getX();
		int bombY=bomberman.getY();
		BombaSuper bomba = new BombaSuper(bombX,bombY);
		return bomba;
	}
}

