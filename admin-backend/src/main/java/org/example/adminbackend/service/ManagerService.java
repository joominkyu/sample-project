package org.example.adminbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.adminbackend.entity.Manager;
import org.example.adminbackend.repository.manager.ManagerCreateRequest;
import org.example.adminbackend.repository.manager.ManagerLoginIdCheckResponse;
import org.example.adminbackend.repository.manager.ManagerLoginRequest;
import org.example.adminbackend.repository.manager.ManagerLoginResponse;
import org.example.adminbackend.repository.manager.ManagerRepository;
import org.example.adminbackend.repository.manager.ManagerResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    public ManagerResponse createManager(ManagerCreateRequest request) {
        if (managerRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 사용 중인 관리자 아이디입니다.");
        }

        Manager manager = new Manager(
                request.getLoginId(),
                passwordEncoder.encode(request.getPassword()),
                request.getName()
        );

        Manager saved = managerRepository.save(manager);
        return ManagerResponse.from(saved);
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
                manager.getName()
        );
    }

    public List<ManagerResponse> getManagers() {
        return managerRepository.findAll()
                .stream()
                .map(ManagerResponse::from)
                .toList();
    }
}