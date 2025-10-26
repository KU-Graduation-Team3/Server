package com.example.KuGraduation.domain.news.client;

import com.example.KuGraduation.domain.news.dto.request.SentimentItem;
import com.example.KuGraduation.domain.news.dto.request.SentimentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SentimentApiClient {

    private final RestClient sentimentClient;

    public List<SentimentItem> predict(List<String> texts) {
        List<SentimentItem> body = this.sentimentClient.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new SentimentRequest(texts))
                .retrieve()
                .body(new ParameterizedTypeReference<List<SentimentItem>>() {});
        return body != null ? body : Collections.emptyList();
    }
}