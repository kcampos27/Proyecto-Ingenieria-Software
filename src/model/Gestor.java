package model;

public class Gestor {
    private TableroModel tablero;
    private static Gestor miGestor;

    private Gestor() {}

    public static Gestor getInstance() {
        if (miGestor == null) {
            miGestor = new Gestor();
        }
        return miGestor;
    }

    public TableroModel getTablero() {
        return tablero;
    }

    public void cargarPantalla(String tipoPantalla) {
        switch (tipoPantalla) {
            case "classic" -> {
                tablero = new Classic();
                System.out.println("Classic");
            }
            case "soft" -> {
                tablero = new Soft();
                System.out.println("Soft");
            }
            case "empty" -> {
                tablero = new Empty();
                System.out.println("Empty");
            }
        }
    }

    // ✅ Este método es fundamental para seleccionar bomberman blanco o negro
    public void seleccionarBomberman(String tipo) {
        BomberMan.reiniciarBomberman(); // Limpia instancia anterior

        if (tipo.equals("W")) {
            BomberMan.setTipoInicial("blanco");
        } else if (tipo.equals("B")) {
            BomberMan.setTipoInicial("negro");
        }

        BomberMan bm = BomberMan.getMiBomberMan();
        tablero.aniadirContent(0, 0, bm); // Añadir bomberman al tablero
        System.out.println("Bomberman " + tipo + " seleccionado");
    }
}
