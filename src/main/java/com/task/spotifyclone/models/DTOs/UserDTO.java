package com.task.spotifyclone.models.DTOs;

import com.task.spotifyclone.models.Song;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<SongDTO> likedSongs;


    public Set<SongDTO> getLikedSongs() {
        return likedSongs;
    }
    public void setLikedSongs(Set<SongDTO> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public UserDTO() {
        this.likedSongs = new HashSet<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
