package com.task.spotifyclone.services;

import com.task.spotifyclone.models.Artist;
import com.task.spotifyclone.models.DTOs.ArtistDTO;
import com.task.spotifyclone.models.DTOs.SongDTO;
import com.task.spotifyclone.models.Song;
import com.task.spotifyclone.models.User;
import com.task.spotifyclone.repositories.ArtistRepository;
import com.task.spotifyclone.repositories.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SongService {


    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public SongService(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    // Add a new song
    public Song addSong(Song song, Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));
        song.setArtist(artist);
        return songRepository.save(song);
    }

    // Get all songs
    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songs.stream().map(this::convertToSongDTO).collect(Collectors.toList());
    }
    private SongDTO convertToSongDTO(Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setPublishYear(song.getPublishYear());

        if (song.getArtist() != null) {
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setId(song.getArtist().getId());
            artistDTO.setName(song.getArtist().getName());
            dto.setArtist(artistDTO);
        }

        return dto;
    }

    // Get a single song by ID
    public Song getSongById(Long id) {
        Optional<Song> song = songRepository.findById(id);
        return song.orElseThrow(() -> new RuntimeException("Song not found"));
    }

    // Update a song
    public Song updateSong(Long id, Song songDetails) {
        Song song = getSongById(id);
        song.setTitle(songDetails.getTitle());
        song.setPublishYear(songDetails.getPublishYear());
        // set other fields
        return songRepository.save(song);
    }

    // Delete a song
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }


}