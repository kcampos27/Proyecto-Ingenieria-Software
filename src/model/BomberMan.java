package model;

public abstract class BomberMan extends Elemento {

    private static BomberMan miBomberMan;
    private static String tipoInicial = "blanco";
    private int bombasActivas;
    private int orientacion;
    private String tipo;
    private StateSoltarBomba estado;
    private int maxVida;
    private int maxBombas;
    private boolean pausado = false;

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

    protected BomberMan(String nombre, String tipo, int pVida, int pMaxB) {
        super(0, 0, nombre, 3);
        Gestor.getInstance().getTablero().actualizarItem("switch","vida",pVida);
        this.tipo = tipo;
        this.orientacion = 0;
        maxVida = pVida;
        maxBombas = pMaxB;
    }

    public boolean posibleMoverse(int pX, int pY) {
        return !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, getNombre())
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueD")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueB")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bombaS")
            && !Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bombaU");
    }

    public void mover(int pX, int pY) {
    	if (!pausado)
    	{
    		int nextX = this.getX() + pX;
    		int nextY = this.getY() + pY;

    		System.out.println("Solicitado moverse a la casilla " + nextX + ", " + nextY);

    		if (nextX >= 0 && nextY >= 0 && nextX <= 16 && nextY <= 10) {
    			if (posibleMoverse(nextX, nextY)) {
    				if (Gestor.getInstance().getTablero().casillaIncluye(getX(), getY(), "bombaS")) {
    					Gestor.getInstance().getTablero().generarContent(getX(), getY(), "bombaS");
    				}
    				else if (Gestor.getInstance().getTablero().casillaIncluye(getX(), getY(), "bombaU")) {
    					Gestor.getInstance().getTablero().generarContent(getX(), getY(), "bombaU");
    				}
    				Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), getNombre());
    				Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), "enllamas");
    				setX(nextX);
    				setY(nextY);

    				cambiarOrientacion(pX, pY);

    				if (Gestor.getInstance().getTablero().casillaIncluye(nextX, nextY, "enemigo")
    						|| Gestor.getInstance().getTablero().casillaIncluye(nextX, nextY, "*")) {
    					Gestor.getInstance().getTablero().damage(nextX, nextY, 1, new String[] { getNombre() });
    				}
    			} else {
    				System.out.print("Bloqueado por: ");
    				Gestor.getInstance().getTablero().printContent(nextX, nextY);
    				cambiarOrientacion(pX, pY);
    			}
    		} else {
    			System.out.println("Movimiento fuera de limites");
    			cambiarOrientacion(pX, pY);
    		}
    	}
    }

    protected void sigOrientacion() {
        orientacion = (orientacion + 1) % 4;
    }

    @Override
    public void getHurt(int pDmg) {
    	if (getVida() > 0) {
    		this.restarVida(pDmg);
    		cambiarNombre("enllamas"); // 👈 para que se vea en llamas
    		Gestor.getInstance().getTablero().orientarBomber(getX(), getY(), "enllamas");
    		System.out.println("OUCH, vida restante: " + getVida());
    		Gestor.getInstance().getTablero().actualizarItem("sub","vida",pDmg);
    	}

    	if (getVida() == 0) {
    		Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), getNombre());
    		setX(-1);
    		setY(-1);
            
    		// Restaurar nombre original según tipo
    		if (tipo.equals("blanco")) cambiarNombre("bombermanW");
    		else if (tipo.equals("negro")) cambiarNombre("bombermanN");
            
    		Gestor.getInstance().getTablero().pausar("lose",true);
    	}
    }
    
    public void bombaExploto() {
        bombasActivas--;
    }

    public void cambiarNombre(String nuevoNombre) {
        setNombre(nuevoNombre);
    }

    // 👇 IMPORTANTE: ahora es abstracto
    protected abstract void cambiarOrientacion(int pX, int pY);

    // Método abstracto que implementan Blanco y Negro
    public void soltarBomba()
    {
    	if(!pausado)
        {estado.soltarBomba();}
    }
    
    public void cambiarEstado(StateSoltarBomba pEstado)
    {
    	estado = pEstado;
    }
    
    public String getTipo()
    {
        return tipo;
    }
    
    protected void setActivas(int pNum) {
		bombasActivas = pNum;
	}
    protected void setEstado(StateSoltarBomba pEstado)
    {
    	estado = pEstado;
    }
    protected int getOrientacion()
    {
    	return orientacion;
    }
    public boolean hayBombas()
    {
    	return bombasActivas < maxBombas;
    }
    public void incrementarActivas() {bombasActivas++;}
    
    @Override
    public void detener()
    {
    	pausado = true;
    }
    
    @Override
    public void continuar()
    {
    	pausado = false;
    }
}
