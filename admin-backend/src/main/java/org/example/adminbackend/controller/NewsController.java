package org.example.adminbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.adminbackend.repository.news.NewsCreateRequest;
import org.example.adminbackend.repository.news.NewsResponse;
import org.example.adminbackend.repository.news.NewsUpdateRequest;
import org.example.adminbackend.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/news")
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

    @PostMapping
    public NewsResponse createNews(@RequestBody @Valid NewsCreateRequest request) {
        return newsService.createNews(request);
    }

    @PutMapping("/{id}")
    public NewsResponse updateNews(
            @PathVariable Long id,
            @RequestBody @Valid NewsUpdateRequest request
    ) {
        return newsService.updateNews(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
    }
}