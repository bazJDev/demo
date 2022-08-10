package ru.mesnyankin.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mesnyankin.demo.model.camera.CameraSource;
import ru.mesnyankin.demo.model.camera.SourceData;
import ru.mesnyankin.demo.model.camera.TokenData;
import ru.mesnyankin.demo.service.CameraDataSourceService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class CameraDataSourceServiceImlp implements CameraDataSourceService {
    private final RestTemplate restTemplate;
    private final static String basicUrl = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";

    public CameraDataSourceServiceImlp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public CompletableFuture<SourceData> getSourceData(String dataUrl) {
        return CompletableFuture.completedFuture(restTemplate.getForObject(dataUrl, SourceData.class));
    }

    @Async
    public CompletableFuture<TokenData> getTokenData(String dataUrl) {
        return CompletableFuture.completedFuture(restTemplate.getForObject(dataUrl, TokenData.class));
    }

    public List<CameraSource> getSources() {
        ResponseEntity<CameraSource[]> cameraSourceArray = restTemplate.getForEntity(basicUrl, CameraSource[].class);
        return Arrays.asList(Objects.requireNonNull(cameraSourceArray.getBody()));
    }
}
