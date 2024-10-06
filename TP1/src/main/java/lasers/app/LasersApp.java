package lasers.app;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.Graph;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import javafx.stage.Stage;
import javafx.scene.control.ListView;
import lasers.*;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class LasersApp extends Application {
    @Override
    public void start(Stage escenario) {
        Juego juego = new Juego();



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

    public static void main(String[] args) {
        launch(args);
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
    
    private Pane crearGrilla(Juego juego) {
        Nivel nivel = juego.getNivel();
        var dimension = nivel.getDimension();
        var proporcion = 30;
        var padding = 30;
        var tamanioCelda = 60;
        Pane grilla = new Pane();
        Rectangle rect = new Rectangle(0, 0, dimension.getPosX()*proporcion + 2 * padding, dimension.getPosY()*proporcion + 2 * padding);
        rect.setFill(Color.LIGHTGRAY);
        grilla.getChildren().add(rect);


        //ListBLOQUES
        List<Bloque> bloques = nivel.getBloques();
        for (Bloque bloque: bloques) {
            var pos = bloque.getCoordenada();
            var posicionX = (pos.getPosX()-1) * proporcion  + padding;
            var posicionY = (pos.getPosY()-1) * proporcion  + padding;
            rect = new Rectangle(posicionX, posicionY, tamanioCelda, tamanioCelda);
            if (bloque.getTipo() == 'F' ){
                rect.setFill(Color.web("#506266"));
            }else if (bloque.getTipo()=='B'){
                rect.setFill(Color.web("#506266"));
            }else if (bloque.getTipo()=='R'){
                rect.setFill(Color.web("#0c7e7e"));
            }else if (bloque.getTipo()=='G'){
                rect.setFill(Color.LIGHTCYAN);
            }else if (bloque.getTipo()=='C') {
                rect.setFill(Color.web("#13c8b5"));
            }
            grilla.getChildren().add(rect);
        }


        //CELDAS
        List<Celda> celdas = nivel.getCeldas();
        for (Celda c: celdas) {
            var pos = c.getCoordenada();
            var posicionX = (pos.getPosX()-1) * proporcion  + padding;
            var posicionY = (pos.getPosY()-1) * proporcion  + padding;
            rect = new Rectangle(posicionX,posicionY, tamanioCelda, tamanioCelda);
            rect.setFill(Color.TRANSPARENT);
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(3);
            grilla.getChildren().add(rect);
        }

        //LASERS:
        for (Emisor e: nivel.getEmisores()) {
            var laser = e.emitirLaser(nivel);
            for (DefaultEdge arista : laser.edgeSet()) {
                var inicio = laser.getEdgeSource(arista).getPosicion();
                var fin = laser.getEdgeTarget(arista).getPosicion();
                var inicioX = inicio.getPosX() * proporcion + padding;
                var inicioY = inicio.getPosY() * proporcion + padding;

                var finX = fin.getPosX() * proporcion + padding;
                var finY = fin.getPosY() * proporcion + padding;
                var linea = new Line(inicioX, inicioY, finX, finY);
                linea.setStroke(Color.web("#fb0c06"));
                linea.setStrokeWidth(3);
                grilla.getChildren().add(linea);

            }
        }
        //ListObjetivos
        var radio = 6;
        List<Coordenada> objetivos = nivel.getObjetivos();
        for (Coordenada o: objetivos) {
            var posicionX = (o.getPosX() * proporcion) + padding;
            var posicionY = (o.getPosY() * proporcion) + padding;
            Circle circulo = new Circle(posicionX, posicionY, radio);
            circulo.setFill(Color.WHITE);
            for (Emisor e: nivel.getEmisores()) {
                var laser = e.emitirLaser(nivel).vertexSet();
                if (laser.contains(new Vector2D(o, ""))) {
                    circulo.setFill(Color.web("#fb0c06"));
                }
            }
            circulo.setStroke(Color.web("#fb0c06"));
            circulo.setStrokeWidth(3);
            grilla.getChildren().add(circulo);
        }

        //ListEmisores
        radio = 6;
        List<Emisor> emisores = nivel.getEmisores();
        for (Emisor e: emisores) {
            var posicion = e.getPosicion();
            var posicionX = (posicion.getPosX() * proporcion) + padding;
            var posicionY = (posicion.getPosY() * proporcion) + padding;
            var circulo = new Circle(posicionX, posicionY, radio);
            circulo.setFill(Color.web("#fb0c06"));
            grilla.getChildren().add(circulo);
        }



        return grilla;


    }
}

