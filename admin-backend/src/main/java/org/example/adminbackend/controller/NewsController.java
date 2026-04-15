package org.example.adminbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class NewsController {

    @GetMapping("/news")
    public List<String> getNews() {
        return List.of("공지 1", "공지 2", "공지 3");
    }
}