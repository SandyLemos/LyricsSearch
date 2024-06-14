package com.example.lyrics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MostrarLetraController {

    @FXML
    private Label letra;

    public void MostrarLetra(String mensagem){
        letra.setText(mensagem);
    }
}
