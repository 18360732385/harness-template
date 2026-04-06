# Testing & Quality Rules

## 权威来源与同步

- **勿在本文件单独定稿**：权威来源为仓库根 **`.cursor/rules/11-testing-and-quality-rules.mdc`**；同步流程见 [`harness-collab/README.md`](../../README.md) 中「Cursor 规则：单一事实来源与同步」。门禁命令以 `.mdc` 为准（模板为 `mvn clean verify`）。
- 本文件为 Markdown 导出，供无 Cursor 环境阅读或与 `05-methodology` 一并分发。
- **在 Cursor 中的生效范围**：对应 `.mdc` 使用 `globs: "**/*.java"`、`alwaysApply: false`，与 Java 相关会话中附加。

## 测试策略

- 单元测试：覆盖核心分支、边界、异常路径。
- 集成测试：覆盖关键业务链路与依赖交互。
- 回归测试：每次缺陷修复必须沉淀回归用例。

## 质量门禁建议

- observe：仅采集质量数据
- warn：告警并要求治理计划
- enforce：不通过即阻塞

## 最低通过条件

- 单测与集成测试通过。
- 静态检查无阻塞问题。
- 分层与依赖方向符合 `harness-collab/05-methodology/01-architecture/architecture-constraints.md`，经评审与治理闭环落实（本模板不内置自动结构测试；机器校验可选参见 [`archunit-recipe.md`](../01-architecture/archunit-recipe.md)）。
- 文档同步完成（API + `harness-collab/func.md` 或团队约定的 func 路径）。

## 分档执行命令

- 历史项目：`mvn clean verify -Pharness-legacy`
- 新项目：`mvn clean verify -Pharness-new`
- 可选安全扫描：`mvn clean verify -Psecurity-scan`

## 验收证据

- Surefire：`target/surefire-reports/*`
- SpotBugs：`target/spotbugsXml.xml`
- JaCoCo：`target/site/jacoco/*`
