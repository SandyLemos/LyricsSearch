package com.example.lyrics;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CadastroController {

    @FXML
    private TextField email;

    @FXML
    private TextField nome;

    @FXML
    private TextField senha;

    @FXML
    private TextField telefone;

    UserDaoImpl userDao = UserDaoImpl.getInstance();

    public void addUser(ActionEvent event) {
        if(nome.getText().equals(null)==false && email.getText().equals(null)==false && senha.getText().equals(null)==false && telefone.getText().equals(null)==false) {
            User usuario = new User(nome.getText(),senha.getText(),email.getText(), telefone.getText());
            userDao.users.add(usuario);
        }

        for(int i=0; i<userDao.users.size(); i++) {
            User usuarioTeste = userDao.users.get(i);
            System.out.println("Nome: "+ usuarioTeste.getUserName() + "E-mail: "+ usuarioTeste.getEmail());
        }

    }
}
