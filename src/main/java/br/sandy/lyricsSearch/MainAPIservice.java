package br.sandy.lyricsSearch;

import br.sandy.lyricsSearch.Model.Music;
import br.sandy.lyricsSearch.Model.MusicApiService;
import br.sandy.lyricsSearch.Model.MusicService;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.List;

// Busca de músicas online através de API

public class MainAPIservice {
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
    }
}
