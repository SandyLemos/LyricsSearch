package br.sandy.lyricsSearch;

import java.util.Objects;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class FirstPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }
        @Override
        public void start(Stage primarystage) throws Exception{

        // Debugging: imprimir o caminho do recurso
            System.out.println(getClass().getResource("/View/fxml/firstPage.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/firstPage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primarystage.setScene(scene);
        primarystage.setTitle("Welcome!");
        primarystage.show();

        Application.setUserAgentStylesheet(Objects.requireNonNull(getClass().getResource("/assets/themes/dracula.css")).toExternalForm());
        //Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
    }
}
