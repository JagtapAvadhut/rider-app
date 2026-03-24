package com.rideshare.userservice.service;
import com.rideshare.userservice.dto.UserResponse; import com.rideshare.userservice.repository.UserProfileRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class UserService { private final UserProfileRepository repository; public UserService(UserProfileRepository repository){this.repository=repository;} public List<UserResponse> all(){ return repository.findAll().stream().map(u->new UserResponse(u.getId(),u.getEmail(),u.getFullName(),u.getPhone())).toList(); }}
