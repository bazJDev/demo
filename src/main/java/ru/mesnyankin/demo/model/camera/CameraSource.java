package ru.mesnyankin.demo.model.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CameraSource {
    private Integer id;
    private String sourceDataUrl;
    private String tokenDataUrl;
}
