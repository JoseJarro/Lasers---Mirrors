package lasers.app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import lasers.*;

public class OpacoMovilView implements View {
    private final Rectangle rect;

    public OpacoMovilView(Bloque bloque, Integer escala) {
        var tamanioCelda = escala * 2;
        var padding = tamanioCelda / 2;
        var posicion = bloque.getCoordenada();
        var posicionX = (posicion.getPosX()-1) * escala  + padding;
        var posicionY = (posicion.getPosY()-1) * escala  + padding;
        rect = new Rectangle(posicionX, posicionY, tamanioCelda, tamanioCelda);
        rect.setFill(Color.web("#506266"));
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(3);
    }

    @Override
    public Shape render() {
        return rect;
    }

}