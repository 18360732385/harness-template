# AI 交付作战手册（plan_vs_impl）

## 核心原则

- 先规格、后设计、再计划、最后实现。
- 每个实现动作必须在计划中有映射。
- 每次代码变更都要有验证证据。

## 标准链路

`harness-collab/product-specs -> harness-collab/design-docs -> harness-collab/exec-plans -> implementation -> tests -> review`

## AI 任务执行规范

1. 读取 `harness-collab/func.md`，优先复用。
2. 读取相关规格与设计文档。
3. 输出或更新执行计划。
4. 按计划逐步编码与测试。
5. 同步更新 API 与功能文档。

## spec -> design -> plan 映射检查

- **标识约定**：需求条目使用 **REQ-**（见 `product-specs` 模板），设计文档 **DES-**，执行计划 **EP-**，任务 **TASK-**；模板见 `harness-collab/product-specs/templates`、`design-docs/templates`、`exec-plans/templates`。
- Spec 映射：`harness-collab/product-specs` 中每个 **REQ** 条目必须在 `harness-collab/design-docs` 中有对应设计说明（设计文档 §0 映射表）。
- Design 映射：`harness-collab/design-docs` 中每个关键设计项必须在 `harness-collab/exec-plans` 中有 **TASK**（执行计划 §2.1、§3）。
- Plan 映射：`harness-collab/exec-plans` 的每个 **TASK** 必须在 **§6 plan_vs_impl** 中有行，并映射到代码、测试或文档变更证据。
- 缺失映射时禁止直接进入实现阶段。

## 交付检查单

- 需求边界清晰
- 设计方案完整
- 执行计划可验收
- 代码实现与计划一致
- 测试覆盖关键路径
- 文档同步完成
- 映射关系检查通过（spec/design/plan）

## 标准验证命令

- 本地与 CI：**`mvn clean verify -Pharness-legacy`**（本仓库根 [`pom.xml`](../../../pom.xml) 虽默认激活 legacy，CI 仍显式传入以便阅读一致）；新项目/ enforce 阶段使用 `-Pharness-new`。
- 该命令覆盖：单元测试、集成测试、Checkstyle、SpotBugs、JaCoCo（legacy 下模板多为 `warn` 语义，new profile 可升级覆盖率 enforce）。

## 交付证据建议

- 执行命令与结果摘要
- `plan_vs_impl` 对照表
- API 文档与 `harness-collab/func.md` 同步记录
