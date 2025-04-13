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

    public void play()
    {
        if (bomberman==null)
        {
            System.out.println("Seleccione un Bomberman");
        }
        else if (bomberman.equals("W"))
        {
            Gestor.getInstance().cargarPantalla(tipoPantalla);
            setChanged();
            notifyObservers(1);
            Gestor.getInstance().getTablero().inicializar();
            System.out.println("Bomberman W");
        }
        else if (bomberman.equals("B"))
        {
            Gestor.getInstance().cargarPantalla(tipoPantalla);
            setChanged();
            notifyObservers(1);
            Gestor.getInstance().getTablero().inicializar();
            System.out.println("Bomberman B");
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
