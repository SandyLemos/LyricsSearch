package br.sandy.lyricsSearch.Model;

public class User {

    private String id;
    private String userName;
    private String password;
    private String email;
    private String favoritesSongs;

    public user(String id, String userName, String password, String email, String favoritesSongs){
        this.id= id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.favoritesSongs = favoritesSongs;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getFavoritesSongs(){
        return favoritesSongs;
    }

    public void setFavoritesSongs(String favoritesSongs){
        this.favoritesSongs = favoritesSongs;
    }
}
