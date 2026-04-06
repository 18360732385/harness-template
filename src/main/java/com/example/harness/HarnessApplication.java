package com.example.harness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 职责：Spring Boot 启动入口（模板示例工程）。
 * 作者：Harness Template
 * 创建时间：2026-04-06
 */
@SpringBootApplication
public class HarnessApplication {

    /**
     * 功能：启动应用。
     *
     * @param args 命令行参数
     */
    public static void main(final String[] args) {
        SpringApplication.run(HarnessApplication.class, args);
    }
}
