package lasers.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

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
        VBox cajaJuego = new VBox(grilla); //puede ser VBOX  o no
        cajaJuego.setPadding(new Insets(20));

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
}
