package com.example.KuGraduation.domain.news.dto.request;

public record SentimentItem(String text, String label, Integer score) { // score: -1|0|1
    public int safeScore() { return score == null ? 0 : score; }
}
