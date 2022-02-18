package com.example.demo.album.repository;



import com.example.demo.album.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AlbumDaoJpa extends JpaRepository<Album, String> {
}
