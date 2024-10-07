package lasers.app;

import lasers.Emisor;
import lasers.Nivel;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

public class LaserView {
    private final List<View> camino = new ArrayList<>();

    public LaserView(Nivel nivel, Emisor emisor, Integer escala) {
        var laser = emisor.emitirLaser(nivel);
        for (DefaultEdge arista : laser.edgeSet()) {
            var inicio = laser.getEdgeSource(arista).getPosicion();
            var fin = laser.getEdgeTarget(arista).getPosicion();
            var inicioX = (inicio.getPosX()+1) * escala;
            var inicioY = (inicio.getPosY()+1) * escala;
            var finX = (fin.getPosX()+1) * escala;
            var finY = (fin.getPosY()+1) * escala;
            View trazo = new CaminoLaserView(inicioX, inicioY, finX, finY);
            camino.add(trazo);
        }
    }

    public List<View> mostrarCamino() {
        return camino;
    }
}
