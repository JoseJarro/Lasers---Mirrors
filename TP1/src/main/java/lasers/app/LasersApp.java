package lasers.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import lasers.*;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class LasersApp extends Application {
    @Override
    public void start(Stage escenario) {

        ListView<String> listaNiveles = agregarListaNiveles();
        listaNiveles.setOnMouseClicked(e -> handleCLic(e, listaNiveles));

        Canvas grilla = crearGrilla();

        VBox cajaJuego = new VBox(grilla);
        cajaJuego.setPadding(new Insets(20));
        HBox contenedor = new HBox(listaNiveles, cajaJuego);
        Scene escena = new Scene(contenedor);
        escenario.setTitle("Lasers");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void handleCLic(MouseEvent e, ListView<String> listaNiveles) {
        //handle de evento cuando se hace click en un nivel, por el momento imprime solo lo que deberia
        //devolver.
        int idx = listaNiveles.getSelectionModel().getSelectedIndex();
        if (idx >= 0) {
            String elemento = listaNiveles.getItems().get(idx);
            System.out.println(elemento + ".dat");
        }
    }

    private ListView<String> agregarListaNiveles() {
        //para retornar un objeto de javafx que es una lista que contiene los niveles de resources
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

    private Canvas crearGrilla() {
        Juego juego = new Juego();
        Nivel nivel = juego.getNivel();
        var dimension = nivel.getDimension();
        var proporcion = 30;
        var padding = 30;
        var tamanioCelda = 60;
        Canvas grilla = new Canvas(dimension.getPosX()*proporcion + 2 * padding, dimension.getPosY()*proporcion + 2 * padding);
        GraphicsContext gc = grilla.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, grilla.getWidth(), grilla.getHeight());


        //CELDAS
        List<Celda> celdas = nivel.getCeldas();
        gc.setFill(Color.TRANSPARENT);
        for (Celda c: celdas) {
            var pos = c.getCoordenada();
            var posicionX = (pos.getPosX()-1) * proporcion  + padding;
            var posicionY = (pos.getPosY()-1) * proporcion  + padding;
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(3);
            gc.strokeRect(posicionX, posicionY, tamanioCelda, tamanioCelda);

        }

        //ListObjetivos
        var radio = 6;
        List<Coordenada> objetivos = nivel.getObjetivos();
        gc.setFill(Color.WHITE);
        for (Coordenada o: objetivos) {
            var posicionX = (o.getPosX() * proporcion) - radio + padding;
            var posicionY = (o.getPosY() * proporcion) - radio + padding;
            gc.fillOval(posicionX, posicionY, radio*2, radio*2);
            gc.setStroke(Color.ORANGERED);
            gc.setLineWidth(3);
            gc.strokeOval(posicionX, posicionY, radio*2, radio*2);
        }

        //ListEmisores
        radio = 6;
        List<Emisor> emisores = nivel.getEmisores();
        gc.setFill(Color.RED);
        for (Emisor e: emisores) {
            var posicion = e.getPosicion();
            var posicionX = (posicion.getPosX() * proporcion) - radio + padding;
            var posicionY = (posicion.getPosY() * proporcion) - radio + padding;
            gc.fillOval(posicionX, posicionY, radio*2, radio*2);
        }

        List<Bloque> bloques = nivel.getBloques();
        for (Bloque b: bloques) {
            var pos = b.getCoordenada();
            var posicionX = (pos.getPosX()-1) * proporcion  + padding;
            var posicionY = (pos.getPosY()-1) * proporcion  + padding;
            if (b.toString().equals("ESPEJO")) {
                System.out.println("TRUEEEE");
                gc.setFill(Color.DARKCYAN);
            }
            gc.fillRect(posicionX, posicionY, tamanioCelda, tamanioCelda);


        }

        return grilla;

        //ListBLOQUES

    }

}

