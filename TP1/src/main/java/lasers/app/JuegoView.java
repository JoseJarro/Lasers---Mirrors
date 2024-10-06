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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import lasers.*;

import javafx.scene.Group;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoView extends Group {
    private final Juego juego;

    public JuegoView(Stage escenario, Juego juego) {
        this.juego = juego;
        Pane grilla = crearGrilla(juego);
        VBox cajaJuego = new VBox(grilla);
        cajaJuego.setPadding(new Insets(20));

        ListView<String> listaNiveles = agregarListaNiveles();
        listaNiveles.setOnMouseClicked(e -> {
            int idx = listaNiveles.getSelectionModel().getSelectedIndex();
            if (idx >= 0) {
                String elemento = listaNiveles.getItems().get(idx) + ".dat";
                System.out.println(elemento);
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
        Nivel nivel = juego.getNivel();
        var dimension = nivel.getDimension();
        var escala = 30;
        var tamanioCelda = escala * 2;
        var padding = tamanioCelda / 2;
        Pane grilla = new Pane();
        var ancho = dimension.getPosX()*escala + tamanioCelda;
        var alto = dimension.getPosY()*escala + tamanioCelda;
        Rectangle rect = new Rectangle(0, 0, ancho, alto);
        rect.setFill(Color.LIGHTGRAY);


        List<Shape> formas = new ArrayList<>();
        formas.add(rect);
        for (Celda c: nivel.getCeldas()) {
            formas.add((new CeldaView(c, escala)).forma());
        }
        for (Bloque bloque: nivel.getBloques()) {
            if (bloque.getTipo() == 'F' ){
                formas.add((new OpacoFijoView(bloque, escala)).forma());
            } else if (bloque.getTipo()=='B'){
                formas.add((new OpacoMovilView(bloque, escala)).forma());
            }else if (bloque.getTipo()=='R'){
                formas.add((new EspejoView(bloque, escala)).forma());
            }else if (bloque.getTipo()=='G'){
                formas.add((new VidrioView(bloque, escala)).forma());
            }else if (bloque.getTipo()=='C') {
                formas.add((new CristalView(bloque, escala)).forma());
            }
        }

        for (Emisor e: nivel.getEmisores()) {
            var laser = new LaserView(nivel, e, escala).forma();
            formas.addAll(laser);
        }
        for (Coordenada o: nivel.getObjetivos()) {
            formas.add((new ObjetivoView(nivel, o, escala)).forma());
        }
        for (Emisor e: nivel.getEmisores()) {
            formas.add((new EmisorView(e.getPosicion(), escala)).forma());
        }

        Rectangle tapa = new Rectangle(0, 0, ancho, alto);
        tapa.setFill(Color.TRANSPARENT);

        var posInicial = new Coordenada(0,0);
        tapa.setOnMousePressed(e -> {
            var inicioX = (int) e.getX() - padding;
            var inicioY = (int) e.getY() - padding;
            inicioX = (inicioX / 60) * 2 + 1;
            inicioY = (inicioY / 60) * 2 + 1;

            posInicial.setPosX(inicioX);
            posInicial.setPosY(inicioY);
            System.out.println(inicioX + "," + inicioY);
        });

        tapa.setOnMouseReleased(e -> {
            var finalX = (int) e.getX() - padding;
            var finalY = (int) e.getY() - padding;
            finalX = (finalX / 60) * 2 + 1;
            finalY = (finalY / 60) * 2 + 1;

            var posFinal = new Coordenada(finalX, finalY);

            juego.moverBloque(posInicial, posFinal);
            var nivelActualizado = crearGrilla(juego);
            VBox cajaJuego = (VBox) grilla.getParent();
            cajaJuego.getChildren().setAll(nivelActualizado);
            System.out.println(finalX + "," + finalY);
        });

        formas.add(tapa);

        for (Shape forma : formas) {
            grilla.getChildren().add(forma);
        }

        return grilla;


    }

}
