package model;

public abstract class BomberMan extends Elemento {

    protected static BomberMan miBomberMan;
    protected static String tipoInicial = "blanco";
    protected int bombasActivas;
    protected int orientacion;
    protected int bombasActivasMax;
    protected String tipo;
    protected StrategySoltarBomba strategy;
    protected int maxVida;

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

    protected BomberMan(String nombre, String tipo, int pVida) {
        super(0, 0, nombre, 3);
        Gestor.getInstance().getTablero().actualizarItem("switch","vida",pVida);
        this.tipo = tipo;
        this.orientacion = 0;
        maxVida = pVida;
    }

    public boolean posibleMoverse(int pX, int pY) {
        return !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, nombre)
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueD")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueB")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bombaS")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bombaU");
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
                else if (Gestor.getInstance().getTablero().casillaIncluye(x, y, "bombaU")) {
                    Gestor.getInstance().getTablero().generarContent(x, y, "bombaU");
                }
                Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
                Gestor.getInstance().getTablero().eliminarContent(x, y, "enllamas");
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
            vida = vida - pDmg;
            cambiarTipo("enllamas"); // 👈 para que se vea en llamas
            Gestor.getInstance().getTablero().orientarBomber(x, y, "enllamas");
            System.out.println("OUCH, vida restante: " + vida);
            Gestor.getInstance().getTablero().actualizarItem("sub","vida",pDmg);
        }

        if (vida == 0) {
            Gestor.getInstance().getTablero().eliminarContent(x, y, nombre);
            x = 0;
            y = 0;
            vida = maxVida;
            
            // Restaurar nombre original según tipo
            if (tipo.equals("blanco")) cambiarTipo("bombermanW");
            else if (tipo.equals("negro")) cambiarTipo("bombermanN");
            
            Gestor.getInstance().getTablero().actualizarItem("add","vida",maxVida);
            Gestor.getInstance().getTablero().aniadirContent(0, 0, this);
            System.out.println("Reaparezco en 0,0");
        }
    }
    public void bombaExploto() {
        bombasActivas--;
    }

    public void cambiarTipo(String nuevoNombre) {
        nombre = nuevoNombre;
    }

    // 👇 IMPORTANTE: ahora es abstracto
    protected abstract void cambiarOrientacion(int pX, int pY);

    // Método abstracto que implementan Blanco y Negro
    public void soltarBomba(){
        strategy.soltarBomba(miBomberMan);
    }
    public String getTipo()
    {
        return tipo;
    }
}
