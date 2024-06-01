package br.sandy.lyricsSearch.Dao;

import br.sandy.lyricsSearch.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private List<User> users;

    public UserDaoImpl(){
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user){
        users.add(user);
    }

    @Override
    public User getUserById(String id){
        optional<User> user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return user.orElse(null);
    }

    @Override
    public List<User> getAllUsers(){
        return new ArrayList<>(users);
    }

    @Override
    public void updateUser(User user){
        for(int i = 0; i <users.size();i++){
            if(users.get(i).getId().equals(user.getId())){
                users.set(i, user);
                return;
            }
        }
    }

    @Override
    public void deleteUser(String id){
        users.removeIf(user -> user.getId().equals(id));
    }
}
