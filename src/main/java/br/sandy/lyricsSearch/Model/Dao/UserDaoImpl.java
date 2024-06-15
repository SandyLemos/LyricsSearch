package br.sandy.lyricsSearch.Model.Dao;

import br.sandy.lyricsSearch.Model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements UserDao {
    private static UserDaoImpl instance;
    public List<User> users;

    private UserDaoImpl(){
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user){
        users.add(user);
    }


    @Override
    public List<User> getAllUsers(){
        return new ArrayList<>(users);
    }



    public static synchronized UserDaoImpl getInstance() {
        if (instance==null) {
            instance = new UserDaoImpl();
        }

        return instance;

    }
}
