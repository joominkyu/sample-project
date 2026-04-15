package org.example.adminbackend.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getLoginId()) && "1234".equals(request.getPassword())) {
            return ResponseEntity.ok("로그인 성공");
        }

        return ResponseEntity.status(401).body("아이디 또는 비밀번호가 올바르지 않습니다.");
    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String loginId;
        private String password;
    }
}
