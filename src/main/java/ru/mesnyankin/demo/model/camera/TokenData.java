package ru.mesnyankin.demo.model.camera;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TokenData {
    private String value;
    private Integer ttl;
}
