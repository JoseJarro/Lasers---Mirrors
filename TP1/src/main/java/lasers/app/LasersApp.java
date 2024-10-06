package lasers.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import lasers.*;

import java.io.File;
import java.net.URL;
import java.util.Collections;

public class LasersApp extends Application {
    @Override
    public void start(Stage escenario) {


        ListView<String> listaNiveles = agregarListaNiveles();
        listaNiveles.setOnMouseClicked(e -> handleCLic(e, listaNiveles));

        GridPane grilla = new GridPane();
        grilla.setStyle("-fx-background-color: lightgray;"); //css provisorio
        Pane cajaJuego = new Pane(); //puede ser VBOX  o no
        Line laser = new Line(0,50,50,100);
        laser.setStroke(Color.RED);
        VerificadorNivel verificador = new VerificadorNivel();
        Nivel nivel = new Nivel("level1.dat",verificador);
        cajaJuego = dibujarNivel(nivel);


        HBox contenedor = new HBox(listaNiveles, cajaJuego);
        Scene escena = new Scene(contenedor, 400, 300);
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

    private Pane dibujarNivel(Nivel nivel){
        final int ladoBloque = 100;
        Pane root = new Pane();

        for (Celda celda :nivel.getCeldas()) {

            int coordenadaX1 = celda.getCoordenada().getPosX() * (ladoBloque / 2) - (ladoBloque / 2);
            int coordenadaY1 = celda.getCoordenada().getPosY() * (ladoBloque / 2) - (ladoBloque / 2);


            Rectangle rectangleCelda = new Rectangle(coordenadaX1, coordenadaY1, ladoBloque, ladoBloque);
            rectangleCelda.setFill(Color.WHITE);
            rectangleCelda.setStroke(Color.BLACK);

            root.getChildren().add(rectangleCelda);
        }


        for (Bloque bloque :nivel.getBloques()){

            int coordenadaX1 = bloque.getCoordenada().getPosX()*(ladoBloque/2) -(ladoBloque/2);
            int coordenadaY1 = bloque.getCoordenada().getPosY()*(ladoBloque/2) - (ladoBloque/2);


            Rectangle rectangleBloque = new Rectangle(coordenadaX1,coordenadaY1,ladoBloque,ladoBloque);
            rectangleBloque.setStroke(Color.BLACK);

            if (bloque.getTipo() == 'F' ){
             rectangleBloque.setFill(Color.GREY);
            }else if (bloque.getTipo()=='B'){
                rectangleBloque.setFill(Color.GREY);
            }else if (bloque.getTipo()=='R'){
                rectangleBloque.setFill(Color.CYAN);
            }else if (bloque.getTipo()=='G'){
                rectangleBloque.setFill(Color.LIGHTCYAN);
            }else if (bloque.getTipo()=='C') {
                rectangleBloque.setFill(Color.LIGHTBLUE);
            }

        root.getChildren().add(rectangleBloque);

        }
    return root;
    }
}
