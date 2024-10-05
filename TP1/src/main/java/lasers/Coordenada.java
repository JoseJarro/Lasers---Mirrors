package lasers;

import java.util.Objects;

public class Coordenada {
    private int posX;
    private int posY;

    public Coordenada(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordenada otro = (Coordenada) o;
        return this.posX == otro.posX && this.posY == otro.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
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
        return String.format("(%d:%d)", this.posX, this.posY);
    }
}
