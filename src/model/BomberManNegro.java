package model;

public class BomberManNegro extends BomberMan {

    private int bombasActivas;

    public BomberManNegro() {
        super("bombermanN", "negro");
        this.bombasActivas = 0;
    }

    @Override
    public void soltarBomba() {
        if (bombasActivas < 1 &&
            (Gestor.getInstance().getTablero().casillaIncluye(x, y, "") ||
             Gestor.getInstance().getTablero().casillaIncluye(x, y, nombre))) {

            Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
            cambiarTipo("blackwithbomb1"); // ✅ Este sí existe en tus recursos
            Gestor.getInstance().getTablero().aniadirContent(x, y, this);
            cambiarTipo("bombermanN");

            new BombaUltra(x, y);
            bombasActivas++;
            System.out.println("BOMBA ULTRA colocada. Activas: " + bombasActivas);
        }
    }

    public void bombaExploto() {
        bombasActivas = 0;
    }

    @Override
    protected void cambiarOrientacion(int pX, int pY) {
        String newDir = nombre;

        if (pX == 0 && pY == 1) {
            newDir = switch (orientacion) {
                case 0 -> "blackdown1";
                case 1 -> "blackdown2";
                case 2 -> "blackdown3";
                default -> "blackdown4";
            };
        } else if (pX == 0 && pY == -1) {
            newDir = switch (orientacion) {
                case 0 -> "blackup1";
                case 1 -> "blackup2";
                case 2 -> "blackup3";
                default -> "blackup4";
            };
        } else if (pX == 1) {
            newDir = switch (orientacion) {
                case 0 -> "blackright1";
                case 1 -> "blackright2";
                case 2 -> "blackright3";
                default -> "blackright4";
            };
        } else if (pX == -1) {
            newDir = switch (orientacion) {
                case 0 -> "blackleft1";
                case 1 -> "blackleft2";
                case 2 -> "blackleft3";
                default -> "blackleft4";
            };
        }

        sigOrientacion();
        Gestor.getInstance().getTablero().orientarBomber(x, y, newDir);
    }
}
