package br.sandy.lyricsSearch.Controller;

import br.sandy.lyricsSearch.Model.Music;
import br.sandy.lyricsSearch.Model.Dao.MusicListDaoImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddMusicaController {
    @FXML
    private TextField album;

    @FXML
    private TextField artista;

    @FXML
    private TextField letra;

    @FXML
    private TextField titulo;

    @FXML
    private Button voltar;

    @FXML
    void changeToHelloScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) voltar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    MusicListDaoImp musicDao = MusicListDaoImp.getInstance();

    public void addMusic(ActionEvent event) {
        if (!titulo.getText().isEmpty() && !artista.getText().isEmpty() && !album.getText().isEmpty() && !letra.getText().isEmpty()) {
            Music musica = new Music(titulo.getText(), artista.getText(), album.getText(), letra.getText());
            musicDao.addMusics(musica);
        }
        for (Music music : musicDao.getAllMusics()) {
            System.out.println("titulo: " + music.getTitle());
        }
    }

}
