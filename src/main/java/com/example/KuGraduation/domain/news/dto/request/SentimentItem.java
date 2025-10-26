package com.example.KuGraduation.domain.news.dto.request;

public record SentimentItem(String text, String label, Integer score) {
    public int safeScore() { return score == null ? 0 : score; }
}
