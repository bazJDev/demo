package ru.mesnyankin.demo.service.impl;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import ru.mesnyankin.demo.model.Camera;
import ru.mesnyankin.demo.model.camera.CameraSource;
import ru.mesnyankin.demo.model.camera.SourceData;
import ru.mesnyankin.demo.model.camera.TokenData;
import ru.mesnyankin.demo.service.CameraStorageService;
import ru.mesnyankin.demo.service.CameraDataSourceService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Data
@Log
@Service
public class CameraStorageServiceImpl implements CameraStorageService {
    private final CameraDataSourceService cameraDataSourceService;
    @Override
    public List<Camera> getCameraList() {
        List<CameraSource> cameraSourceList = cameraDataSourceService.getSources();

        return cameraSourceList.parallelStream().
                map(cam -> {
                    try {
                        return buildCamera(cam);
                    } catch (InterruptedException | ExecutionException e) {
                        log.warning(String.format("failed build camera for id %s by %s", cam.getId(), e.getMessage()));
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    private Camera buildCamera(CameraSource cameraSource) throws ExecutionException, InterruptedException {
        log.info("build camera id :" + cameraSource.getId());
        CompletableFuture<SourceData> sourceDataCompletableFuture = cameraDataSourceService.getSourceData(cameraSource.getSourceDataUrl());
        CompletableFuture<TokenData> tokenDataCompletableFuture = cameraDataSourceService.getTokenData(cameraSource.getTokenDataUrl());
        CompletableFuture.allOf(sourceDataCompletableFuture, tokenDataCompletableFuture).join();

        return new Camera(cameraSource.getId(), sourceDataCompletableFuture.get(), tokenDataCompletableFuture.get());
    }
}
