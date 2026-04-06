package com.example.harness.service;

/**
 * 职责：模板示例用例服务，提供最小可测业务方法。
 * 作者：Harness Template
 * 创建时间：2026-04-06
 */
public class WelcomeService {

    /**
     * 功能：返回固定欢迎语，便于单测与 JaCoCo 基线。
     *
     * @return 欢迎语文本
     */
    public String welcome() {
        return "ok";
    }
}
