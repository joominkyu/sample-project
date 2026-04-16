package org.example.adminbackend.repository.news;

import org.example.adminbackend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
