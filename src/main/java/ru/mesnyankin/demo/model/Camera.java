package ru.mesnyankin.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.mesnyankin.demo.model.camera.SourceData;
import ru.mesnyankin.demo.model.camera.TokenData;
import ru.mesnyankin.demo.model.camera.sourcedata.UrlType;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Camera {
    private Integer id;
    private UrlType urlType;
    private String videoUrl;
    private String value;
    private Integer ttl;

    public Camera(Integer id, SourceData sourceData, TokenData tokenData) {
        this.id = id;
        this.urlType = sourceData.getUrlType();
        this.videoUrl = sourceData.getVideoUrl();
        this.value = tokenData.getValue();
        this.ttl = tokenData.getTtl();
    }
}
