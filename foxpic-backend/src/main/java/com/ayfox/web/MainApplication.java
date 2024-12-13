package com.ayfox.web;

import cn.hutool.core.util.StrUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 主类（项目启动入口）
 */
@SpringBootApplication
@MapperScan("com.ayfox.web.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MainApplication {

    public static void main(String[] args) throws UnknownHostException {
        Logger logger = LoggerFactory.getLogger(MainApplication.class);

        SpringApplication application = new SpringApplication(MainApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        ConfigurableApplicationContext app = application.run(args);
        logger.info("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ");
        Environment env = app.getEnvironment();
        // ip
        String ip = InetAddress.getLocalHost().getHostAddress();
        // port
        String port = env.getProperty("server.port");
        // path
        String path = env.getProperty("server.servlet.context-path");
        if (StrUtil.isBlank(path)) {
            path = "";
        }
        logger.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local访问网址: \t\thttp://localhost:" + port + path + "\n\t" +
                "External访问网址: \thttp://" + ip + ":" + port + path + "\n\t" +
                "接口文档地址: \t\thttp://localhost:" + port + path + "/doc.html\n\t" +
                "----------------------------------------------------------");
    }

}
