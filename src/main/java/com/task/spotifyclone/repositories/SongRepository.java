package com.task.spotifyclone.repositories;

import com.task.spotifyclone.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
