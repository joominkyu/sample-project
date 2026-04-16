package org.example.adminbackend.service;

import org.example.adminbackend.entity.News;
import org.example.adminbackend.repository.news.NewsCreateRequest;
import org.example.adminbackend.repository.news.NewsRepository;
import org.example.adminbackend.repository.news.NewsResponse;
import org.example.adminbackend.repository.news.NewsUpdateRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
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

    public NewsResponse createNews(NewsCreateRequest request) {
        News news = new News(request.getTitle(), request.getContent());
        News saved = newsRepository.save(news);
        return NewsResponse.from(saved);
    }

    @Transactional
    public NewsResponse updateNews(Long id, NewsUpdateRequest request) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("뉴스를 찾을 수 없습니다. id=" + id));

        news.update(request.getTitle(), request.getContent());
        return NewsResponse.from(news);
    }

    public void deleteNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("뉴스를 찾을 수 없습니다. id=" + id));

        newsRepository.delete(news);
    }
}