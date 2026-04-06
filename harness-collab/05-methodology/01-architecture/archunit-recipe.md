# ArchUnit 引入食谱（可选）

本模板**不**默认执行 ArchUnit（依赖与测试源码挂在 **`archunit`** Maven profile）；当团队需要将 `architecture-constraints.md` 中的分层与依赖方向改为**机器校验**时，可按下列步骤接入或启用 profile。

## 1. 本模板已提供的 Maven profile（推荐）

根 [`pom.xml`](../../../pom.xml) 中 **`archunit`** profile 已声明 `archunit-junit5`（测试作用域），并通过 `build-helper-maven-plugin` 将源码目录 **`src/test/archunit/java`** 加入测试编译路径，因此**默认 `mvn verify` 不编译、不执行** ArchUnit 测试。

```bash
mvn clean verify -Pharness-new -Parchunit
```

业务仓若从零接入，可照下面「仅依赖声明 + 测试类」方式自行粘贴；或复制本模板的 profile 与 `src/test/archunit/java` 目录结构。

## 2. 自行接入：Maven 依赖（测试作用域）

在未使用上述 profile 时，可在 `pom.xml` 的 `<dependencies>` 中增加（版本按与 JUnit 5 兼容性自选）：

```xml
<dependency>
  <groupId>com.tngtech.archunit</groupId>
  <artifactId>archunit-junit5</artifactId>
  <version>1.3.0</version>
  <scope>test</scope>
</dependency>
```

## 3. 测试类放置位置

本模板实现：`src/test/archunit/java/<根包>/architecture/ArchitectureConstraintsTest.java`（仅 `-Parchunit` 时参与编译）。若放在默认 `src/test/java`，则与「默认不跑 ArchUnit」策略冲突，需自行用 `@Tag` + Surefire 分组等方式隔离。

## 4. 规则与约束对齐

- 将本文件 [`architecture-constraints.md`](architecture-constraints.md) 中的**允许 / 禁止**依赖边翻译为 `ArchRule`（例如 `classes().that().resideInAnyPackage("..controller..").should().onlyBeAccessed().byAnyPackage("..interfaces..", "..controller..")` 等需按**实际包名**调整）。
- 历史项目豁免类可 `.because("legacy-baseline")` 并登记在 [`legacy-baseline.md`](../00-governance/legacy-baseline.md) 中，与人工台账一致。

## 5. 与门禁的关系

- 测试类随 `mvn verify` 执行；失败即阻塞（与 `harness-new` 下其它测试一致）。
- 若仅想「observe」ArchUnit 结果，可先使用 `ArchRule` 的评估 API 记录日志而不 `assert`，待收敛后再改为硬失败（不推荐长期保留）。

## 6. 验收

- [ ] 规则与 `architecture-constraints.md` 已对照评审，包名模式已更新为仓库真实结构。
- [ ] 本地 `mvn clean verify`（需要 ArchUnit 时使用 `-Parchunit` 等组合）通过。
