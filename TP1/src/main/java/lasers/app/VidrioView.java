package lasers.app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lasers.*;

public class VidrioView {
    private final Rectangle rect;

    public VidrioView(Bloque bloque, Integer escala) {
        var tamanioCelda = escala * 2;
        var padding = tamanioCelda / 2;
        var posicion = bloque.getCoordenada();
        var posicionX = (posicion.getPosX()-1) * escala  + padding;
        var posicionY = (posicion.getPosY()-1) * escala  + padding;
        rect = new Rectangle(posicionX, posicionY, tamanioCelda, tamanioCelda);
        rect.setFill(Color.LIGHTCYAN);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(3);

        rect.setOnMouseClicked(e -> {
            System.out.println(posicion.getPosX() + "," + posicion.getPosY());
        });
    }

    public Rectangle forma() {
        return rect;
    }
}