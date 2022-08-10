package ru.mesnyankin.demo.service;

import ru.mesnyankin.demo.model.camera.CameraSource;
import ru.mesnyankin.demo.model.camera.SourceData;
import ru.mesnyankin.demo.model.camera.TokenData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CameraDataSourceService {
    CompletableFuture<SourceData> getSourceData(String sourceUrl);
    CompletableFuture<TokenData> getTokenData(String tokenUrl);
    List<CameraSource> getSources();
}
