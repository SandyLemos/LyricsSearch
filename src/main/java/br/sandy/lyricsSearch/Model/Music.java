package br.sandy.lyricsSearch.Model;

public class Music {

    private String title;
    private String artist;
    private String album;
    private String lyric;

    public Music(String title, String artist, String album, String lyric){
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.lyric = lyric;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getArtist(){
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getAlbum(){
        return album;
    }

    public void setAlbum(String album){
        this.album = album;
    }

    public String getLyric(){
        return lyric;
    }

    public void setLyric(String lyric){
        this.lyric = lyric;
    }

    @Override
    public String toString(){
        return "Music [Title =" + title + ", Artist =" + artist + ", Album =" + album + ",Lyric =" + lyric + "]";
    }
}
