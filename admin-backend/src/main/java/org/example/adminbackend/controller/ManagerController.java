package org.example.adminbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.repository.manager.ManagerCreateRequest;
import org.example.adminbackend.repository.manager.ManagerLoginRequest;
import org.example.adminbackend.repository.manager.ManagerLoginResponse;
import org.example.adminbackend.repository.manager.ManagerResponse;
import org.example.adminbackend.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping
    public List<ManagerResponse> getManagers() {
        return managerService.getManagers();
    }

    @PostMapping
    public ManagerResponse createManager(@RequestBody ManagerCreateRequest request) {
        return managerService.createManager(request);
    }

    @PostMapping("/login")
    public ManagerLoginResponse login(@RequestBody ManagerLoginRequest request) {
        return managerService.login(request);
    }
}