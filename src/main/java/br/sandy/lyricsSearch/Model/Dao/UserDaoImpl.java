package br.sandy.lyricsSearch.Model.Dao;

import br.sandy.lyricsSearch.Model.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String FILE_PATH = "src/main/java/br/sandy/lyricsSearch/Model/Dao/users_data.txt";
    private static UserDaoImpl instance;
    private List<User> users;

    private UserDaoImpl() {
        this.users = loadUsersFromFile();
    }

    @Override
    public void addUser(User user) {
        System.out.println("Adding user: " + user.getUserName());
        users.add(user);
        saveUsersToFile();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    public static synchronized UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    private List<User> loadUsersFromFile() {
        List<User> loadedUsers = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Cria diretórios pai se não existirem
                file.createNewFile(); // Cria o arquivo se não existir
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3]);
                    loadedUsers.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadedUsers;
    }

    private void saveUsersToFile() {
        File file = new File(FILE_PATH);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (User user : users) {
                System.out.println("Saving user: " + user.getUserName());
                bw.write(user.getUserName() + ";" +
                        user.getPassword() + ";" +
                        user.getEmail() + ";" +
                        user.getTelefone());
                bw.newLine();
            }
            bw.flush();
            System.out.println("Usuário cadastrado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}