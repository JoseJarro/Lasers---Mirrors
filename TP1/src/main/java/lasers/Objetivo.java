package lasers;

import org.jgrapht.graph.SimpleGraph;

public class Objetivo {
    private Boolean estado;
    private Coordenada posicion;

    public Objetivo(int x, int y) {
        this.posicion = new Coordenada(x, y);
        this.estado = false;
    }

    public Boolean objetivoAlcanzado(Nivel nivel) {
        for (Emisor emisor : nivel.getEmisores()) {
            SimpleGraph<Vector2D, null> laser = emisor.emitirLaser(nivel);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Objetivo: " + super.toString();
    }
}
