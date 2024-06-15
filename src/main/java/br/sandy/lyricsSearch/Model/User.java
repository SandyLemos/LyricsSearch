package br.sandy.lyricsSearch.Model;

public class User {


    private String userName;
    private String password;
    private String email;
    private String telefone;

    public User(String userName, String password, String email, String telefone){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.telefone = telefone;
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

}