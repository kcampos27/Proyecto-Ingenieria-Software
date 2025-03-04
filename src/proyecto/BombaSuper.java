package codigoBombas;

public class BombaSuper extends Bombas {

	public BombaSuper(int x, int y) { //son las coordenadas de la bomba y num de celda que destruye
		super(x,y,1);
	}
	//Metodo con tablero hecho //Llama a la explosion definida
	//public void meterExpl (Tablero tablero){
	//Explosion.meterExpl(tablero, getX(), getY(), getRange());}
}
