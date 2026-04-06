package com.example.harness.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 职责：WelcomeService 单元测试。
 * 作者：Harness Template
 * 创建时间：2026-04-06
 */
class WelcomeServiceTest {

    /**
     * 功能：校验欢迎语返回值。
     * 入参：无
     * 出参：无
     * 异常说明：断言失败时抛出
     */
    @Test
    void welcomeReturnsOk() {
        Assertions.assertEquals("ok", new WelcomeService().welcome());
    }
}
