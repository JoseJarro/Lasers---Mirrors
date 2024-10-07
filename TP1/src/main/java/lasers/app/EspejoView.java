package lasers.app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lasers.*;

public class EspejoView {
    private final Rectangle rect;

    public EspejoView(Bloque bloque, Integer escala) {
        var tamanioCelda = escala * 2;
        var padding = tamanioCelda / 2;
        var posicion = bloque.getCoordenada();
        var posicionX = (posicion.getPosX()-1) * escala  + padding;
        var posicionY = (posicion.getPosY()-1) * escala  + padding;
        rect = new Rectangle(posicionX, posicionY, tamanioCelda, tamanioCelda);
        rect.setFill(Color.web("#0c7e7e"));
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(3);

        double[] offsetX = {0};
        double[] offsetY = {0};
        rect.setOnDragDetected(e -> {
            offsetX[0] = e.getX() - rect.getX();
            offsetY[0] = e.getY() - rect.getY();
            System.out.println("HOlaaa");
            e.consume();
        });

        rect.setOnMouseDragged(e -> {
            rect.setX(e.getX() - offsetX[0]);
            rect.setY(e.getY() - offsetY[0]);
            System.out.println("HOlaaa");
            e.consume();
        });

    }

    public Rectangle forma() {
        return rect;
    }
}