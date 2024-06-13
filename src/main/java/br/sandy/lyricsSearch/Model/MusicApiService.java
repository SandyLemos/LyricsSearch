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