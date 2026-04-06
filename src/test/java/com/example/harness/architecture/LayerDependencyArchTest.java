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
public class LayerDependencyArchTest {

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
            .allowEmptyShould(true);

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
            .allowEmptyShould(true);

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
                    "..interfaces..",
                    "..application..",
                    "..domain..")
            .allowEmptyShould(true);
}
