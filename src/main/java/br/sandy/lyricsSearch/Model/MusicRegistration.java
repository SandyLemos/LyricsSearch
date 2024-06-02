package br.sandy.lyricsSearch.Model;

import br.sandy.lyricsSearch.Dao.MusicDao;
import br.sandy.lyricsSearch.Dao.MusicDaoLocalImpl;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class MusicRegistration {
    private List<Music> localMusics;
    private MusicDao musicDao;

    public MusicRegistration() {
        localMusics = new ArrayList<>();
        musicDao = new MusicDao();
    }

    public void addMusic(Music music) {
        localMusics.add(music);
    }

    public List<Music> listMusic() {
        return new ArrayList<>(localMusics);
    }

    public Music searchMusic(String title, String artist, String album, String lyric, String id) {
        for (Music music : localMusics) {
            boolean matches = (title == null || music.getTitle().equalsIgnoreCase(title)) &&
                    (artist == null || music.getArtist().equalsIgnoreCase(artist)) &&
                    (album == null || music.getAlbum().equalsIgnoreCase(album)) &&
                    (lyric == null || music.getLyric().equalsIgnoreCase(lyric)) &&
                    (id == null || music.getId().equalsIgnoreCase(id));
            if (matches) {
                return music;
            }
        }
        return null;
    }

    public void removeMusic(String title) {
        localMusics.removeIf(music -> music.getTitle().equalsIgnoreCase(title));
    }

    public List<Music> searchMusicOnline(String query) throws IOException, ParseException {
        return musicDao.pesquisarMusica(query);
    }
}