package com.example.harness.architecture;

import com.example.harness.HarnessApplication;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * 职责：可选 ArchUnit 分层校验，与 methodology 中 architecture-constraints 对齐。
 * 仅在使用 {@code -Parchunit} 时参与编译与执行。
 * 作者：Harness Template
 * 创建时间：2026-04-06
 */
@AnalyzeClasses(
        packagesOf = HarnessApplication.class,
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ArchitectureConstraintsTest {

    /**
     * 禁止 service 依赖 controller（允许依赖方向的反向）。
     */
    @ArchTest
    static final ArchRule servicesShouldNotDependOnControllers = noClasses()
            .that().resideInAPackage("..service..")
            .should().dependOnClassesThat().resideInAnyPackage("..controller..");

    /**
     * 禁止 domain 依赖 repository（出站适配不应被领域核心引用）。
     */
    @ArchTest
    static final ArchRule domainShouldNotDependOnRepositories = noClasses()
            .that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAnyPackage("..repository..");
}
