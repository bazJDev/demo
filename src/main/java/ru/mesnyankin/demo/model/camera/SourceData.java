package ru.mesnyankin.demo.model.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.mesnyankin.demo.model.camera.sourcedata.UrlType;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SourceData {
    private UrlType urlType;
    private String videoUrl;
}
