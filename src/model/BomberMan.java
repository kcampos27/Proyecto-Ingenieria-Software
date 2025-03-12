package model;

public class BomberMan {
    private int x, y;

    public BomberMan(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void mover(int nuevoX, int nuevoY) {
        this.x = nuevoX;
        this.y = nuevoY;
    }
}
