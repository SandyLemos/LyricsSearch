package br.sandy.lyricsSearch;

import br.sandy.lyricsSearch.Model.Music;
import br.sandy.lyricsSearch.Model.MusicOnlineSearch;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;

// Busca de músicas online através de API

public class MainExample {
    public static void main(String[] args){
        MusicOnlineSearch musicReg = new MusicOnlineSearch();

        // Buscando músicas online
        try{
            System.out.println("Músicas encontradas online:");
            for(Music music : musicReg.searchMusicOnline("treasure")){
                System.out.println(music.getTitle() + "-" + music.getArtist());
            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
