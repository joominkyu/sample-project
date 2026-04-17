package org.example.adminbackend.config;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.entity.Manager;
import org.example.adminbackend.entity.ManagerGrade;
import org.example.adminbackend.repository.manager.ManagerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerInitializer implements CommandLineRunner {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!managerRepository.existsByLoginId("admin")) {
            Manager manager = new Manager(
                    "admin",
                    passwordEncoder.encode("1234"),
                    "최초관리자",
                    ManagerGrade.SUPER_ADMIN
            );
            managerRepository.save(manager);
        }
    }
}