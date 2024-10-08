package lasers.app;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import lasers.Coordenada;

public class EmisorView implements View{
    private final Circle emisor;

    public EmisorView(Coordenada posicion, Integer escala) {
        var radio = 6;
        var posicionX = (posicion.getPosX()+1) * escala;
        var posicionY = (posicion.getPosY()+1) * escala;
        emisor = new Circle(posicionX, posicionY, radio);
        emisor.setFill(Color.web("#fb0c06"));
    }

    @Override
    public Shape render() {
        return emisor;
    }
}