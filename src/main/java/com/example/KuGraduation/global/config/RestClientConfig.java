package com.example.KuGraduation.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    RestClient crawlerClient(@Value("${crawler.base-url}") String baseUrl) {
        var rf = new SimpleClientHttpRequestFactory();
        rf.setConnectTimeout(1500);
        rf.setReadTimeout(180000);
        return RestClient.builder().baseUrl(baseUrl).requestFactory(rf).build();
    }

    @Bean
    RestClient sentimentClient(@Value("${sentiment.base-url}") String baseUrl) {
        var rf = new SimpleClientHttpRequestFactory();
        rf.setConnectTimeout(1500);
        rf.setReadTimeout(180000);
        return RestClient.builder().baseUrl(baseUrl).requestFactory(rf).build();
    }
}
