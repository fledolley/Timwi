package com.example.demo.album.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Album {
    @Id
    public String idAlbum;
    public String name;
    public String releaseDate;
    public String urlImage;
    @JsonIgnore
    public Long duration = 0L;

    public Album(String idAlbum, String name, String releaseDate, String urlImage, Long duration) {
        this.idAlbum = idAlbum;
        this.name = name;
        this.releaseDate = releaseDate;
        this.urlImage = urlImage;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return idAlbum.equals(album.idAlbum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlbum, name, urlImage, releaseDate, duration);
    }
}
