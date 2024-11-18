package com.shermann.park_api.repositories;

import com.shermann.park_api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    @Query("select user.role from UserModel u where u.username like :username")
    UserModel.Role findRoleByUsername(String username);
}
