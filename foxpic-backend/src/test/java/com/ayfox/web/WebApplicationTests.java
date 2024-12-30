package com.ayfox.web;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {
    Logger logger = LoggerFactory.getLogger(WebApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Test
    void testFileName() {
        logger.info("111111");
        String fileName = "https://example.com/public/5/2024-12-29_VTRrs5UrA00znFGA_thumbnail.png";
        String name = StrUtil.subBefore(fileName, ".", true);
        logger.info(name);
    }
}
