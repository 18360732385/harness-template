package com.example.harness;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 职责：校验 Spring 应用上下文可加载。
 * 作者：Harness Template
 * 创建时间：2026-04-06
 */
@SpringBootTest
class HarnessApplicationTest {

    /**
     * 功能：启动并装配应用上下文。
     * 入参：无
     * 出参：无
     * 异常说明：上下文加载失败时抛出
     */
    @Test
    void contextLoads() {
        // 上下文成功加载即通过
    }
}
