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

## 交付检查单

- 需求边界清晰
- 设计方案完整
- 执行计划可验收
- 代码实现与计划一致
- 测试覆盖关键路径
- 文档同步完成

## 标准验证命令

- 本地与 CI 统一使用：`mvn clean verify`
- 该命令覆盖：单元测试、集成测试、Checkstyle、SpotBugs、ArchUnit、JaCoCo（默认 `warn`，可升级 `enforce`）
