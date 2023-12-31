package com.task.spotifyclone.repositories;

import com.task.spotifyclone.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
