package lasers.app;
import javafx.application.Application;

import javafx.stage.Stage;
import lasers.*;

public class LasersApp extends Application {
    @Override
    public void start(Stage escenario) {
        Juego juego = new Juego();
        JuegoView juegoView = new JuegoView(escenario, juego);
    }

    public static void main(String[] args) {
        launch(args);
    }

}

