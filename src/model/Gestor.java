package model;

public class Gestor {
    private TableroModel tablero;
    private String bomberman;
    private static Gestor miGestor;
    private Gestor()
    {
    }
    public static Gestor getInstance()
    {
        if (miGestor == null)
        {
            miGestor = new Gestor();
        }

        return miGestor;
    }
    public TableroModel getTablero() {
        return tablero;
    }

    public void cargarPantalla(String tipoPantalla)
    {
        switch (tipoPantalla) {
            case "classic"->
            {
                tablero= new Classic();
                System.out.println("Classic");
            }
            case "soft"->
            {
                tablero= new Soft();
                System.out.println("Soft");
            }
            case "empty"->
            {
                tablero= new Empty();
                System.out.println("Empty");
            }
        }
    }
}
