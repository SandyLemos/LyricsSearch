package com.example.lyrics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaInicial extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader carregar = new FXMLLoader(getClass().getResource("TelaInicial.fxml"));
        Scene scene = new Scene(carregar.load());
        stage.setScene(scene);
        stage.show();
    }
}
