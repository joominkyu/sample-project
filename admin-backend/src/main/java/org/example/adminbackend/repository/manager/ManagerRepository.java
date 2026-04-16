package org.example.adminbackend.repository.manager;

import org.example.adminbackend.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}