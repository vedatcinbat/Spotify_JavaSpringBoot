package com.task.spotifyclone.controllers;

import com.task.spotifyclone.models.Artist;
import com.task.spotifyclone.models.DTOs.ArtistDTO;
import com.task.spotifyclone.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    // Add a new artist
    @PostMapping
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        Artist newArtist = artistService.addArtist(artist);
        return ResponseEntity.ok(newArtist);
    }

    // Get all artists
    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        List<ArtistDTO> artistDTOs = artistService.getAllArtists();
        return ResponseEntity.ok(artistDTOs);
    }


    // Get a single artist by ID
    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    // Update an artist
    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist artistDetails) {
        Artist updatedArtist = artistService.updateArtist(id, artistDetails);
        return ResponseEntity.ok(updatedArtist);
    }

    // Delete an artist
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.ok().build();
    }
}
