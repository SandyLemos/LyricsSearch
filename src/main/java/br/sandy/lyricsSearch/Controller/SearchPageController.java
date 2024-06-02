package br.sandy.lyricsSearch.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SearchPageController {

    @FXML
    private TextField filterField;

    @FXML
    private ListView<?> resultsList;

    @FXML
    private Button searchButton;
}
