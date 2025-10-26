package com.example.KuGraduation.domain.news.constant;

public enum Overall { POSITIVE, NEUTRAL, NEGATIVE;
    public static Overall fromSum(int sum) {
        if (sum > 0) return POSITIVE;
        if (sum < 0) return NEGATIVE;
        return NEUTRAL;
    }
}
