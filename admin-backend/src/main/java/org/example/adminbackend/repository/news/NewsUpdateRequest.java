package org.example.adminbackend.repository.news;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsUpdateRequest {
    private String title;
    private String content;
}
