package proyecto;

public class BomberMan {
    private int x, y; // Posición en la cuadrícula

    public BomberMan(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void mover(int dx, int dy, int maxCols, int maxRows) {
        int nuevoX = x + dx;
        int nuevoY = y + dy;

        // Evita que salga del tableru
        if (nuevoX >= 0 && nuevoX < maxCols) x = nuevoX;
        if (nuevoY >= 0 && nuevoY < maxRows) y = nuevoY;
    }
}
