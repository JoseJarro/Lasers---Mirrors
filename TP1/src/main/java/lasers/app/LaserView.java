package lasers.app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import lasers.Emisor;
import lasers.Nivel;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

public class LaserView {
    private Emisor emisor;
    private List<Line> camino = new ArrayList<>();
    public LaserView(Nivel nivel, Emisor emisor, Integer escala) {
        this.emisor = emisor;
        var laser = emisor.emitirLaser(nivel);
        for (DefaultEdge arista : laser.edgeSet()) {
            var inicio = laser.getEdgeSource(arista).getPosicion();
            var fin = laser.getEdgeTarget(arista).getPosicion();
            var inicioX = (inicio.getPosX()+1) * escala;
            var inicioY = (inicio.getPosY()+1) * escala;
            var finX = (fin.getPosX()+1) * escala;
            var finY = (fin.getPosY()+1) * escala;
            var trazo = new Line(inicioX, inicioY, finX, finY);
            trazo.setStroke(Color.web("#fb0c06"));
            trazo.setStrokeWidth(3);
            camino.add(trazo);
        }
    }

    public List<Line> forma() {
        return camino;
    }
}
