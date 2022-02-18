package com.example.demo.album.service;


import com.example.demo.album.model.Album;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteService {

    public final String TOKEN = "BQCdJFOe97F6n9E-zFAx3OVlMHxVg7gHaqMUfOmZQyRwSgvezsgFDBQJImhrRDq8jCSlIwoHrOPtbolr_MKue1dLg21db1mNwTqEwg9qLj61HG7T8oLozfvI0ugC8cpeSpXCgfpeUfGJaXi2ZdoegPE";



    public List<Album> getAlbumByChoice(String name,  String choice) {
        JsonNode res = WebClient.create("https://api.spotify.com/v1/search")
                .get()
                .uri(builder -> builder
                        .queryParam("q", choice+":" + name)
                        .queryParam("type", "album")
                        .build())
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                    httpHeaders.setBearerAuth(TOKEN);
                })
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        List<Album> albums = new ArrayList<>();
        for (JsonNode jsonNode : res.get("albums").get("items")) {
            albums.add(getAlbum(jsonNode.get("id").asText()));
        }
        return albums;
    }

    private Album getAlbum(String albumId) {
        JsonNode albumInfo = WebClient.create("https://api.spotify.com/v1/albums/" + albumId)
                .get()
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                    httpHeaders.setBearerAuth(TOKEN);
                })
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        String name = albumInfo.get("name").asText();
        String releaseDate = albumInfo.get("release_date").asText();
        String urlImage = albumInfo.get("images").get(0).get("url").asText();

        JsonNode tracksInfo = WebClient.create("https://api.spotify.com/v1/albums/" + albumId + "/tracks")
                .get()
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                    httpHeaders.setBearerAuth(TOKEN);
                })
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
        long duration = 0L;
        for (JsonNode jsonNode : tracksInfo.get("items")) {
            duration += jsonNode.get("duration_ms").asLong();
        }
        return new Album(albumId, name, releaseDate, urlImage, duration);
    }


}
