package br.sandy.lyricsSearch.Controller;

import br.sandy.lyricsSearch.Model.User;
import br.sandy.lyricsSearch.Model.Dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroController {

    @FXML
    private Button back;

    @FXML
    private TextField email;

    @FXML
    private TextField nome;

    @FXML
    private TextField senha;

    @FXML
    private TextField telefone;

    @FXML
    void backToHelloScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/fxml/hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    UserDaoImpl userDao = UserDaoImpl.getInstance();

    @FXML
    public void addUser(ActionEvent event) {
        if (!nome.getText().isEmpty() && !email.getText().isEmpty() && !senha.getText().isEmpty() && !telefone.getText().isEmpty()) {
            User usuario = new User(nome.getText(), senha.getText(), email.getText(), telefone.getText());
            System.out.println("Adicionando pela UI: " + usuario.getUserName());
            userDao.addUser(usuario);
        }

        for (int i = 0; i < userDao.getAllUsers().size(); i++) {
            User usuarioTeste = userDao.getAllUsers().get(i);
            System.out.println("Nome: " + usuarioTeste.getUserName() + " E-mail: " + usuarioTeste.getEmail());
        }
    }
}
