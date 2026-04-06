---
description: 
alwaysApply: true
---

# AGENTS 协作协议（Java Harness）

## 1. 目标

通过规范驱动 AI Coding，让 AI 深度参与并稳定执行以下流程：

`需求理解 -> 模块拆分 -> 技术设计 -> 执行计划 -> 代码实现 -> 测试验证 -> 缺陷修复 -> 复盘沉淀`

## 2. 适用场景

- 新项目 Bootstrap：直接采用全套目录与门禁。
- 历史项目 Retrofit：按渐进策略接入（observe/warn/enforce）。

**与历史内部规范冲突时**：在业务仓库中请使用 [**adapters/**](adapters/README.md) 模板（条文 + 决策记录 + 可选 Cursor 规则片段），将 harness 约定置于默认优先，例外仅走 `legacy-baseline` 登记。

## 2.1 权威优先级（冲突时）

当用户指令、本协议、Cursor 规则或其它文档要求不一致时，按下列顺序解释（前者优先）：

1. **当前任务中用户的明确指令**（含范围、取舍、禁止项）。
2. **本文件（harness-collab/AGENTS.md）**与团队已采纳的 **`harness-collab/README.md`** 约定（例如 `func.md` 的权威路径）。
3. **仓库根 `.cursor/rules/*.mdc`**（通用工程规则；其中 Java 专属规则仅在与 `**/*.java` 相关会话中由编辑器附加，见各规则 frontmatter）。
4. **`harness-collab/methodology/*`** 中的方法与模板文档。

## 3. 强制执行顺序

1. 阅读 `harness-collab/func.md`，确认是否可复用已有能力。
2. 输出 `harness-collab/product-specs`（需求）与 `harness-collab/design-docs`（设计）。
3. 输出 `harness-collab/exec-plans`（分解与验收）。
4. 按计划逐项实现，禁止跳步实现。
5. 执行测试（单元、集成、回归）与质量门禁。
6. 同步 API 文档与功能资产文档。

## 4. plan_vs_impl 约束

- 实现必须映射到计划条目。
- 每个变更必须能追溯到需求与设计。
- 出现偏离时，先更新计划与设计，再改代码。

## 5. 文档同步约束

- API 入参、出参、URL、Method 变化必须同步 **`harness-collab/api-doc/`**（建议按模块写在 `api-doc/modules/` 下，格式见 `api-doc/templates/api-doc-template.md`）。
- 新增/修改服务能力必须同步 `harness-collab/func.md`。
- 结构性改动必须同步 `harness-collab/methodology/01-architecture/`。

## 6. 质量门禁约束

- 代码门禁：Checkstyle、SpotBugs。
- 架构约定：分层与依赖方向以 `harness-collab/methodology/01-architecture/architecture-constraints.md` 为准，通过代码评审与治理闭环落实；**本模板不内置**自动分层依赖校验（如需可自行引入 ArchUnit 等工具）。
- 测试门禁：关键路径单测与集成测试通过。

## 7. 历史项目改造策略

- 第 1 阶段（observe）：记录问题，不拦截发布。
- 第 2 阶段（warn）：告警并纳入治理清单。
- 第 3 阶段（enforce）：门禁拦截，必须修复后合入。

## 8. 输出物清单

- 需求规格：`harness-collab/product-specs/*`
- 技术设计：`harness-collab/design-docs/*`
- 执行计划：`harness-collab/exec-plans/*`
- 实施代码：`src/main/java/*`
- 测试代码：`src/test/java/*`
- 文档更新：`harness-collab/methodology/*`（治理、架构、工程等）+ **`harness-collab/api-doc/`**（接口说明，按模块）+ `harness-collab/func.md`
