package model;

import vista.PantallaView;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class PantallaModel extends Observable
{
    //ATRIBUTOS
    private String bomberman;
    private String tipoPantalla;
    private static PantallaModel miPantalla;

    //METODOS
    private PantallaModel(){
        tipoPantalla = "classic";
    }

    public static PantallaModel getMiPantalla()
    {
        if(miPantalla == null)
            miPantalla = new PantallaModel();
        return miPantalla;
    }

    public void setBomberman(String pBomberman)
    {
        bomberman = pBomberman;
    }

    public void play() {
        if (bomberman == null) {
            System.out.println("Seleccione un Bomberman");
        } else {
            // 1. Crear estructura del tablero
            Gestor.getInstance().cargarPantalla(tipoPantalla);

            // 2. Notificar a la vista para que cree y se suscriba a TableroView
            setChanged();
            notifyObservers(new Object[] {1,getTipoPantalla()});

            // 3. Ahora que la vista está lista, inicializar el tablero con contenido
            Gestor.getInstance().getTablero().inicializar();

            // 4. Finalmente, seleccionar e instanciar BomberMan
            Gestor.getInstance().seleccionarBomberman(bomberman);

            // 5. Se coloca BomberMan en la posicion 0,0 del tablero
            Gestor.getInstance().getTablero().aniadirContent(0,0,BomberMan.getMiBomberMan());

            System.out.println("Bomberman " + bomberman + " listo");
        }
    }





    public void select(int pNext)
    {
        if (pNext == 1) {
            switch (tipoPantalla) {
                case "classic" -> tipoPantalla = "soft";
                case "soft" -> tipoPantalla = "empty";
                case "empty" -> tipoPantalla = "classic";
            }
        }
        else if (pNext == -1) {
            switch (tipoPantalla) {
                case "classic" -> tipoPantalla = "empty";
                case "soft" -> tipoPantalla = "classic";
                case "empty" -> tipoPantalla = "soft";
            }
        }
    }
    public String getTipoPantalla()
    {
        String pantalla="";
        switch (tipoPantalla) {
            case "classic" -> pantalla = "stageBack1";
            case "soft" -> pantalla = "stageBack3";
            case "empty" -> pantalla = "stageBack2";
        }
        return pantalla;
    }
}
