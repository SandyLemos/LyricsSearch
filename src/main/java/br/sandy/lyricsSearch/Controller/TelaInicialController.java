package com.example.lyrics;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import javafx.event.ActionEvent;
import java.io.IOException;

public class TelaInicialController {

    @FXML
    private TextField nome_artista;

    @FXML
    private TextField nome_musica;


    @FXML
    private StackPane conteudo;

    MusicListDaoImp musicDao = MusicListDaoImp.getInstance();

    public void mostrar(ActionEvent event) throws IOException {

        boolean existe = false;
        String n_artista = nome_artista.getText();
        String n_musica = nome_musica.getText();

        MostrarLetraController mostrarController = null;
        Parent root = null; // Declare root outside the loop

        for (int i = 0; i < musicDao.MusicList.size(); i++) {
            if (musicDao.MusicList.get(i).getTitle().equals(n_musica) && musicDao.MusicList.get(i).getArtist().equals(n_artista)) {
                existe = true;
                System.out.println("Encontrado");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarLetra.fxml"));
                root = loader.load();
                mostrarController = loader.getController();
                mostrarController.MostrarLetra(musicDao.MusicList.get(i).getLyric());
                break;
            }
        }

        if (existe && mostrarController != null) {
            conteudo.getChildren().removeAll();
            conteudo.getChildren().setAll(root);
        }
    }



}

