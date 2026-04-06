# User Rules（可复制到 Cursor Rule）

## 权威来源与同步

- **勿在本文件单独定稿**：权威来源为仓库根 **`.cursor/rules/01-user-rules.mdc`**；变更后按 [`harness-collab/README.md`](../../README.md) 中「Cursor 规则：单一事实来源与同步」同步 `07-cursor-rules` 与本目录。
- 本文件为 Markdown 导出，供无 Cursor 环境阅读或与 `05-methodology` 一并分发。

## 权威优先级（冲突时）

1. 当前任务中用户的明确指令。  
2. `harness-collab/AGENTS.md` 与 `harness-collab/README.md`（含 func 路径约定）。  
3. `.cursor/rules/*.mdc` 与方法论文档镜像。  
4. 其它说明；若存在多个 `func.md` 路径，以 `harness-collab/README.md` 表格为准。

## 适用范围

适用于 Java 项目中 AI 参与需求分析、设计、计划、实现、测试、修复全流程。

## 触发条件

- 接收到功能开发、改造、修复、重构任务。
- 任何涉及代码、接口、流程变化的任务。

## 任务分型与必需输出

- 新功能：需求规格 + 设计文档 + 执行计划 + 测试 + 文档同步。
- 改造/重构：影响分析 + 执行计划 + 回滚策略 + 测试。
- 修复：问题复盘 + 修复说明 + 回归用例 + 文档同步。

## 强制约束

1. 开发前必须读取 `harness-collab/func.md`（或团队约定的 func 权威路径），优先复用已有服务能力。
2. 先输出设计与计划，再进入实现。
3. 实现必须遵循 plan_vs_impl。
4. 任何 API 变更必须同步接口文档。
5. 每次开发完成后必须同步 func 权威路径（本模板为 `harness-collab/func.md`）。

## 执行流程

`需求理解 -> 功能拆分 -> 设计文档 -> 执行计划 -> 实现 -> 单元/集成/回归 -> 文档同步 -> 复盘`

## 输出要求

- 关键结论可追溯到需求与设计。
- 代码、测试、文档三者一致。
- 避免无依据的假设与越界修改。

## 验收证据（DoD）

- 本地验证命令：`mvn clean verify` 通过。
- 设计/计划/实现映射清晰。
- API 文档与 func 权威路径同步完成。
