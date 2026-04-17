package org.example.adminbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.repository.manager.ManagerCreateRequest;
import org.example.adminbackend.repository.manager.ManagerGradeUpdateRequest;
import org.example.adminbackend.repository.manager.ManagerLoginIdCheckResponse;
import org.example.adminbackend.repository.manager.ManagerPasswordChangeRequest;
import org.example.adminbackend.repository.manager.ManagerResponse;
import org.example.adminbackend.service.ManagerService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/check-login-id")
    public ManagerLoginIdCheckResponse checkLoginId(@RequestParam String loginId) {
        return managerService.checkLoginId(loginId);
    }

    @PostMapping
    public ManagerResponse createManager(@RequestBody ManagerCreateRequest request) {
        return managerService.createManager(request);
    }

    @PatchMapping("/{managerId}/grade")
    public ManagerResponse updateGrade(
            @PathVariable Long managerId,
            @RequestHeader("X-Admin-LoginId") String currentLoginId,
            @RequestBody ManagerGradeUpdateRequest request
    ) {
        return managerService.updateGrade(managerId, currentLoginId, request);
    }

    @PatchMapping("/{managerId}/password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long managerId,
            @RequestBody ManagerPasswordChangeRequest request
    ) {
        managerService.changePassword(managerId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void> deleteManager(
            @PathVariable Long managerId,
            @RequestHeader("X-Admin-LoginId") String currentLoginId
    ) {
        managerService.deleteManager(managerId, currentLoginId);
        return ResponseEntity.noContent().build();
    }
}