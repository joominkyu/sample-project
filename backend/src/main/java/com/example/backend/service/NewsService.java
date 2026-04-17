package com.example.backend.service;

import com.example.backend.entity.News;
import com.example.backend.repository.news.NewsRepository;
import com.example.backend.repository.news.NewsResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository;

    public List<NewsResponse> getNewsList() {
        return newsRepository.findAll()
                .stream()
                .map(NewsResponse::from)
                .toList();
    }

    public NewsResponse getNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("뉴스를 찾을 수 없습니다. id=" + id));

        return NewsResponse.from(news);
    }
}