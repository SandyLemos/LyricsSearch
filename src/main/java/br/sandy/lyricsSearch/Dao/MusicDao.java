package br.sandy.lyricsSearch.Dao;

import br.sandy.lyricsSearch.Model.Music;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import org.apache.hc.core5.http.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import org.json.JSONException;

public class MusicDao {
    private final String CLIENT_ID = "37015464548e453a9805973122b41f39";
    private final String SECRET_CLIENTE = "3ba9da5bcd114a10ba12b4f3af4f1e76";
    private final String URL_BASE = "https://api.spotify.com/v1";
    private final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private String tokenAcesso;

    private void pegarToken() throws IOException, ParseException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(TOKEN_URL);

            List<BasicNameValuePair> parametros = new ArrayList<>();
            parametros.add(new BasicNameValuePair("grant_type", "client_credentials"));
            parametros.add(new BasicNameValuePair("client_id", CLIENT_ID));
            parametros.add(new BasicNameValuePair("client_secret", SECRET_CLIENTE));

            post.setEntity(new UrlEncodedFormEntity(parametros));

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = new JSONObject(responseBody);
                this.tokenAcesso = jsonObject.getString("access_token");
            }
        }
    }

    public List<Music> pesquisarMusica(String nomeMusica) throws IOException, ParseException {
        pegarToken();
        List<Music> listaMusicas = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String urlPesquisa = URL_BASE + "/search?q=" + nomeMusica.replace(" ", "+") + "&type=track";
            HttpGet get = new HttpGet(urlPesquisa);
            get.setHeader("Authorization", "Bearer " + tokenAcesso);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String corpoResponse = EntityUtils.toString(response.getEntity());
                JSONObject resultadosPesquisa = new JSONObject(corpoResponse);
                JSONArray tracks = resultadosPesquisa.getJSONObject("tracks").getJSONArray("items");

                for (int i = 0; i < tracks.length(); i++) {
                    JSONObject music = tracks.getJSONObject(i);
                    String id = music.getString("id");
                    String title = music.getString("name");
                    String artist = music.getJSONArray("artists").getJSONObject(0).getString("name");
                    String album = music.getJSONObject("album").getString("name");
                    String lyric = "Lyrics not available"; // Placeholder, já que a API do Spotify não fornece letras

                    listaMusicas.add(new Music(id, title, artist, album, lyric));
                }
            }
        }
        return listaMusicas;
    }

    public void catchLyric(Music music) throws IOException, ParseException {
        final String URL_LYRICS_OVH = "https://api.lyrics.ovh/v1/";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String urlSearch = URL_LYRICS_OVH + URLEncoder.encode(music.getArtist(), StandardCharsets.UTF_8) + "/"
                    + URLEncoder.encode(music.getTitle(), StandardCharsets.UTF_8);

            HttpGet get = new HttpGet(urlSearch);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                int statusCode = response.getCode(); // Compatível com HttpClient 5
                String bodyResponse = EntityUtils.toString(response.getEntity());

                System.out.println("HTTP Status: " + statusCode);
                System.out.println("Response Body: " + bodyResponse);

                if (statusCode == 200) {
                    try {
                        JSONObject searchResults = new JSONObject(bodyResponse);
                        String lyric = searchResults.getString("lyric");
                        music.setLyric(lyric);
                    } catch (JSONException e) {
                        System.err.println("JSON Parsing error: " + e.getMessage());
                        e.printStackTrace();
                        music.setLyric("ERRO!!!");
                    }
                } else {
                    System.err.println("Non-OK response: " + statusCode);
                    music.setLyric("ERRO!!!");
                }
            }
        } catch (IOException e) {
            System.err.println("HTTP request error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (ParseException e) {
            System.err.println("Parsing error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    }