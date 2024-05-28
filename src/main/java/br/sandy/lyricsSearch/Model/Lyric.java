package br.sandy.lyricsSearch.Model;

public class Lyric {

    private String title;
    private String artist;
    private String description;

    public Lyric(String title, String artist, String description){
        this.title = title;
        this.artist = artist;
        this.description = description;
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

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
