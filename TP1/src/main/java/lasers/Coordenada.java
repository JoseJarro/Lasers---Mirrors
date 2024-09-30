package lasers;

public class Coordenada {
    private int posX;
    private int posY;

    public Coordenada(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public Boolean iguales(Coordenada o) {
        if (this.posX == o.posX && this.posY == o.posY) {
            return true;
        }
        return false;
    }

    public int getPosY() {
        return this.posY;
    }
    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) { this.posX = posX; }
    public void setPosY(int posY) { this.posY = posY; }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.posX, this.posY);
    }
}
