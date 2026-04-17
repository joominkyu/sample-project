package org.example.adminbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.entity.Manager;
import org.example.adminbackend.entity.ManagerGrade;
import org.example.adminbackend.repository.manager.ManagerCreateRequest;
import org.example.adminbackend.repository.manager.ManagerGradeUpdateRequest;
import org.example.adminbackend.repository.manager.ManagerLoginIdCheckResponse;
import org.example.adminbackend.repository.manager.ManagerLoginRequest;
import org.example.adminbackend.repository.manager.ManagerLoginResponse;
import org.example.adminbackend.repository.manager.ManagerPasswordChangeRequest;
import org.example.adminbackend.repository.manager.ManagerRepository;
import org.example.adminbackend.repository.manager.ManagerResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public ManagerLoginIdCheckResponse checkLoginId(String loginId) {
        boolean exists = managerRepository.existsByLoginId(loginId);

        if (exists) {
            return new ManagerLoginIdCheckResponse(false, "이미 사용 중인 관리자 아이디입니다.");
        }

        return new ManagerLoginIdCheckResponse(true, "사용 가능한 관리자 아이디입니다.");
    }

    public ManagerLoginResponse login(ManagerLoginRequest request) {
        Manager manager = managerRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), manager.getPassword())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        return new ManagerLoginResponse(
                "로그인 성공",
                manager.getLoginId(),
                manager.getName(),
                manager.getGrade()
        );
    }

    public List<ManagerResponse> getManagers() {
        return managerRepository.findAll()
                .stream()
                .map(ManagerResponse::from)
                .toList();
    }

    @Transactional
    public ManagerResponse createManager(ManagerCreateRequest request) {
        if (managerRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 사용 중인 관리자 아이디입니다.");
        }

        ManagerGrade grade = managerRepository.count() == 0
                ? ManagerGrade.SUPER_ADMIN
                : ManagerGrade.ADMIN;

        Manager manager = new Manager(
                request.getLoginId(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                grade
        );

        Manager saved = managerRepository.save(manager);
        return ManagerResponse.from(saved);
    }

    @Transactional
    public ManagerResponse updateGrade(Long managerId, String currentLoginId, ManagerGradeUpdateRequest request) {
        Manager currentManager = managerRepository.findByLoginId(currentLoginId)
                .orElseThrow(() -> new IllegalArgumentException("현재 로그인한 관리자를 찾을 수 없습니다."));

        if (currentManager.getGrade() != ManagerGrade.SUPER_ADMIN) {
            throw new IllegalArgumentException("SUPER_ADMIN만 관리자 등급을 변경할 수 있습니다.");
        }

        Manager targetManager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("관리자를 찾을 수 없습니다."));

        if (targetManager.getLoginId().equals(currentLoginId)) {
            throw new IllegalArgumentException("본인 계정의 등급은 변경할 수 없습니다.");
        }

        if (targetManager.getGrade() == ManagerGrade.SUPER_ADMIN) {
            throw new IllegalArgumentException("최초 관리자 계정의 등급은 변경할 수 없습니다.");
        }

        if (request.getGrade() == ManagerGrade.SUPER_ADMIN) {
            throw new IllegalArgumentException("SUPER_ADMIN으로 변경할 수 없습니다.");
        }

        targetManager.changeGrade(request.getGrade());

        return ManagerResponse.from(targetManager);
    }

    @Transactional
    public void changePassword(Long managerId, ManagerPasswordChangeRequest request) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("관리자를 찾을 수 없습니다."));

        String newPassword = request.getNewPassword();

        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("새 비밀번호를 입력해주세요.");
        }

        if (newPassword.length() < 4) {
            throw new IllegalArgumentException("비밀번호는 4자 이상 입력해주세요.");
        }

        manager.changePassword(passwordEncoder.encode(newPassword));
    }

    @Transactional
    public void deleteManager(Long managerId, String currentLoginId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("관리자를 찾을 수 없습니다."));

        if (manager.getLoginId().equals(currentLoginId)) {
            throw new IllegalArgumentException("본인 계정은 삭제할 수 없습니다.");
        }

        if (manager.getGrade() == ManagerGrade.SUPER_ADMIN) {
            throw new IllegalArgumentException("최초 관리자 계정은 삭제할 수 없습니다.");
        }

        managerRepository.delete(manager);
    }
}