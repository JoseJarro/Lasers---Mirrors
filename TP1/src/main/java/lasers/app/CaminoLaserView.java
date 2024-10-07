package lasers.app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class CaminoLaserView implements View {
    private final Line lineaLaser;

    public CaminoLaserView(Integer iniX, Integer iniY, Integer finX, Integer finY) {
        lineaLaser = new Line(iniX, iniY, finX, finY);
        lineaLaser.setStroke(Color.web("#fb0c06"));
        lineaLaser.setStrokeWidth(3);
    }

    @Override
    public Shape render() {
        return lineaLaser;
    }

}