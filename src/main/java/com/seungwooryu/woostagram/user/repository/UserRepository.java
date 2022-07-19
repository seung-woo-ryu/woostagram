package com.seungwooryu.woostagram.user.repository;

import com.seungwooryu.woostagram.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
