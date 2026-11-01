package com.demo.Repository;

import com.demo.Model.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @EntityGraph(attributePaths = {"roles"})
    Optional<UserEntity> findByUserName(String userName);
}
