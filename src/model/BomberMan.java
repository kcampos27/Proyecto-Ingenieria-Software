package model;

public abstract class BomberMan extends Elemento {

    protected static BomberMan miBomberMan;
    protected static String tipoInicial = "blanco";

    protected int orientacion;
    protected int bombasActivasMax;
    protected String tipo;

    public static void setTipoInicial(String tipo) {
        tipoInicial = tipo;
    }

    public static void reiniciarBomberman() {
        miBomberMan = null;
    }

    public static BomberMan getMiBomberMan() {
        if (miBomberMan == null) {
            if (tipoInicial.equalsIgnoreCase("blanco")) {
                miBomberMan = new BomberManBlanco();
            } else {
                miBomberMan = new BomberManNegro();
            }
        }
        return miBomberMan;
    }

    protected BomberMan(String nombre, String tipo) {
        super(0, 0, nombre, 3);
        this.tipo = tipo;
        this.orientacion = 0;
    }

    public boolean posibleMoverse(int pX, int pY) {
        return !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, nombre)
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueD")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueB")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bombaS");
    }

    public void mover(int pX, int pY) {
        int nextX = this.x + pX;
        int nextY = this.y + pY;

        System.out.println("Solicitado moverse a la casilla " + nextX + ", " + nextY);

        if (nextX >= 0 && nextY >= 0 && nextX <= 16 && nextY <= 10) {
            if (posibleMoverse(nextX, nextY)) {
                if (Gestor.getInstance().getTablero().casillaIncluye(x, y, "bombaS")) {
                    Gestor.getInstance().getTablero().generarContent(x, y, "bombaS");
                }
                Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
                this.x = nextX;
                this.y = nextY;

                cambiarOrientacion(pX, pY);

                if (Gestor.getInstance().getTablero().casillaIncluye(nextX, nextY, "enemigo")
                    || Gestor.getInstance().getTablero().casillaIncluye(nextX, nextY, "*")) {
                    Gestor.getInstance().getTablero().damage(nextX, nextY, 1, new String[] { nombre });
                }
            } else {
                System.out.print("Bloqueado por: ");
                Gestor.getInstance().getTablero().printContent(nextX, nextY);
                cambiarOrientacion(pX, pY);
            }
        } else {
            System.out.println("Movimiento fuera de límites");
            cambiarOrientacion(pX, pY);
        }
    }

    protected void sigOrientacion() {
        orientacion = (orientacion + 1) % 4;
    }

    @Override
    public void getHurt(int pDmg) {
        if (vida > 0) {
            vida = Math.max(0, vida - pDmg);
            Gestor.getInstance().getTablero().orientarBomber(x, y, "enllamas");
            System.out.println("OUCH, vida restante: " + vida);
        }

        if (vida == 0) {
            Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
            x = 0;
            y = 0;
            vida = 3;
            Gestor.getInstance().getTablero().aniadirContent(0, 0, this);
            System.out.println("Reaparezco en 0,0");
        }
    }

    public void cambiarTipo(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    // 👇 IMPORTANTE: ahora es abstracto
    protected abstract void cambiarOrientacion(int pX, int pY);

    // Método abstracto que implementan Blanco y Negro
    public abstract void soltarBomba();
}
