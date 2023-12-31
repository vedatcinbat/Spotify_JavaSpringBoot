package com.task.spotifyclone.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int publishYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany(mappedBy = "likedSongs")
    private Set<User> usersWhoLiked = new HashSet<>();

    public Song() {}

    public Song(String title, int publishYear) {
        this.title = title;
        this.publishYear = publishYear;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public Set<User> getUsersWhoLiked() {
        return usersWhoLiked;
    }

    public void setUsersWhoLiked(Set<User> usersWhoLiked) {
        this.usersWhoLiked = usersWhoLiked;
    }

    // Helper methods for managing the bidirectional relationship
    public void addUserWhoLiked(User user) {
        this.usersWhoLiked.add(user);
        user.getLikedSongs().add(this);
    }

    public void removeUserWhoLiked(User user) {
        this.usersWhoLiked.remove(user);
        user.getLikedSongs().remove(this);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
