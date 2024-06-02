package br.sandy.lyricsSearch;

import br.sandy.lyricsSearch.Model.Music;
import br.sandy.lyricsSearch.Model.MusicRegistration;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;

// Testando a classe MusicRegistration

public class MainExample {
    public static void main(String[] args){
        MusicRegistration musicReg = new MusicRegistration();

        // Adicionando músicas localmente
        Music music1 = new Music("1", "Title1", "Artist1", "Album1", "Lyric1");
        musicReg.addMusic(music1);

        Music music2 = new Music("2", "Title2", "Artist2", "Album2", "Lyric2");
        musicReg.addMusic(music2);

        // Listando músicas locais
        System.out.println("Músicas loais: ");
        for(Music music : musicReg.listMusic()){
            System.out.println(music.getTitle());
        }

        // Buscando uma múscia localmente
        Music searchedMusic = musicReg.searchMusic("Title", null, null, null, null);
        System.out.println("Música local encontrada: " + searchedMusic.getTitle());

        // Removendo uma música localmente
        musicReg.removeMusic("Title2");

        // Confirmando remoção
        System.out.println("Músicas locais após remoção:");
        for(Music music : musicReg.listMusic()){
            System.out.println(music.getTitle());
        }

        // Buscando músicas online
        try{
            System.out.println("Músicas encontradas online:");
            for(Music music : musicReg.searchMusicOnline("Title1")){
                System.out.println(music.getTitle() + "-" + music.getArtist());
            }
        } catch(IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
