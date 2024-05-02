package com.codingAmigos.fullstackbackend.repository;

import com.codingAmigos.fullstackbackend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
