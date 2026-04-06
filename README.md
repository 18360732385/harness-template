# Java Harness Engineering 模板仓库

本仓库提供一套可直接复用的 Harness Engineering 工程基线，适用于：

- 新项目（Bootstrap）：快速从 0 到 1 建立规范驱动的 AI 研发流程。
- 历史项目（Retrofit）：在不破坏存量交付节奏下渐进接入门禁和文档体系。

## 目录总览

- `AGENTS.md`：AI 协作主协议（需求、设计、计划、实现、测试、复盘）。
- `func.md`：功能资产登记（变更前先检索复用）。
- `docs/`：治理、架构、工程流程、AI 工作流、API 标准。
- `docs/05-rules/`：可直接复用的 user/project rules 文本版本。
- `product-specs/`：产品规格文档模板与实例。
- `design-docs/`：技术设计文档模板与实例。
- `exec-plans/`：执行计划模板与实例。
- `.cursor/rules/`：User Rules 与 Java Project Rules。
- `config/`：Checkstyle 与 SpotBugs 配置。
- `src/test/java/.../architecture`：ArchUnit 结构测试。

## 推荐使用流程

1. 在 `product-specs/` 编写需求规格。
2. 在 `design-docs/` 输出技术设计。
3. 在 `exec-plans/` 拆分可执行计划。
4. 按 `AGENTS.md` 流程执行开发与测试。
5. 通过质量门禁（Checkstyle、SpotBugs、ArchUnit）。
6. 同步 API 与功能文档（`docs/04-api-standards`、`func.md`）。

## 质量门禁

- 静态检查：Checkstyle、SpotBugs
- 结构测试：ArchUnit（分层依赖方向）
- 覆盖率：JaCoCo（模板默认阈值，可按项目阶段调整）
- 文档门禁：API 变更文档同步、功能资产同步
- 统一命令：`mvn clean verify`

## 迁移策略（历史项目）

建议按阶段推进：`observe -> warn -> enforce`，逐步收敛历史遗留问题，避免一次性改造风险。

## 新项目使用建议

建议从 `warn` 启动，首个稳定迭代切换至 `enforce`，兼顾效率与质量。

## 通用复用指南（模板定位）

- 新项目接入：直接复制本模板，优先启用 `mvn clean verify` 与 `.cursor/rules/*`。
- 历史项目接入：先对齐文档与规则，再分阶段开启门禁，结合 `docs/00-governance/legacy-baseline.md` 管理豁免。
- 中性原则：模板只提供工程能力与流程，不包含任何业务域模型与业务逻辑。

## 说明

- 当前仓库已包含可直接执行的正式配置：`.cursor/rules/*.mdc`、`pom.xml`、`config/*`、`src/test/java/*`。
- `docs/05-rules/` 与 `docs/00-governance/quality-tooling-templates.md` 继续保留为“可复制模板”，便于迁移到其他仓库。
