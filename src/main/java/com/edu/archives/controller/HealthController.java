package com.edu.archives.controller;

import com.edu.archives.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 健康检查控制器。
// 调用关系：部署平台或运维可调用 /health 检查服务状态。
@RestController
public class HealthController {

	// 健康检查接口。
    @GetMapping("/health")
    public Result health() {
		// 返回固定健康信息。
        return Result.success("系统运行正常");
    }
}