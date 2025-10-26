package com.example.KuGraduation.domain.news.service;

import com.example.KuGraduation.domain.news.client.CrawlerApiClient;
import com.example.KuGraduation.domain.news.client.SentimentApiClient;
import com.example.KuGraduation.domain.news.constant.StockAnalysisResult;
import com.example.KuGraduation.domain.news.dto.request.SentimentItem;
import com.example.KuGraduation.domain.news.dto.request.SentimentRequest;
import com.example.KuGraduation.domain.news.dto.response.AnalysisResult;
import com.example.KuGraduation.domain.news.dto.response.CrawledArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsAnalysisService {

    private final CrawlerApiClient crawlerApiClient;
    private final SentimentApiClient sentimentApiClient;

    public AnalysisResult analyzeByStockCode(String stockCode, Integer maxArticles) {
        int max = (maxArticles != null) ? maxArticles : 5;

        List<CrawledArticle> articles = this.crawlerApiClient.crawl(stockCode, max);
        if (articles.isEmpty()) {
            return AnalysisResult.empty(stockCode);
        }

        List<String> allSentences = new ArrayList<>();
        for (CrawledArticle article : articles) {
            if (article.sentences() != null && !article.sentences().isEmpty()) {
                allSentences.addAll(article.sentences());
            }
        }
        if (allSentences.isEmpty()) {
            return AnalysisResult.empty(stockCode);
        }

        List<SentimentItem> items = this.sentimentApiClient.predict(allSentences);
        int sum = items.stream().mapToInt(SentimentItem::safeScore).sum();
        StockAnalysisResult overall = StockAnalysisResult.fromSum(sum);

        int positives = (int) items.stream().filter(i -> i.safeScore() > 0).count();
        int negatives = (int) items.stream().filter(i -> i.safeScore() < 0).count();
        int neutrals  = (int) items.stream().filter(i -> i.safeScore() == 0).count();

        return new AnalysisResult(
                stockCode,
                allSentences.size(),
                sum,
                overall,
                positives, neutrals, negatives
        );
    }

}
