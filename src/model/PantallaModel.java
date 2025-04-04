package model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class PantallaModel extends Observable
{
    //ATRIBUTOS
    private String bomberman;
    private String tipoPantalla;
    private static PantallaModel miPantalla;

    //METODOS
    public static PantallaModel getMiPantalla()
    {
        if(miPantalla == null)
            miPantalla = new PantallaModel();
        return miPantalla;
    }

    public void setBomberman(String pBomberman)
    {
        bomberman = pBomberman;
        setChanged();
        notifyObservers();
    }

    public void play()
    {
        if (bomberman==null)
        {
            System.out.println("Seleccione un Bomberman");
        }
        else if (bomberman.equals("W"))
        {
            System.out.println("Bomberman W");
        }
        else if (bomberman.equals("B"))
        {
            System.out.println("Bomberman B");
        }
    }
}
