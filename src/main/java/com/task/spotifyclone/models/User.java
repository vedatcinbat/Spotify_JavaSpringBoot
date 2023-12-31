package com.task.spotifyclone.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
            name = "user_liked_songs",
            joinColumns = @JoinColumn(name = "user_id"), // This column should exist in your join table
            inverseJoinColumns = @JoinColumn(name = "song_id") // This column should also exist
    )
    private Set<Song> likedSongs = new HashSet<>();

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Song> getLikedSongs() {
        return likedSongs;
    }

    public void likeSong(Song song) {
        likedSongs.add(song);
        song.getUsersWhoLiked().add(this);
    }

    // Method to unlike a song
    public void unlikeSong(Song song) {
        likedSongs.remove(song);
        song.getUsersWhoLiked().remove(this);
    }
}
