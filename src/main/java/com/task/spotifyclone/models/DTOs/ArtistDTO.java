package com.task.spotifyclone.models.DTOs;

import com.task.spotifyclone.models.Song;

import java.util.HashSet;
import java.util.Set;

public class ArtistDTO {
    private Long id;
    private String name;
    private Set<SongDTO> songs = new HashSet<>();

    public Set<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(Set<SongDTO> songs) {
        this.songs = songs;
    }

    // constructors, getters, and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
