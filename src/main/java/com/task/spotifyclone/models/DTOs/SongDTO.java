package com.task.spotifyclone.models.DTOs;

public class SongDTO {
    private Long id;
    private String title;
    private int publishYear;
    private ArtistDTO artist;

    // constructors, getters, and setters
    // Constructor
    public SongDTO() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

}
