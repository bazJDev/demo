package ru.mesnyankin.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mesnyankin.demo.model.Camera;
import ru.mesnyankin.demo.service.CameraStorageService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    private final CameraStorageService cameraStorageService;

    public RestController(CameraStorageService cameraStorageService) {
        this.cameraStorageService = cameraStorageService;
    }

    @GetMapping("getCameraList")
    public ResponseEntity<List<Camera>> getCameraList() {
        return ResponseEntity.ok(cameraStorageService.getCameraList());
    }
}
