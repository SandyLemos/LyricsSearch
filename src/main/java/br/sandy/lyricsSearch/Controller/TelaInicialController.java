package br.sandy.lyricsSearch.Controller;

import br.sandy.lyricsSearch.Model.Dao.MusicListDaoImp;
import br.sandy.lyricsSearch.Model.SearchLyrics;

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

    private MusicListDaoImp musicDao = MusicListDaoImp.getInstance();
    private SearchLyrics searchLyrics;

    public TelaInicialController() {
        this.searchLyrics = new SearchLyrics();
    }

    @FXML
    public void mostrar(ActionEvent event) throws IOException {
        String n_artista = nome_artista.getText();
        String n_musica = nome_musica.getText();

        Parent root = null;
        MostrarLetraController mostrarController = null;

        // Verifica se a música já está na lista
        for (int i = 0; i < musicDao.MusicList.size(); i++) {
            if (musicDao.MusicList.get(i).getTitle().equals(n_musica) && musicDao.MusicList.get(i).getArtist().equals(n_artista)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/MostrarLetra.fxml"));
                root = loader.load();
                mostrarController = loader.getController();
                mostrarController.MostrarLetra(musicDao.MusicList.get(i).getLyric());
                break;
            }
        }

        // Se a música não está na lista, busca a letra
        if (root == null) {
            String letra = searchLyrics.fetchLyrics(n_artista, n_musica);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/MostrarLetra.fxml"));
            root = loader.load();
            mostrarController = loader.getController();
            mostrarController.MostrarLetra(letra);
        }

        // Exibe o conteúdo
        if (root != null && mostrarController != null) {
            conteudo.getChildren().clear(); // Limpa todos os nós existentes
            conteudo.getChildren().add(root); // Adiciona o novo nó com a letra da música
        }
    }


}
