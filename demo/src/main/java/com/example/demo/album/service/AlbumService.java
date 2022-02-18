package com.example.demo.album.service;

import com.example.demo.album.model.Album;
import com.example.demo.album.repository.AlbumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    AlbumDao albumDao;

    @Autowired
    public AlbumService(AlbumDao albumDao){
        this.albumDao = albumDao;
    }

    public Album getAlbum(String albumId){
        return albumDao.getById(albumId);
    }

    public void postAlbum(Album album){
        albumDao.save(album);
    }

}
