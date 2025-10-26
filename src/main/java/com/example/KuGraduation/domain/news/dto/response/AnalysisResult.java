package com.example.KuGraduation.domain.news.dto.response;

import com.example.KuGraduation.domain.news.constant.Overall;
import com.example.KuGraduation.domain.news.dto.request.SentimentItem;

import java.util.List;

public record AnalysisResult(
        String stockCode,
        int sentenceCount,
        int totalScore,
        Overall overall,
        int positiveCount,
        int neutralCount,
        int negativeCount,
        List<SentimentItem> sampleTop5 // 미리보기
) {
    public static AnalysisResult empty(String code) {
        return new AnalysisResult(code, 0, 0, Overall.NEUTRAL, 0, 0, 0, List.of());
    }
}
