package org.example.adminbackend.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/news")
public class NewsController {

    @GetMapping
    public List<NewsResponse> getNewsList() {
        return List.of(
                new NewsResponse(1L, "첫 번째 뉴스", "내용 1"),
                new NewsResponse(2L, "두 번째 뉴스", "내용 2")
        );
    }

    @PostMapping
    public NewsResponse createNews(@RequestBody NewsCreateRequest request) {
        return new NewsResponse(3L, request.getTitle(), request.getContent());
    }

    @Getter
    @AllArgsConstructor
    public class NewsResponse {
        private Long id;
        private String title;
        private String content;
    }

    @Getter
    @Setter
    public class NewsCreateRequest {
        private String title;
        private String content;
    }
}

