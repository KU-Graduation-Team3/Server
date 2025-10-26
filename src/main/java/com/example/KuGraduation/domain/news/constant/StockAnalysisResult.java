package com.example.KuGraduation.domain.news.constant;

public enum StockAnalysisResult { POSITIVE, NEUTRAL, NEGATIVE;
    public static StockAnalysisResult fromSum(int sum) {
        if (sum > 0) return POSITIVE;
        if (sum < 0) return NEGATIVE;
        return NEUTRAL;
    }
}
