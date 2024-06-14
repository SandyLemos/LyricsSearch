package br.sandy.lyricsSearch.Model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.JSONObject;

public class SearchLyrics {

    // Método usado para buscar a letra através do Artista e do Título digitado pelo Usuário
    public String fetchLyrics(String artist, String title) {
        try {
            String encodedArtist = URLEncoder.encode(artist, StandardCharsets.UTF_8.toString());
            String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString());

            URI uri = new URI("https", "api.lyrics.ovh", "/v1/" + encodedArtist + "/" + encodedTitle, null);
            URL url = uri.toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder inline = new StringBuilder();
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();

                JSONObject data = new JSONObject(inline.toString());
                return data.optString("lyrics", "Letra não encontrada.");
            } else {
                return "Não foi possível encontrar a letra. Verifique se o nome do artista e o título da música estão corretos.";
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "Ocorreu um erro ao buscar a letra da música.";
        }
    }
}