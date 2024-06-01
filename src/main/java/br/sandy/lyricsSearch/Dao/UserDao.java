package br.sandy.lyricsSearch.Dao;

import br.sandy.lyricsSearch.Model.User;
import java.util.List;

public interface UserDao {
    void addUser(User user);
    User getUserById(String id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(String id);
}
