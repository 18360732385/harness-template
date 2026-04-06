package com.example.harness.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/**
 * 类说明：分层依赖架构约束测试。
 * 作者：AI Agent
 * 创建时间：2026-04-06
 */
@AnalyzeClasses(packages = "com.example")
@SuppressWarnings("unused")
public class LayerDependencyArchTest {
    /**
     * 方法说明：控制是否允许空包规则直接通过，用于新老项目分档。
     * 入参：无。
     * 出参：布尔值，true 表示允许空规则，false 表示必须命中实际类。
     * 异常：无。
     */
    private static final boolean ALLOW_EMPTY_SHOULD = Boolean.parseBoolean(
            System.getProperty("archunit.allow.empty.should", "true"));

    /**
     * 方法说明：控制 application 是否允许直接依赖 infrastructure。
     * 入参：无。
     * 出参：布尔值，true 表示允许，false 表示禁止。
     * 异常：无。
     */
    private static final boolean APPLICATION_INFRASTRUCTURE_ALLOWED = Boolean.parseBoolean(
            System.getProperty("archunit.application.infrastructure.allowed", "true"));

    /**
     * 方法说明：控制是否强制启用 infrastructure 边界约束。
     * 入参：无。
     * 出参：布尔值，true 表示启用，false 表示关闭。
     * 异常：无。
     */
    private static final boolean ENFORCE_INFRASTRUCTURE_BOUNDARY = Boolean.parseBoolean(
            System.getProperty("archunit.enforce.infrastructure.boundary", "false"));

    /**
     * 方法说明：校验 application 层不依赖 interfaces 层。
     * 入参：无。
     * 出参：无。
     * 异常：测试失败时由 ArchUnit 抛出断言异常。
     */
    @ArchTest
    static final ArchRule applicationShouldNotDependOnInterfaces = noClasses()
            .that().resideInAPackage("..application..")
            .should().dependOnClassesThat().resideInAPackage("..interfaces..")
            .allowEmptyShould(ALLOW_EMPTY_SHOULD);

    /**
     * 方法说明：校验 domain 层不依赖 infrastructure 层。
     * 入参：无。
     * 出参：无。
     * 异常：测试失败时由 ArchUnit 抛出断言异常。
     */
    @ArchTest
    static final ArchRule domainShouldNotDependOnInfrastructure = noClasses()
            .that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("..infrastructure..")
            .allowEmptyShould(ALLOW_EMPTY_SHOULD);

    /**
     * 方法说明：校验 interfaces 层仅依赖 application 与 domain。
     * 入参：无。
     * 出参：无。
     * 异常：测试失败时由 ArchUnit 抛出断言异常。
     */
    @ArchTest
    static final ArchRule interfacesShouldOnlyDependOnAllowedLayers = classes()
            .that().resideInAPackage("..interfaces..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                    "java..",
                    "javax..",
                    "jakarta..",
                    "org.springframework..",
                    "..interfaces..",
                    "..application..",
                    "..domain..")
            .allowEmptyShould(ALLOW_EMPTY_SHOULD);

    /**
     * 方法说明：在新项目档位下，禁止 application 层直接依赖 infrastructure。
     * 入参：无。
     * 出参：无。
     * 异常：测试失败时由 ArchUnit 抛出断言异常。
     */
    @ArchTest
    static final ArchRule applicationShouldNotDependOnInfrastructure = APPLICATION_INFRASTRUCTURE_ALLOWED
            ? classes().should().haveSimpleNameNotContaining("___HARNESS_NOOP___").allowEmptyShould(true)
            : noClasses()
            .that().resideInAPackage("..application..")
            .should().dependOnClassesThat().resideInAPackage("..infrastructure..")
            .allowEmptyShould(ALLOW_EMPTY_SHOULD);

    /**
     * 方法说明：在新项目档位下，限制 infrastructure 反向依赖 application/interfaces。
     * 入参：无。
     * 出参：无。
     * 异常：测试失败时由 ArchUnit 抛出断言异常。
     */
    @ArchTest
    static final ArchRule infrastructureShouldNotDependOnUpperLayers = ENFORCE_INFRASTRUCTURE_BOUNDARY
            ? noClasses()
            .that().resideInAPackage("..infrastructure..")
            .should().dependOnClassesThat().resideInAnyPackage("..application..", "..interfaces..")
            .allowEmptyShould(ALLOW_EMPTY_SHOULD)
            : classes().should().haveSimpleNameNotContaining("___HARNESS_NOOP___").allowEmptyShould(true);
}
