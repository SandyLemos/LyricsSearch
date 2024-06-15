package br.sandy.lyricsSearch.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class MostrarLetraController {

    @FXML
    private Label letra;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private StackPane stackPane;

    public void initialize() {
        // Ajustes no Label
        letra.setWrapText(true); // Permite a quebra de linha automática
        letra.setAlignment(Pos.CENTER); // Centraliza o texto horizontalmente
        letra.setTextAlignment(TextAlignment.CENTER);
        letra.setTextFill(Color.BLACK); // Cor do texto
        letra.setFont(new Font(18)); // Tamanho da fonte

        // Ajustes no ScrollPane
        scrollPane.setFitToWidth(true); // Ajusta a largura do conteúdo ao ScrollPane
        scrollPane.setContent(letra); // Define o Label como o conteúdo do ScrollPane

        // Configurações adicionais para o StackPane e Rectangle
        Rectangle background = new Rectangle(600.0, 335.0);
        background.setArcWidth(5.0);
        background.setArcHeight(5.0);
        background.setFill(Color.INDIANRED);
        background.setStroke(Color.BLACK);
        background.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);

        stackPane.getChildren().addAll(background, scrollPane); // Adiciona o Rectangle e o ScrollPane ao StackPane
        StackPane.setAlignment(scrollPane, Pos.CENTER); // Centraliza o ScrollPane dentro do StackPane
        stackPane.setMargin(scrollPane, new Insets(10)); // Define margem ao redor do ScrollPane dentro do StackPane
    }

    public void MostrarLetra(String mensagem) {
        letra.setText(mensagem);
        scrollPane.setVvalue(0); // Resetar a posição da ScrollPane para o início
    }
}