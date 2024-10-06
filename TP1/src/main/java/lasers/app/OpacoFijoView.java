package lasers.app;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import lasers.*;

public class OpacoFijoView {
    private Coordenada posicion;
    private final Path grupo;

    public OpacoFijoView(Bloque bloque, Integer escala) {
        var tamanioCelda = escala * 2;
        var padding = tamanioCelda / 2;
        posicion = bloque.getCoordenada();
        var posicionX = (posicion.getPosX()-1) * escala  + padding;
        var posicionY = (posicion.getPosY()-1) * escala  + padding;

        grupo = new Path();
        grupo.getElements().add(new MoveTo(posicionX,posicionY));
        grupo.getElements().add(new LineTo(posicionX + tamanioCelda,posicionY));
        grupo.getElements().add(new LineTo(posicionX + tamanioCelda,posicionY + tamanioCelda));
        grupo.getElements().add(new LineTo(posicionX,posicionY + tamanioCelda));
        grupo.getElements().add(new LineTo(posicionX,posicionY));

        grupo.getElements().add(new MoveTo(posicionX,posicionY));
        grupo.getElements().add(new LineTo(posicionX + tamanioCelda,posicionY + tamanioCelda));

        grupo.getElements().add(new MoveTo(posicionX,posicionY + tamanioCelda));
        grupo.getElements().add(new LineTo(posicionX + tamanioCelda,posicionY));

        grupo.setFill(Color.web("#506266"));
        grupo.setStroke(Color.BLACK);

        grupo.setOnMouseClicked(e -> {
            System.out.println(posicion.getPosX() + "," + posicion.getPosY());
        });
    }

    public Path forma() {
        return grupo;
    }

}
