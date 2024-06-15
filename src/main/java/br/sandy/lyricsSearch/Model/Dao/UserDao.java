package br.sandy.lyricsSearch.Model.Dao;

import br.sandy.lyricsSearch.Model.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> getAllUsers();

}