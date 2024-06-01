package br.sandy.lyricsSearch.Dao;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Closeable;
import java.io.IOException;
import org.apache.hc.core5.http.ParseException;
import java.util.ArrayList;
import java.util.List;

// Esse Dao é responsável pela comunicação com a API do Spotify
// This Dao is responsible for communicating with the Spotify API

public class MusicDao {
    private final String CLIENT_ID = "37015464548e453a9805973122b41f39";
    private final String SECRET_CLIENTE = "3ba9da5bcd114a10ba12b4f3af4f1e76";
    private final String URL_BASE = "https://api.spotify.com/v1";
    private final String TOKEN_URL = "https://accounts.spotify.com/api/token";
    private String acessToken;

    private void catchToken() throws IOException, ParseException {
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
                this.acessToken = jsonObject.getString("access_token");


            }
        }
    }

    public List<Music> searchMusic(String nomeMusica) throws IOException, ParseException {
        catchToken();
        List<Music> listMusics = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            String urlPesquisa = URL_BASE + "/search?q=" + nomeMusica.replace(" " +
                    "", "+") + "&type=track";
            HttpGet get = new HttpGet(urlPesquisa);
            get.setHeader("Authorization", "Bearer " + acessToken);

            try (CloseableHttpResponse response = httpClient.execute(get)){

                String corpoResponse = EntityUtils.toString(response.getEntity());
                JSONObject resultadosPesquisa = new JSONObject(corpoResponse);
                JSONArray tracks = resultadosPesquisa.getJSONObject("tracks").getJSONArray("items");

                for (int i = 0; i < tracks.length(); i++){
                    JSONObject music = tracks.getJSONObject(i);
                    String id = music.getString("id");
                    String name = music.getString("name");
                    String artist = music.getJSONArray("artists").getJSONObject(0).getString("name");
                    listMusics.add(new Music(name, artist, id));
                }
            }
        }
        return listMusics;
    }
}
