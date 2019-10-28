package com.company.springbootodata.repository;

import com.company.springbootodata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
