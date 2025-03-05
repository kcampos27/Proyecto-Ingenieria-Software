package proyecto;

public class BombaUltra extends Bombas {

	public BombaUltra(int x, int y) {
		super(x,y,20);
	}
	public void explotar(TableroModel tablero) {
		Explosion.explotar(tablero, getX(), getY(), getRango());
	}
}
