package br.sandy.lyricsSearch.Model;

import br.sandy.lyricsSearch.Model.Dao.MusicDao;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.List;

public class MusicOnlineSearch {
    private MusicDao musicDao;

    public MusicOnlineSearch() {
        musicDao = new MusicDao();
    }

    public List<Music> searchMusicOnline(String query) throws IOException, ParseException {
        return musicDao.pesquisarMusica(query);
    }
}