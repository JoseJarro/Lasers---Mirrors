package lasers.app;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import lasers.*;

public class CristalView {
    private Coordenada posicion;
    private final Rectangle rect;

    public CristalView(Bloque bloque, Integer escala) {
        var tamanioCelda = escala * 2;
        var padding = tamanioCelda / 2;
        posicion = bloque.getCoordenada();
        var posicionX = (posicion.getPosX()-1) * escala  + padding;
        var posicionY = (posicion.getPosY()-1) * escala  + padding;
        rect = new Rectangle(posicionX, posicionY, tamanioCelda, tamanioCelda);
        rect.setFill(Color.web("#13c8b5"));

        rect.setOnMouseClicked(e -> {
            System.out.println(posicion.getPosX() + "," + posicion.getPosY());
        });
    }

    public Rectangle forma() {
        return rect;
    }
}