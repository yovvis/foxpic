package com.ayfox.web.controller;

import com.ayfox.web.core.ResultUtils;
import com.ayfox.web.core.model.vo.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查
 *
 * @author tenyon
 * @date 2024/12/9
 */
@RestController
@RequestMapping("/")
public class HealthController {
    @GetMapping("/health")
    public BaseResponse health() {
        return ResultUtils.success("health");
    }
}
