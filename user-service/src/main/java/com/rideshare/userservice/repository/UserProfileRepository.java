package com.rideshare.userservice.repository;
import com.rideshare.userservice.entity.UserProfile; import org.springframework.data.jpa.repository.JpaRepository;
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {}
