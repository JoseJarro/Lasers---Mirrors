package lasers.app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lasers.Coordenada;
import lasers.Emisor;
import lasers.Nivel;
import lasers.Vector2D;

public class ObjetivoView {
    private final Circle objetivo;

    public ObjetivoView(Nivel nivel, Coordenada posicion, Integer escala) {
        var radio = 6;
        var posicionX = (posicion.getPosX()+1) * escala;
        var posicionY = (posicion.getPosY()+1) * escala;
        objetivo = new Circle(posicionX, posicionY, radio);
        objetivo.setFill(Color.WHITE);
        for (Emisor e: nivel.getEmisores()) {
            var laser = e.emitirLaser(nivel).vertexSet();
            if (laser.contains(new Vector2D(posicion, ""))) {
                objetivo.setFill(Color.web("#fb0c06"));
            }
        }
        objetivo.setStroke(Color.web("#fb0c06"));
        objetivo.setStrokeWidth(3);
    }

    public Circle forma() {
        return objetivo;
    }
}
