package com.example.KuGraduation.domain.news.dto.response;

import java.util.List;

public record CrawledArticle(
        String url,
        List<String> sentences
) {
}
