package com.shermann.park_api.repositories;

import com.shermann.park_api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
