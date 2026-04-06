# ArchUnit 引入食谱（可选）

本模板**不**默认引入 ArchUnit；当团队需要将 `architecture-constraints.md` 中的分层与依赖方向改为**机器校验**时，可按下列步骤接入。

## 1. Maven 依赖（测试作用域）

在根 `pom.xml` 的 `<dependencies>`（或 `dependencyManagement` + 子模块）中增加（版本号按当前 ArchUnit 与 JUnit 5 兼容性自选）：

```xml
<dependency>
  <groupId>com.tngtech.archunit</groupId>
  <artifactId>archunit-junit5</artifactId>
  <version>1.3.0</version>
  <scope>test</scope>
</dependency>
```

## 2. 测试类放置位置

建议：`src/test/java/<根包>/architecture/ArchitectureConstraintsTest.java`（或 `.../arch/...`），与其它测试一致。

## 3. 规则与约束对齐

- 将本文件 [`architecture-constraints.md`](architecture-constraints.md) 中的**允许 / 禁止**依赖边翻译为 `ArchRule`（例如 `classes().that().resideInAnyPackage("..controller..").should().onlyBeAccessed().byAnyPackage("..interfaces..", "..controller..")` 等需按**实际包名**调整）。
- 历史项目豁免类可 `.because("legacy-baseline")` 并登记在 [`legacy-baseline.md`](../00-governance/legacy-baseline.md) 中，与人工台账一致。

## 4. 与门禁的关系

- 测试类随 `mvn verify` 执行；失败即阻塞（与 `harness-new` 下其它测试一致）。
- 若仅想「observe」ArchUnit 结果，可先使用 `ArchRule` 的评估 API 记录日志而不 `assert`，待收敛后再改为硬失败（不推荐长期保留）。

## 5. 验收

- [ ] 规则与 `architecture-constraints.md` 已对照评审，包名模式已更新为仓库真实结构。
- [ ] 本地 `mvn clean verify` 通过。
