package org.example.adminbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.repository.manager.*;
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

    @PatchMapping("/{managerId}/password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long managerId,
            @RequestBody ManagerPasswordChangeRequest request
    ) {
        managerService.changePassword(managerId, request.getNewPassword());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{managerId}")
    public void deleteManager(
            @PathVariable Long managerId,
            @RequestHeader("X-Admin-LoginId") String currentLoginId
    ) {
        managerService.deleteManager(managerId, currentLoginId);
    }

    @PatchMapping("/{managerId}/grade")
    public ManagerResponse updateGrade(
            @PathVariable Long managerId,
            @RequestHeader("X-Admin-LoginId") String currentLoginId,
            @RequestBody ManagerGradeUpdateRequest request
    ) {
        return managerService.updateGrade(managerId, currentLoginId, request.getGrade());
    }
}