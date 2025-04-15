package model;

public class StateSinBombas implements StateSoltarBomba
{

	@Override
	public void soltarBomba() 
	{
		System.out.println("No hay bombas suficientes, recargando...");
		if(BomberMan.getMiBomberMan().bombasActivas < BomberMan.getMiBomberMan().maxBombas)
		{
			if(BomberMan.getMiBomberMan().getTipo().equals("blanco"))
			{BomberMan.getMiBomberMan().cambiarEstado(new StateSuperWhite());}
			
			else if(BomberMan.getMiBomberMan().getTipo().equals("negro"))
			{BomberMan.getMiBomberMan().cambiarEstado(new StateUltraBlack());}
		}
	}

	
	
}
