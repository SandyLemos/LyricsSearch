package br.sandy.lyricsSearch.Dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

public class MusicDao {

    public void getLyrics() {
        Client client = ClientBuilder.newClient();

        try {
            Response response = client.target("https://api.lyrics.ovh/v1/Coldplay/Adventure of a Lifetime")
                    .request(MediaType.TEXT_PLAIN_TYPE)
                    .get();

            System.out.println("status: " + response.getStatus());
            System.out.println("headers: " + response.getHeaders());
            System.out.println("body:" + response.readEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}