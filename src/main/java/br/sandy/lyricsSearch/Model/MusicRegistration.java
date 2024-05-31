package br.sandy.lyricsSearch.Model;

import java.util.ArrayList;
import java.util.List;

public class MusicRegistration {
    private List<Music> musics;

    public MusicRegistration() {
        this.musics = new ArrayList<>();
    }

    // Adiciona
    public void addMusic(Music music) {
        musics.add(music);
    }

    // Remove por título
    public void removeMusic(String title){
        musics.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
    }

    // Busca por título, artista, gênero, álbum e letra
    public Music searchMusic(String title, String artist, String gender, String album, String lyric){
        for(Music music : musics){
            if(music.getTitle().equalsIgnoreCase(title)){
                return music;
            }
        }
        return null;
    }

    public List<Music> listMusic(){
        return new ArrayList<>(musics);
    }
}
