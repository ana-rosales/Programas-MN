package fes.aragon.inicio;

import java.io.IOException;

import fes.aragon.ui.PaginasFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Inicio extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(PaginasFXML.INICIO.getPagina());
        
        Scene scene = new Scene(root);
     
        primaryStage.setScene(scene);
        primaryStage.setTitle("Metodos Numericos");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
