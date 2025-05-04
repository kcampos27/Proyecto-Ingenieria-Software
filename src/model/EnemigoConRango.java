package model;

public class EnemigoConRango extends Enemigo {

    public EnemigoConRango(int pX, int pY, String pName) {
        super(pX, pY, pName);
    }

    @Override
    public synchronized void mover(int pX, int pY) {
        int nextX = getX() + pX;
        int nextY = getY() + pY;

        if (nextX >= 0 && nextY >= 0 && nextX <= 16 && nextY <= 10) {
            if (posibleMoverse(nextX, nextY)) {
                System.out.println("");
                System.out.println("______________________________________________");

                // Limpiar las explosiones anteriores
                limpiarExplosiones();

                // Actualizar posición del enemigo
                Gestor.getInstance().getTablero().eliminarContent(getX(), getY(), getNombre());
                setX(nextX);
                setY(nextY);
                Gestor.getInstance().getTablero().aniadirContent(getX(), getY(), this);

                // Infligir daño en las casillas adyacentes
                infligirDañoEnRango();

                // Recibir daño si toca una explosión
                if (Gestor.getInstance().getTablero().casillaIncluye(getX(), getY(), "*")) {
                    Gestor.getInstance().getTablero().damage(getX(), getY(), 1, new String[]{"enemigoR"});
                }

                System.out.println("");
                System.out.println("______________________________________________");
            }
        }
    }

    private void infligirDañoEnRango() {
        // Coordenadas de las casillas adyacentes
        int[][] direcciones = {
            {0, 1},  // Arriba
            {0, -1}, // Abajo
            {-1, 0}, // Izquierda
            {1, 0}   // Derecha
        };

        for (int[] dir : direcciones) {
            int targetX = getX() + dir[0];
            int targetY = getY() + dir[1];

            if (targetX >= 0 && targetY >= 0 && targetX <= 16 && targetY <= 10) {
                // Verificar si hay bloques duros, blandos o enemigos
                if (!Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, "bloqueD") &&
                    !Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, "bloqueB") &&
                    !Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, "enemigo")) {

                    // Colocar el ícono de daño
                    Gestor.getInstance().getTablero().generarContent(targetX, targetY, "+");

                    // Daño únicamente a Bomberman
                    if (Gestor.getInstance().getTablero().casillaIncluye(targetX, targetY, BomberMan.getMiBomberMan().getNombre())) {
                        Gestor.getInstance().getTablero().damage(targetX, targetY, 1, new String[]{BomberMan.getMiBomberMan().getNombre()});
                        System.out.println("Enemigo inflige daño a Bomberman en la casilla (" + targetX + ", " + targetY + ").");
                    }
                }
            }
        }
    }

    private void limpiarExplosiones() {
        // Coordenadas de las casillas adyacentes
        int[][] direcciones = {
            {0, 1},  // Arriba
            {0, -1}, // Abajo
            {-1, 0}, // Izquierda
            {1, 0}   // Derecha
        };

        for (int[] dir : direcciones) {
            int targetX = getX() + dir[0];
            int targetY = getY() + dir[1];

            if (targetX >= 0 && targetY >= 0 && targetX <= 16 && targetY <= 10) {
                // Eliminar explosiones previas
                Gestor.getInstance().getTablero().eliminarContent(targetX, targetY, "+");
            }
        }
    }

    @Override
    public boolean posibleMoverse(int pX, int pY) {
        boolean moverse = true;

        // Reglas para impedir movimiento en bloques duros, blandos y otros enemigos
        if (Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueD") || 
            Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "bloqueB") ||
            Gestor.getInstance().getTablero().casillaIncluye(pX, pY, "enemigo")) {
            moverse = false;
            System.out.println("Movimiento bloqueado: casilla (" + pX + ", " + pY + ") contiene un obstáculo.");
        }

        // Regla general de movimiento del enemigo
        return moverse && super.posibleMoverse(pX, pY);
    }
}

