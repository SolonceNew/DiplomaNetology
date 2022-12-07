package com.example.cloudservice.repository;

import com.example.cloudservice.model.ERole;
import com.example.cloudservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
       Role findByName(ERole name);
}
