package com.task.spotifyclone.repositories;

import com.task.spotifyclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
