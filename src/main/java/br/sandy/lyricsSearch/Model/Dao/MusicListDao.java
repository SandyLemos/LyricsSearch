package br.sandy.lyricsSearch.Model.Dao;

import br.sandy.lyricsSearch.Model.Music;
import java.util.List;

public interface MusicListDao {
    public List<Music>getAllMusics();
    public void addMusics(Music musica);
    public void updateMusic(int index,Music musica);
    public Music getMusic(int index);
}
