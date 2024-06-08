package br.sandy.lyricsSearch.Model;

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

public class MusicApiService {
    private static final String CLIENT_ID = "37015464548e453a9805973122b41f39";
    private static final String SECRET_CLIENT = "3ba9da5bcd114a10ba12b4f3af4f1e76";
    private static final String URL_BASE = "https://api.spotify.com/v1";
    private static final String TOKEN_URL = "https://accounts.spotify.com/api/token";

    private String accessToken;

    // Método para obter o token de acesso da Api do Spotify
    private String fetchAccessToken() throws IOException, ParseException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(TOKEN_URL);

            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("grant_type", "client_credentials"));
            params.add(new BasicNameValuePair("client_id", CLIENT_ID));
            params.add(new BasicNameValuePair("client_secret", SECRET_CLIENT));

            post.setEntity(new UrlEncodedFormEntity(params));

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = new JSONObject(responseBody);
                return jsonObject.getString("access_token");
            }
        }
    }

    // Método para pesquisar músicas da Api do Spotify
    public List<Music> searchMusic(String musicName) throws IOException, ParseException {
        accessToken = fetchAccessToken();
        List<Music> musicList = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String searchUrl = URL_BASE + "/search?q=" + URLEncoder.encode(musicName, StandardCharsets.UTF_8) + "&type=track";
            HttpGet get = new HttpGet(searchUrl);
            get.setHeader("Authorization", "Bearer " + accessToken);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JSONObject searchResults = new JSONObject(responseBody);
                JSONArray tracks = searchResults.getJSONObject("tracks").getJSONArray("items");

                for (int i = 0; i < tracks.length(); i++) {
                    JSONObject track = tracks.getJSONObject(i);
                    String id = track.getString("id");
                    String title = track.getString("name");
                    String artist = track.getJSONArray("artists").getJSONObject(0).getString("name");
                    String album = track.getJSONObject("album").getString("name");
                    String lyric = "Lyrics not available";

                    musicList.add(new Music(id, title, artist, album, lyric));
                }
            }
        }
        return musicList;
    }

    // Método para buscar a letra de uma música da Api Ovh
    public void catchLyric(Music music) throws IOException, ParseException {
        final String LYRICS_API_URL = "https://api.lyrics.ovh/v1/";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = LYRICS_API_URL + URLEncoder.encode(music.getArtist(), StandardCharsets.UTF_8) + "/"
                    + URLEncoder.encode(music.getTitle(), StandardCharsets.UTF_8);

            HttpGet get = new HttpGet(url);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                int statusCode = response.getCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                if (statusCode == 200) {
                    JSONObject jsonResponse = new JSONObject(responseBody);
                    String lyric = jsonResponse.getString("lyrics");
                    music.setLyric(lyric);
                } else {
                    music.setLyric("Lyrics not found");
                }
            }
        }
    }
}