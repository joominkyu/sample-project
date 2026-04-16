package com.example.backend.controller;

import com.example.backend.repository.news.NewsResponse;
import com.example.backend.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<NewsResponse> getNewsList() {
        return newsService.getNewsList();
    }

    @GetMapping("/{id}")
    public NewsResponse getNews(@PathVariable Long id) {
        return newsService.getNews(id);
    }
}