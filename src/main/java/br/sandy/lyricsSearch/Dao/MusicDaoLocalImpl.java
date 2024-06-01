package br.sandy.lyricsSearch.Dao;

import br.sandy.lyricsSearch.Model.Music;
import java.util.ArrayList;
import java.util.List;

// Esse Dao é responsável pela manipulação local do CRUD dos dados de música
// This Dao is responsible for local CRUD manipulation of music data

public class MusicDaoLocalImpl {
    private List<Music> musics;

    public MusicDaoLocalImpl(){
        this.musics = new ArrayList<>();
    }

    public void addMusic(Music music){
        musics.add(music);
    }

    public void removeMusic(String title){
        musics.removeIf(m -> m.getTitle().equalsIgnoreCase(title));
    }

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
