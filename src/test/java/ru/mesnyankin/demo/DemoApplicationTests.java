package ru.mesnyankin.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mesnyankin.demo.model.Camera;
import ru.mesnyankin.demo.model.camera.CameraSource;
import ru.mesnyankin.demo.model.camera.SourceData;
import ru.mesnyankin.demo.model.camera.TokenData;
import ru.mesnyankin.demo.model.camera.sourcedata.UrlType;
import ru.mesnyankin.demo.service.CameraDataSourceService;
import ru.mesnyankin.demo.service.CameraStorageService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    @MockBean
    private CameraStorageService cameraStorageService;

    @MockBean
    CameraDataSourceService cameraDataSourceService;

    @Test
    public void getCameraList_thenOK() {
        Camera camera = new Camera(1, UrlType.LIVE, "1", "2", 3);
        given(cameraStorageService.getCameraList()).willReturn(List.of(camera));

        List<Camera> cameraList = cameraStorageService.getCameraList();
        assertEquals(cameraList.get(0), camera);
    }

    @Test
    public void checkCameraListSize_thenOK() {
        Camera camera = new Camera(1, UrlType.LIVE, "1", "2", 3);
        given(cameraStorageService.getCameraList()).willReturn(List.of(camera));

        List<Camera> cameraList = cameraStorageService.getCameraList();
        assertEquals(cameraList.size(), 1);
    }

    @Test
    public void getCamerasSource_thenOK() {
        CameraSource cameraSource = new CameraSource(1, "data", "token");
        given(cameraDataSourceService.getSources()).willReturn(List.of(cameraSource));

        List<CameraSource> cameraList = cameraDataSourceService.getSources();
        assertEquals(cameraList.get(0), cameraSource);
        assertEquals(cameraList.size(), 1);
    }

    @Test
    public void getCamerasSourcesList_thenOK() {
        CameraSource cameraSource = new CameraSource(1, "data", "token");
        given(cameraDataSourceService.getSources()).willReturn(List.of(cameraSource));

        List<CameraSource> cameraList = cameraDataSourceService.getSources();
        assertEquals(cameraList.get(0), cameraSource);
        assertEquals(cameraList.size(), 1);
    }

    @Test
    public void getSourcesData_thenOK() throws ExecutionException, InterruptedException {
        SourceData sourceData = new SourceData(UrlType.LIVE, "url");
        CompletableFuture<SourceData> item = CompletableFuture.completedFuture(sourceData);
        given(cameraDataSourceService.getSourceData(anyString())).willReturn(item);
        CompletableFuture<SourceData>  completableFuture = cameraDataSourceService.getSourceData(anyString());
        assertEquals(completableFuture.get(), sourceData);
    }

    @Test
    public void getTokenData_thenOK() throws ExecutionException, InterruptedException {
        TokenData tokenData = new TokenData("value", 1);
        CompletableFuture<TokenData> item = CompletableFuture.completedFuture(tokenData);
        given(cameraDataSourceService.getTokenData(anyString())).willReturn(item);
        CompletableFuture<TokenData>  completableFuture = cameraDataSourceService.getTokenData(anyString());
        assertEquals(completableFuture.get(), tokenData);
    }
}
