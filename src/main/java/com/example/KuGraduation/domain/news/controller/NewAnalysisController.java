package com.example.KuGraduation.domain.news.controller;

import com.example.KuGraduation.domain.news.dto.response.AnalysisResult;
import com.example.KuGraduation.domain.news.service.NewsAnalysisService;
import com.example.KuGraduation.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewAnalysisController {

    private final NewsAnalysisService service;

    @GetMapping("predict")
    public BaseResponse<AnalysisResult> predict(
            @RequestParam("code") String stockCode,
            @RequestParam(required = false) Integer maxArticles) {
        return BaseResponse.ok(service.analyzeByStockCode(stockCode, maxArticles),"종목 분석 완료");
    }
}
