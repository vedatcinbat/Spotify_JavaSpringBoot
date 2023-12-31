package com.task.spotifyclone.controllers;

import com.task.spotifyclone.models.Artist;
import com.task.spotifyclone.models.DTOs.SongDTO;
import com.task.spotifyclone.models.Song;
import com.task.spotifyclone.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    // Add a new song
    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song, @RequestParam Long artistId) {
        Song newSong = songService.addSong(song, artistId);
        return ResponseEntity.ok(newSong);
    }

    // Get all songs
    @GetMapping
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<SongDTO> songDTOs = songService.getAllSongs();
        return ResponseEntity.ok(songDTOs);
    }

    // Get a single song by ID
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);
        return ResponseEntity.ok(song);
    }

    // Update a song
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song songDetails) {
        Song updatedSong = songService.updateSong(id, songDetails);
        return ResponseEntity.ok(updatedSong);
    }

    // Delete a song
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok().build();
    }
}
