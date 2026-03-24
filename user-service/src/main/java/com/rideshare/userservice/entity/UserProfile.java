package com.rideshare.userservice.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="users") @Getter @Setter
public class UserProfile { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(unique=true,nullable=false) private String email; private String fullName; private String phone; }
