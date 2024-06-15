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

    MusicListDaoImp musicDao=MusicListDaoImp.getInstance();

    public void addMusic(ActionEvent event){
        Music musica = new Music(titulo.getText(), artista.getText(), album.getText(), letra.getText());

        if (titulo.getText()!=null && artista.getText()!=null && album.getText()!=null && letra.getText()!=null){
            musicDao.addMusics(musica);

        }
        for (int i=0; i<musicDao.MusicList.size();i++) {
            System.out.println("titulo:"+musicDao.MusicList.get(i).getTitle());
        }
    }

}
