package br.sandy.lyricsSearch.Model;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.List;

public class MusicService {
    private MusicApiService musicApiService;

    public MusicService() {
        musicApiService = new MusicApiService();
    }

    // Método para buscar letras de uma música
    public void catchLyric(Music music) throws IOException, ParseException {
        musicApiService.catchLyric(music);
    }
}