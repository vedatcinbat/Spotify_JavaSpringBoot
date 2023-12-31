package com.task.spotifyclone.services;

import com.task.spotifyclone.models.DTOs.SongDTO;
import com.task.spotifyclone.models.DTOs.UserDTO;
import com.task.spotifyclone.models.Song;
import com.task.spotifyclone.models.User;
import com.task.spotifyclone.repositories.SongRepository;
import com.task.spotifyclone.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jdk.jshell.Snippet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public UserService(UserRepository userRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }
    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return convertToUserDTO(user);
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        Set<SongDTO> songDTOs = user.getLikedSongs().stream()
                .map(this::convertToSongDTO)
                .collect(Collectors.toSet());
        dto.setLikedSongs(songDTOs);

        return dto;
    }

    private SongDTO convertToSongDTO(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.getId());
        songDTO.setTitle(song.getTitle());
        songDTO.setPublishYear(song.getPublishYear());

        return songDTO;
    }


    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
             return userRepository.save(user);
        }else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void likeSong(Long userId, Long songId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));
        user.likeSong(song);
        userRepository.save(user);
    }

    public void unlikeSong(Long userId, Long songId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));
        user.unlikeSong(song);
        userRepository.save(user);
    }

}
