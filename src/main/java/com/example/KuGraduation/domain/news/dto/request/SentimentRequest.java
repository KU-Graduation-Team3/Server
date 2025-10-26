package com.example.KuGraduation.domain.news.dto.request;

import java.util.List;

public record SentimentRequest(
        List<String> texts
) {
}
