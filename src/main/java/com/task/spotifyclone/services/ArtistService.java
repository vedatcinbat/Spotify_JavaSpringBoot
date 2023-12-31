package com.task.spotifyclone.services;
import com.task.spotifyclone.models.Artist;
import com.task.spotifyclone.models.DTOs.ArtistDTO;
import com.task.spotifyclone.models.DTOs.SongDTO;
import com.task.spotifyclone.models.Song;
import com.task.spotifyclone.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // Add a new artist
    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    // Get all artists
    public List<ArtistDTO> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artists.stream().map(this::convertToArtistDTO).collect(Collectors.toList());
    }

    private ArtistDTO convertToArtistDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setName(artist.getName());

        Set<SongDTO> songDTOs = artist.getSongs().stream()
                .map(this::convertToSongDTO)
                .collect(Collectors.toSet());
        dto.setSongs(songDTOs);

        return dto;
    }
    private SongDTO convertToSongDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setTitle(song.getTitle());
        songDTO.setPublishYear(song.getPublishYear());

        return songDTO;
    }

    // Get a single artist by ID
    public Artist getArtistById(Long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        return artist.orElseThrow(() -> new RuntimeException("Artist not found"));
    }

    // Update an artist
    public Artist updateArtist(Long id, Artist artistDetails) {
        Artist artist = getArtistById(id);
        artist.setName(artistDetails.getName());
        // set other fields if necessary
        return artistRepository.save(artist);
    }

    // Delete an artist
    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }
}