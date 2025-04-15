package model;

public class BomberManNegro extends BomberMan {

    public BomberManNegro() {
        super("bombermanN", "negro");
        this.bombasActivas = 0;
        strategy= new StrategyUltra();
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
