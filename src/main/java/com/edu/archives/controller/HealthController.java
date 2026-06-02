package com.edu.archives.controller;

import com.edu.archives.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public Result health() {
        return Result.success("系统运行正常");
    }
}