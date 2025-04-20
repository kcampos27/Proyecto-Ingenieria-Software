package model;

public class StateUltraBlack implements StateSoltarBomba
{

	@Override
	public void soltarBomba() 
	{
		 int x = BomberMan.getMiBomberMan().getX();
         int y = BomberMan.getMiBomberMan().getY();
         
         //Quitar bomberman para volver a aniadirlo con el sprite de bomber + bomba
         Gestor.getInstance().getTablero().eliminarContent(x, y, BomberMan.getMiBomberMan().getNombre());
         BomberMan.getMiBomberMan().cambiarNombre("blackwithbomb1");
         Gestor.getInstance().getTablero().aniadirContent(x, y, BomberMan.getMiBomberMan());
         
         //Volvemos a cambiar el nombre del bomberman a su original, para que sea detectado como bomberman normal
         BomberMan.getMiBomberMan().cambiarNombre("bombermanN");
         
         //Crear la nueva bomba
         Gestor.getInstance().getTablero().aniadirContent(x, y, new BombaUltra(x, y));
         BomberMan.getMiBomberMan().incrementarActivas();;
         System.out.println("BOMBA ULTRA colocada. Activas: ");
           
         if (!BomberMan.getMiBomberMan().hayBombas())
         //Si no tiene suficientes bombas, hay que cambiar de estado
         {BomberMan.getMiBomberMan().cambiarEstado(new StateSinBombas());}
       
	}

	
	
}
