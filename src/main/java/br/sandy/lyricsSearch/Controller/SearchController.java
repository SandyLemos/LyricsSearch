package br.sandy.lyricsSearch.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SearchController {

        @FXML
        private TextField artistField;

        @FXML
        private TextField titleField;

        @FXML
        private TextArea lyricsArea;

        private SearchLyrics searchLyrics;

        public SearchController() {
            this.searchLyrics = new SearchLyrics();
        }

        @FXML
        private void handleSearchAction() {
            String artist = artistField.getText();
            String title = titleField.getText();
            String lyrics = searchLyrics.fetchLyrics(artist, title);
            lyricsArea.setText(lyrics);
        }
    }
}
