# AI 交付作战手册（plan_vs_impl）

## 核心原则

- 先规格、后设计、再计划、最后实现。
- 每个实现动作必须在计划中有映射。
- 每次代码变更都要有验证证据。

## 标准链路

`product-specs -> design-docs -> exec-plans -> implementation -> tests -> review`

## AI 任务执行规范

1. 读取 `func.md`，优先复用。
2. 读取相关规格与设计文档。
3. 输出或更新执行计划。
4. 按计划逐步编码与测试。
5. 同步更新 API 与功能文档。

## spec -> design -> plan 映射检查

- Spec 映射：`product-specs` 中每个目标必须在 `design-docs` 有对应设计。
- Design 映射：`design-docs` 中每个关键设计项必须在 `exec-plans` 有可执行任务。
- Plan 映射：`exec-plans` 的每个任务必须映射到代码、测试或文档变更证据。
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

- 本地与 CI 统一使用：`mvn clean verify`
- 该命令覆盖：单元测试、集成测试、Checkstyle、SpotBugs、JaCoCo（默认 `warn`，可升级 `enforce`）

## 交付证据建议

- 执行命令与结果摘要
- `plan_vs_impl` 对照表
- API 文档与 `func.md` 同步记录
