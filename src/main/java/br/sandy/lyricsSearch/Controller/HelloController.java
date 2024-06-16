package br.sandy.lyricsSearch.Controller;

import br.sandy.lyricsSearch.Model.Dao.UserDao;
import br.sandy.lyricsSearch.Model.Dao.UserDaoImpl;
import br.sandy.lyricsSearch.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button cadastro;

    @FXML
    private Button enviarLetra;

    @FXML
    private Button login;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    private UserDao userDao;

    public void initialize() {
        this.userDao = UserDaoImpl.getInstance(); // Aqui inicializamos o userDao usando o getInstance() do UserDaoImpl
    }

    @FXML
    void addMusicScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/AddMusica.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) enviarLetra.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeToCadastroScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/Cadastro.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) enviarLetra.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void changeToLoginScreen(ActionEvent event) {
        String username = usuario.getText().trim();
        String password = senha.getText().trim();

        // Verifica se o usuário existe no userDao
        User user = userDao.getUserByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            // Usuário encontrado e senha corresponde, proceder para a próxima tela
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/TelaInicial.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) usuario.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Caso contrário, exibir mensagem de erro ou tratar conforme necessário
            System.out.println("Usuário ou senha incorretos!");
        }
    }
}
