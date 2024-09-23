package lasers;

public class Coordenada {
    private int posX;
    private int posY;

    public Coordenada(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    public int getPosY() {
        return this.posY;
    }
    public int getPosX() {
        return this.posX;
    }
    @Override
    public String toString() {
        return String.format("(%d,%d)", this.posX, this.posY);
    }
}
