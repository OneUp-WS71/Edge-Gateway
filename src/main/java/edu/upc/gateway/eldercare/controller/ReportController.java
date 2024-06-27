package edu.upc.gateway.eldercare.controller;

import edu.upc.gateway.eldercare.model.Report;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Tag(name = "Report gateway", description = "the report gateway")
@RestController
@RequestMapping("/api/Elderlycare/v1")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final WebClient webClient;

    public ReportController(@Value("${downstream.service.url}") String downstreamUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(downstreamUrl)
                .build();
    }

    @PostMapping("/report")
    public Mono<Report> createReport(@RequestBody Report report, @RequestParam("patientId") int id) {
        String endpoint = "/api/oneup/v1/report/" + id;

        return webClient.post()
                .uri(endpoint)
                .body(BodyInserters.fromValue(report))
                .retrieve()
                .bodyToMono(Report.class)
                .doOnError(WebClientResponseException.class, e -> {
                    logger.error("Error creating report: Status {}, Body: {}", e.getStatusCode(), e.getResponseBodyAsString());
                })
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new RuntimeException("Failed to create report", e)));
    }
}
