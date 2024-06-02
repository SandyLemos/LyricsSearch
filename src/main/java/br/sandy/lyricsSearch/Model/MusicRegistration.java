package br.sandy.lyricsSearch.Model;

import br.sandy.lyricsSearch.Dao.MusicDao;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.List;

public class MusicRegistration {
    private MusicDao musicDao;

    public MusicRegistration() {
        musicDao = new MusicDao();
    }

    public List<Music> searchMusicOnline(String query) throws IOException, ParseException {
        return musicDao.pesquisarMusica(query);
    }
}