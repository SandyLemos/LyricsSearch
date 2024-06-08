package br.sandy.lyricsSearch.Model;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.List;

public class MusicOnlineSearch {
    private MusicApiService musicApiService;

    public MusicOnlineSearch() {
        musicApiService = new MusicApiService();
    }

    // Método para pesquisar músicas online
    public List<Music> searchMusicOnline(String query) throws IOException, ParseException {
        return musicApiService.searchMusic(query);
    }

    // Método para buscar letras de uma música
    public void catchLyric(Music music) throws IOException, ParseException {
        musicApiService.catchLyric(music);
    }
}