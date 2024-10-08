package ru.practicum.stats.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.stats.dto.EndpointHitDto;

import java.util.Map;

@Slf4j
public class StatsClient extends BaseClient {

    @Autowired
    public StatsClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(ClientHttpRequestFactory.class)
                        .build()
        );
    }

    public ResponseEntity<Object> addHit(EndpointHitDto dto) {
        log.info("/POST на /hit от {}", dto.getApp());
        return post("/hit", dto);
    }

    public ResponseEntity<Object> getStats(String start, String end, String[] uris, boolean unique) {
        Map<String, Object> param = Map.of(
                "start", start,
                "end", end,
                "uris", uris,
                "unique", unique
        );
        log.info("/GET на /stats : start={}, end={}, uris={}, unique={}", start, end, uris, unique);
        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", param);
    }
}