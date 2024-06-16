package br.sandy.lyricsSearch.Controller;

import br.sandy.lyricsSearch.Model.Music;
import br.sandy.lyricsSearch.Model.Dao.MusicListDaoImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddMusicaController {
    @FXML
    private TextField album;

    @FXML
    private TextField artista;

    @FXML
    private TextField letra;

    @FXML
    private TextField titulo;

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
