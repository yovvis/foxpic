package com.ayfox.web.manager;

import io.minio.ObjectWriteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;


@SpringBootTest
class MinioManagerTest {
    @Resource
    private MinioManager minioManager;

    @Test
    void putObject() {
        ObjectWriteResponse objectWriteResponse = minioManager.uploadObject("2024/p.jpg", new File("D:\\录屏\\p.jpg"));
    }
}