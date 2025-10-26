package com.example.KuGraduation.domain.news.dto.response;

import com.example.KuGraduation.domain.news.constant.StockAnalysisResult;

public record AnalysisResult(
        String stockCode,
        int sentenceCount,
        int totalScore,
        StockAnalysisResult result,
        int positiveCount,
        int neutralCount,
        int negativeCount
) {
    public static AnalysisResult empty(String code) {
        return new AnalysisResult(code, 0, 0, StockAnalysisResult.NEUTRAL, 0, 0, 0);
    }
}
