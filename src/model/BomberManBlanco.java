package model;

public class BomberManBlanco extends BomberMan {

    public BomberManBlanco() {
        super("bombermanW", "blanco",3,3);
        this.bombasActivas = 0;
        estado= new StateSuperWhite();
    }

    @Override
    protected void cambiarOrientacion(int pX, int pY) {
        String newDir = nombre;

        if (pX == 0 && pY == 1) {
            newDir = switch (orientacion) {
                case 0 -> "down";
                case 1 -> "down2";
                case 2 -> "down3";
                case 3 -> "down4";
                default -> "down";
            };
        } else if (pX == 0 && pY == -1) {
            newDir = switch (orientacion) {
                case 0 -> "up";
                case 1 -> "up3";
                case 2 -> "up4";
                case 3 -> "up";
                default -> "up";
            };
        } else if (pX == 1) {
            newDir = switch (orientacion) {
                case 0 -> "right";
                case 1 -> "right4";
                case 2 -> "right3";
                case 3 -> "right5";
                default -> "right";
            };
        } else if (pX == -1) {
            newDir = switch (orientacion) {
                case 0 -> "left";
                case 1 -> "left4";
                case 2 -> "left3";
                case 3 -> "left5";
                default -> "left";
            };
        }

        sigOrientacion();
        Gestor.getInstance().getTablero().orientarBomber(x, y, newDir);
    }
}
