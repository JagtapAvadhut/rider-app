package com.rideshare.userservice.dto;
import lombok.*;
@Data @AllArgsConstructor public class UserResponse { private Long id; private String email; private String fullName; private String phone; }
