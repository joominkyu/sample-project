package org.example.adminbackend.auth;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.repository.manager.ManagerLoginRequest;
import org.example.adminbackend.repository.manager.ManagerLoginResponse;
import org.example.adminbackend.service.ManagerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminLoginController {

    private final ManagerService managerService;

    @PostMapping("/login")
    public ManagerLoginResponse login(@RequestBody ManagerLoginRequest request) {
        return managerService.login(request);
    }
}