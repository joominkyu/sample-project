package org.example.adminbackend.repository.news;

import org.example.adminbackend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByOrderByCreatedAtDesc();
}