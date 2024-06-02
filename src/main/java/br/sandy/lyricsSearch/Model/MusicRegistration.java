package br.sandy.lyricsSearch.Model;

import br.sandy.lyricsSearch.Dao.MusicDao;
import br.sandy.lyricsSearch.Dao.MusicDaoLocalImpl;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

// Classe interagindo com a Api externa e com a implementação local
// Class interacting with the external Api and the local implementation

public class MusicRegistration {
    private MusicDaoLocalImpl localMusicDao;
    private MusicDao OnlineMusicDao;

    public MusicRegistration(){
        this.localMusicDao = new MusicDaoLocalImpl();
        this.onlineMusicDao = new MusicDao();
    }

    public void addMusic(Music music){
        localMusicDao.addMusic(music);
    }

    public void removeMusic(String title){
        localMusicDao.removeMusic(title);
    }

    public Music searchMusic(String title, String artist, String gender, String album, String lyric){
        return localMusicDao.searchMusic(title, artist, gender, album, lyric);
    }

    public List<Music> listMusic(){
        return localMusicDao.listMusic();
    }

    public List<Music> searchMusicOnline(String nameMusic) throws IOException{
        try{
            return spotifyMusicDao.searchMusic(nameMusic);
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
