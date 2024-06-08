package br.sandy.lyricsSearch;

import br.sandy.lyricsSearch.Model.Music;
import br.sandy.lyricsSearch.Model.MusicApiService;
import br.sandy.lyricsSearch.Model.MusicService;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.List;

// Busca de músicas online através de API

public class MainAPiService {
    public static void main(String[] args){
        MusicService musicSearch = new MusicService();

        // Buscando músicas online no Spotify
        try{
            System.out.println("Músicas encontradas online no Spotify:");
            List<Music> spotifyMusicas = musicSearch.searchMusicOnline("treasure");
            for(Music music : spotifyMusicas){
                System.out.println(music.getTitle() + "-" + music.getArtist());
            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
        }

        // Buscando músicas online no Lyrics.ovh
        try{
            System.out.println("Músicas encontradas online no Lyrics.ovh:");
            List<Music> lyricsMusicas = musicSearch.searchMusicOnline("lana del rey");
            for(Music music : lyricsMusicas){
                new MusicApiService().catchLyric(music);
                System.out.println(music.getTitle() + " - " + music.getLyric());
            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
        }

        // Buscar e imprimir letra de uma música específica
        searchAndPrintSpecificLyric();
    }

    private static void searchAndPrintSpecificLyric() {
        String artist = "Lana Del Rey";
        String title = "Video Games";

        Music specificMusic = null;
        try {
            List<Music> lyricsMusicas = new MusicService().searchMusicOnline(artist + " " + title);
            for (Music music : lyricsMusicas) {
                if (music.getArtist().equalsIgnoreCase(artist) && music.getTitle().equalsIgnoreCase(title)) {
                    specificMusic = music;
                    break;
                }
            }

            if (specificMusic != null) {
                new MusicApiService().catchLyric(specificMusic);
                System.out.println("Letra de " + specificMusic.getArtist() + " - " + specificMusic.getTitle() + ":");
                System.out.println(specificMusic.getLyric());
            } else {
                System.out.println("A música " + title + " de " + artist + " não foi encontrada.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
