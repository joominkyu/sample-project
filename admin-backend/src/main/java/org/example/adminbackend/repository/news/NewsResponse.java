package org.example.adminbackend.repository.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.adminbackend.entity.News;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NewsResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static NewsResponse from(News news) {
        return new NewsResponse(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getCreatedAt(),
                news.getUpdatedAt()
        );
    }
}