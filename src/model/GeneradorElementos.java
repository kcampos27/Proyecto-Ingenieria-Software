package model;

public class GeneradorElementos {
	
	private static GeneradorElementos miGen;
	
	private GeneradorElementos()
	{}
	public static GeneradorElementos getGen()
	{
		if(miGen == null) {miGen = new GeneradorElementos();}
		return miGen;
	}
	
	public Elemento generarElemento(int pX, int pY, String pTipo)
	{
		return ElementosFactory.getFactory().fabricarElemento(pX, pY, pTipo);
	}

}
