# adapters（仅模板｜迁入老服务时用）

本目录 **不修改业务代码**，只提供可复制到目标仓库的 **文字模板与 Cursor 规则片段**，用于解决：

**历史项目内部规范 / 口头约定** 与 **`harness-collab/`**（含 `AGENTS.md`、`05-methodology/`）不一致时，让 AI **默认遵循 harness-collab**，并把**允许的例外**登记清楚，避免模型在多个「权威」之间随机取舍。

## 推荐用法（目标业务仓库内）

1. **复制整份 `adapters/`** 到业务仓的 `harness-collab/06-adapters/`（或仅复制本 README + `decision-record-template.md` + `snippets/`）。
2. 在业务仓根 **`harness-collab/README.md`**（或团队选定的单一入口文）中增加一节，可直接粘贴 [HARNESS-PRECEDENCE.md](HARNESS-PRECEDENCE.md) 全文或首段「冲突裁决」。
3. 将 [snippets/20-harness-precedence-legacy.mdc.sample](snippets/20-harness-precedence-legacy.mdc.sample) **复制到仓库根** `.cursor/rules/` 下，**去掉 `.sample` 后缀**（改为 `.mdc`），与现有规则编号协调；确保 `alwaysApply: true` 在你团队中生效。
4. 所有**经批准的、暂时沿用老规范**的项，记入 [`05-methodology/00-governance/legacy-baseline.md`](../05-methodology/00-governance/legacy-baseline.md)（或业务仓等价路径），并在 [decision-record-template.md](decision-record-template.md) 留档，便于评审与协议 AI：**仅 baseline 与决策记录中的条目可偏离 harness**。

## 与 AGENTS 权威优先级的关系

[harness-collab/AGENTS.md](../AGENTS.md) **§2.1** 已规定：用户明确指令 > `AGENTS` + 本 `README` 的约定 > `.cursor/rules` > `05-methodology`。

本 `adapters` 模板解决的是：**历史内部文档未出现在该列表中时**，在业务仓内补一句 **「冲突时以 harness-collab 为准」**，并把例外 **白名单化**；其中 **§2.1 第 1 条（用户指令）** 仍然高于一切。

## 目录

| 文件 | 说明 |
|------|------|
| [HARNESS-PRECEDENCE.md](HARNESS-PRECEDENCE.md) | 可贴入 README 的短条文（冲突裁决 + baseline） |
| [decision-record-template.md](decision-record-template.md) | 单条决策记录表（复制后逐条填写） |
| [snippets/20-harness-precedence-legacy.mdc.sample](snippets/20-harness-precedence-legacy.mdc.sample) | 复制到 `.cursor/rules/` 的 Cursor 规则样本 |

返回：[harness-collab/README.md](../README.md)
