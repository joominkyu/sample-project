package org.example.adminbackend.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if ("admin".equals(request.getLoginId()) && "1234".equals(request.getPassword())) {
            return ResponseEntity.ok(new LoginResponse("로그인 성공", request.getLoginId()));
        }

        return ResponseEntity.status(401).build();
    }

    @Getter
    @Setter
    public static class LoginRequest {
        private String loginId;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class LoginResponse {
        private String message;
        private String loginId;
    }
}