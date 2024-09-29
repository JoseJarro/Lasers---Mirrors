package lasers;

import java.util.List;

public class Objetivo {
    private final Coordenada posicion;

    public Objetivo(int x, int y) {
        this.posicion = new Coordenada(x, y);
    }

    public Boolean objetivoAlcanzado(Nivel nivel) {
        for (Emisor emisor : nivel.getEmisores()) {
            List<Vector2D> laser = emisor.emitirLaser(nivel);
            for (Vector2D v : laser) {
                if (v.getPosicion().getPosX() == this.posicion.getPosX() && v.getPosicion().getPosY() == this.posicion.getPosY()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Objetivo: " + super.toString();
    }
}
