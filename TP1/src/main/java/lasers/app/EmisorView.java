package lasers.app;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lasers.Coordenada;
import lasers.Emisor;
import lasers.Nivel;
import lasers.Vector2D;

public class EmisorView {
    private Circle emisor;

    public EmisorView(Coordenada posicion, Integer escala) {
        var radio = 6;
        var posicionX = (posicion.getPosX()+1) * escala;
        var posicionY = (posicion.getPosY()+1) * escala;
        emisor = new Circle(posicionX, posicionY, radio);
        emisor.setFill(Color.web("#fb0c06"));
    }

    public Circle forma() {
        return emisor;
    }
}