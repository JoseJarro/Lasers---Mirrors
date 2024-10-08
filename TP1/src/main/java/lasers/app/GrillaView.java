package lasers.app;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class GrillaView implements View {
    private final Rectangle grilla;

    public GrillaView(Color color, Integer ancho, Integer alto) {
        grilla = new Rectangle(0,0, ancho, alto);
        grilla.setFill(color);
    }

    @Override
    public Shape render() {
        return grilla;
    }
}