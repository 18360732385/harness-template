# 历史项目 Retrofit Playbook

## 目标

在不打断现网交付的前提下，将历史项目逐步升级到规范驱动的 AI 协作模式。

## 一页纸：治理阶段 × Maven Profile × 文档子集

与 [`pom.xml`](../../../pom.xml) 中 **harness-legacy**（默认激活）与 **harness-new**（更严 Checkstyle + JaCoCo `haltOnFailure=true`）及 [`quality-gates`](../00-governance/quality-gates.md) 门禁分级对齐。

| 治理阶段 | 行为摘要 | 建议复制的 `harness-collab/` 子集 | Maven 命令（示例） | JaCoCo 语义 |
|---|---|---|---|---|
| **observe** | 采集问题，不拦截发布 | `AGENTS.md`、`func.md`、`01-product-specs` + `02-design-docs` + `03-exec-plans` 模板；[`03-ai-workflow`](../03-ai-workflow/ai-delivery-playbook.md) | `mvn clean verify` 或显式 `mvn clean verify -Pharness-legacy` | 低阈值、`haltOnFailure=false`（legacy 默认） |
| **warn** | 告警进治理台账，逐步收敛 | 上述 + [`00-governance`](../00-governance/quality-gates.md) + [`01-architecture`](../01-architecture/architecture-constraints.md) + [`04-api-docs`](../../04-api-docs/README.md)（模板 [`api-doc-template.md`](../../04-api-docs/templates/api-doc-template.md)） | 同上（仍为 legacy 或团队自调 properties） | 保持 warn，计划修复缺口 |
| **enforce** | 不达标阻塞合入 | 完整 **methodology 00–04** + **`04-api-docs/`** + 根 `.cursor/rules` | `mvn clean verify -Pharness-new`（新项目或治理完成后） | 高阈值、`haltOnFailure=true` |

**仅 AI 协作流、暂不接治理模板时** 的最小复制包：`harness-collab/AGENTS.md`、`func.md`、`01-product-specs/`、`02-design-docs/`、`03-exec-plans/`，以及仓库根 `.cursor/rules/`；可不复制 `05-methodology/00-governance`。

若存量仓库已有成体系的内部规范且可能与 harness 不一致，建议一并复制 [**06-adapters/**](../../06-adapters/README.md)，在业务侧明确 **harness 默认优先** 与 **baseline 豁免**（不涉及改业务代码）。

**何时切换到 `-Pharness-new`**：新项目起步即采用；或存量项目完成 observe/warn 收敛、团队同意以覆盖率与严格 Checkstyle 阻塞合入时。

## 五步迁移

1. 资产盘点：模块、接口、测试、质量债务、线上风险。
2. 差距评估：对照本仓库模板识别差距。
3. 分批接入：先文档与规则，再质量门禁与测试体系。
4. 灰度验证：在低风险模块先 enforce。
5. 全量启用：形成统一门禁与流程。

## 推进策略

- 第一期：`observe`（2~4 周）
- 第二期：`warn`（2~6 周）
- 第三期：`enforce`（持续）

## 成功标准

- 关键模块满足架构约束与质量门禁（含约定落地与测试覆盖）。
- API 文档同步率 100%。
- 功能资产文档有持续更新。
- 线上问题率与回归缺陷率下降。
