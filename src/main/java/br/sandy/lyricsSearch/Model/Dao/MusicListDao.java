package com.example.lyrics;

import java.util.List;

public interface MusicListDao {
    public List<Music>getAllMusics();
    public void addMusics(Music musica);
    public void updateMusic(int index,Music musica);
    public Music getMusic(int index);
}
