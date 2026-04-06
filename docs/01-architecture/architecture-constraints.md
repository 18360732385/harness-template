# 架构约束（Java 后端）

## 分层模型

- interfaces（controller）：入站适配层，处理协议与参数校验。
- application（service）：应用编排层，负责业务流程。
- domain（domain/model）：核心业务规则层。
- infrastructure（repository/client/mapper）：出站适配层，负责存储和外部系统访问。

## 依赖方向

- 允许：`interfaces -> application -> domain`
- 允许：`infrastructure -> domain`（实现领域端口/仓储接口）
- 禁止：`application -> interfaces`
- 禁止：`domain -> infrastructure`

## 包结构建议

- `com.company.project.interfaces`
- `com.company.project.application`
- `com.company.project.domain`
- `com.company.project.infrastructure`

## 约束落地方式

- 编码规范：Checkstyle
- 缺陷扫描：SpotBugs
- 架构测试：ArchUnit

## 例外管理

历史项目可在 `docs/00-governance/legacy-baseline.md` 登记临时豁免，必须设置清退时间。
