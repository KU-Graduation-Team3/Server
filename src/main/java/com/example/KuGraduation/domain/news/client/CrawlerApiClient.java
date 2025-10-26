package com.example.KuGraduation.domain.news.client;

import com.example.KuGraduation.domain.news.dto.response.CrawledArticle;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CrawlerApiClient {

    private final RestClient crawlerClient;

    public List<CrawledArticle> crawl(String stockCode, int maxArticles) {
        List<CrawledArticle> body = this.crawlerClient.get()
                .uri(uri -> uri.path("/crawl")
                        .queryParam("stock_code", stockCode)
                        .queryParam("max_articles", maxArticles)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<List<CrawledArticle>>() {});
        return body != null ? body : Collections.emptyList();
    }
}
