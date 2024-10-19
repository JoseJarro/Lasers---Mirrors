package lasers.app;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lasers.*;
import javafx.scene.Group;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoView extends Group {

    public JuegoView(Stage escenario, Juego juego) {
        Pane grilla = crearGrilla(juego);
        VBox cajaJuego = new VBox(grilla);
        cajaJuego.setPadding(new Insets(20));

        ListView<String> listaNiveles = agregarListaNiveles();
        listaNiveles.setPrefWidth(150);
        listaNiveles.setStyle("-fx-font-size: 16px;");
        listaNiveles.setOnMouseClicked(_ -> {
            int idx = listaNiveles.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                String elemento = listaNiveles.getItems().get(idx) + ".dat";
                juego.cambiarNivel(elemento);
                var nuevoNivel = crearGrilla(juego);
                cajaJuego.getChildren().setAll(nuevoNivel);
            }
        });

        HBox contenedor = new HBox(listaNiveles, cajaJuego);
        Scene escena = new Scene(contenedor);
        escenario.setTitle("Lasers");
        escenario.setScene(escena);
        escenario.show();
    }

    private ListView<String> agregarListaNiveles() {
        var niveles = new ListView<String>();
        URL resourceUrl = getClass().getResource("/");
        if (resourceUrl != null) {
            File resourceDirectory = new File(resourceUrl.getFile());
            File [] files = resourceDirectory.listFiles();
            ObservableList<String> fileNames = FXCollections.observableArrayList();
            if (files != null) {
                for (File file : files) {
                    if (!(file.getName().endsWith(".dat"))) {
                        continue;
                    }
                    var nombre = file.getName().split(".dat")[0];
                    fileNames.add(nombre);
                }
            }
            Collections.sort(fileNames);
            niveles.setItems(fileNames);
        }
        return niveles;
    }

    private Pane crearGrilla(Juego juego) {
        Pane grilla = new Pane();
        Nivel nivel = juego.getNivel();
        var dimension = nivel.getDimension();
        var escala = 30;
        var tamanioCelda = escala * 2;
        var nivelCompleto = juego.juegoTerminado();
        var ancho = dimension.getPosX()*escala + tamanioCelda;
        var alto = dimension.getPosY()*escala + tamanioCelda;
        List<View> formas = new ArrayList<>();
        formas.add(crearFondo(nivelCompleto, ancho, alto, grilla));
        formas.addAll(crearCeldas(nivel, escala));
        formas.addAll(crearBloques(nivel, escala));
        formas.addAll(crearLasers(nivel, escala));
        formas.addAll(crearObjetivos(nivel, escala));
        formas.addAll(crearEmisores(nivel, escala));
        for (View forma : formas) {
            grilla.getChildren().add(forma.render());
        }
        manejarEventosGrilla(grilla, juego, tamanioCelda);
        return grilla;
    }

    private View crearFondo(Boolean nivelCompleto, Integer ancho, Integer alto, Pane grilla) {
        View fondo;
        if (nivelCompleto) {
            fondo = new GrillaView(Color.LIGHTGREEN, ancho, alto);
            grilla.setMouseTransparent(true);
        }
        else{
            fondo = new GrillaView(Color.LIGHTGRAY, ancho, alto);
        }
        return fondo;
    }

    private List<View> crearCeldas(Nivel nivel, Integer escala) {
        List<View> formas = new ArrayList<>();
        for (Celda c: nivel.getCeldas()) {
            formas.add((new CeldaView(c, escala)));
        }
        return formas;
    }

    private List<View> crearBloques(Nivel nivel, Integer escala) {
        List<View> formas = new ArrayList<>();
        for (Bloque bloque: nivel.getBloques()) {
            switch (bloque.getTipo()) {
                case 'F' : formas.add((new OpacoFijoView(bloque, escala))); break;
                case 'B' : formas.add((new OpacoMovilView(bloque, escala))); break;
                case 'R' : formas.add((new EspejoView(bloque, escala))); break;
                case 'G' : formas.add((new VidrioView(bloque, escala))); break;
                case 'C' : formas.add((new CristalView(bloque, escala))); break;
            }
        }
        return formas;
    }

    private List<View> crearLasers(Nivel nivel, Integer escala) {
        List<View> formas = new ArrayList<>();
        for (Emisor e: nivel.getEmisores()) {
            var laser = new LaserView(nivel, e, escala).mostrarCamino();
            formas.addAll(laser);
        }
        return formas;
    }

    private List<View> crearObjetivos(Nivel nivel, Integer escala) {
        List<View> formas = new ArrayList<>();
        for (Coordenada o: nivel.getObjetivos()) {
            formas.add((new ObjetivoView(nivel, o, escala)));
        }
        return formas;
    }

    private List<View> crearEmisores(Nivel nivel, Integer escala) {
        List<View> formas = new ArrayList<>();
        for (Emisor e: nivel.getEmisores()) {
            formas.add((new EmisorView(e.getPosicion(), escala)));
        }
        return formas;
    }

    private void manejarEventosGrilla(Pane grilla, Juego juego, Integer tamanioCelda) {
        var posInicial = new Coordenada(0,0);
        grilla.setOnMousePressed(e -> {
            Coordenada pos = calcularPosicionMouse(e.getX(), e.getY(), tamanioCelda);
            posInicial.setPosX(pos.getPosX());
            posInicial.setPosY(pos.getPosY());
        });

        grilla.setOnMouseReleased(e -> {
            Coordenada posFinal = calcularPosicionMouse(e.getX(), e.getY(), tamanioCelda);

            juego.validoMoverBloque(posInicial, posFinal);
            var nivelActualizado = crearGrilla(juego);
            VBox cajaJuego = (VBox) grilla.getParent();
            cajaJuego.getChildren().setAll(nivelActualizado);
        });

    }

    private Coordenada calcularPosicionMouse(double x, double y, Integer tamanioCelda) {
        var padding = tamanioCelda / 2;
        var posX = (int) x - padding;
        var posY = (int) y - padding;
        posX = (posX / 60) * 2 + 1;
        posY = (posY / 60) * 2 + 1;
        return new Coordenada(posX, posY);
    }
}
