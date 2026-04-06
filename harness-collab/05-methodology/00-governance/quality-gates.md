# 质量门禁说明

## 门禁目标

确保需求、设计、实现、测试和文档形成闭环，并可被自动校验。

## 门禁分级

### observe

- 采集问题，不阻塞合入。
- 适用于历史项目初始接入阶段。

### warn

- 输出告警并记录到治理台账。
- 建议要求责任人提供修复计划。

### enforce

- 未达标直接阻塞合入。
- 适用于新项目与完成治理后的历史项目。

## 一页纸（与 Profile、文档复制）

| 阶段 | 与 CI / 本地 | 文档包建议 |
|------|----------------|------------|
| observe / warn | 默认 **`harness-legacy`**：`mvn clean verify` 等价于带 `-Pharness-legacy`（[`pom.xml`](../../../pom.xml) `activeByDefault`） | 见 [Retrofit Playbook 一页纸矩阵](../02-engineering/retrofit-playbook.md) |
| enforce（新项目或已收敛存量） | **`harness-new`**：`mvn clean verify -Pharness-new`（更严 Checkstyle、JaCoCo 失败即阻塞） | 完整 **methodology 00–04** + Cursor 规则 |

详细矩阵（含应复制子目录、JaCoCo 语义）以 [**retrofit-playbook**](../02-engineering/retrofit-playbook.md) 为准。

## 门禁项

- 静态检查通过（Checkstyle、SpotBugs）。
- 分层与依赖方向符合 `harness-collab/05-methodology/01-architecture/architecture-constraints.md`（通过评审与治理闭环保证；本模板无内置自动结构测试）。
- 单元测试与集成测试通过。
- 覆盖率检查完成（JaCoCo，模板默认 `warn` 语义，可按项目阶段切换到 `enforce`）。
- API 变更文档同步完成（正文位于 `harness-collab/04-api-docs/`，格式见 `04-api-docs/templates/api-doc-template.md`）。
- `harness-collab/func.md` 功能资产同步完成。
- 风险与回滚策略已记录（必须落在 `harness-collab/03-exec-plans` 文档中）。

## 建议执行顺序

1. 本地执行 `mvn clean verify`（统一门禁命令）。
2. CI 执行 `mvn clean verify` 并归档测试报告。
3. 通过后进入评审与发布流程。

## 复用建议（新项目/历史项目）

- 新项目：建议从 `warn` 启动，首个稳定迭代切换到 `enforce`。
- 历史项目：建议按 `observe -> warn -> enforce` 逐步提升门禁强度。

## 风险与回滚可检查闭环

- 每个任务必须在 `harness-collab/03-exec-plans` 中填写“风险与回滚”并可审计。
- 参考模板：`harness-collab/03-exec-plans/templates/template.md`
- 评审时需核对以下字段：触发条件、影响范围、回滚步骤、负责人、验证结果。
