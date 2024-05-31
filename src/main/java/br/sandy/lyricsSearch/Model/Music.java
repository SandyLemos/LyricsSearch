package br.sandy.lyricsSearch.Model;

public class Music {

    private String title;
    private String artist;
    private String gender;
    private String album;
    private String lyric;

    public Music(String title, String artist, String gender, String album, String lyric){
        this.title = title;
        this.artist = artist;
        this.gender = gender;
        this.album = album;
        this.lyric = lyric;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public String getGender(){
        return gender;
    }

    public String getAlbum(){
        return album;
    }

    public String getLyric(){
        return lyric;
    }

    @Override
    public String toString(){
        return "Music [Title =" + title + ", Artist =" + artist + ", Album =" + album + ", Gender =" + gender + "]";
    }
}
