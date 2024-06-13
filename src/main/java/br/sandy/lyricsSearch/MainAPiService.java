package br.sandy.lyricsSearch;

import br.sandy.lyricsSearch.Model.Music;
import br.sandy.lyricsSearch.Model.MusicApiService;
import br.sandy.lyricsSearch.Model.MusicService;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.List;

// Busca de músicas online através de API

public class MainAPiService {
    public static void main(String[] args) {
        // Buscar e imprimir letra de uma música específica
        searchAndPrintSpecificLyric();
    }

    private static void searchAndPrintSpecificLyric() {
        String artist = "Coldplay";
        String title = "Yellow";

        try {
            Music specificMusic = new Music("id_da_musica", title, artist, "nome_do_album", "letra_inicial");

            MusicService musicService = new MusicService();
            musicService.catchLyric(specificMusic);

            System.out.println("Letra de " + specificMusic.getArtist() + " - " + specificMusic.getTitle() + ":");
            System.out.println(specificMusic.getLyric());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
