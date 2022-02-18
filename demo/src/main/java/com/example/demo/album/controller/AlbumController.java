package com.example.demo.album.controller;

import com.example.demo.album.model.Album;
import com.example.demo.album.service.AlbumService;
import com.example.demo.album.service.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    public AlbumService albumService;
    public RemoteService remoteService;

    @Autowired
    public AlbumController(AlbumService albumService, RemoteService remoteService) {
        this.albumService = albumService;
        this.remoteService = remoteService;
    }

    @GetMapping(value = "/{albumId}", produces = "application/json")
    public Album getAlbum(@PathVariable String albumId) {
        return albumService.getAlbum(albumId);
    }

    @GetMapping(value = "", produces = "application/json")
    public void getRemoteAlbum(@RequestParam String artistName, @RequestParam String choice) {
        remoteService.getAlbumByChoice(artistName, choice);
    }

    @PostMapping(value = "", produces = "application/json")
    public void postAlbum(@RequestParam Album album) {
        albumService.postAlbum(album);
    }

}
