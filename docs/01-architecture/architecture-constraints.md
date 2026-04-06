# 架构约束（Java 后端）

## 分层模型

- controller：Web 控制器层，处理协议与参数校验。
- service：业务服务层，负责业务流程编排。
- domain（domain/model）：核心业务规则层。
- repository（repository/client/mapper）：出站适配层，负责持久化与外部系统访问。

## 依赖方向

- 允许：`controller -> service -> domain`
- 允许：`repository -> domain`（实现领域端口/仓储接口）
- 禁止：`service -> controller`
- 禁止：`domain -> repository`

## 包结构建议

- `com.company.project.controller`
- `com.company.project.service`
- `com.company.project.domain`
- `com.company.project.repository`

## 约束落地方式

- 编码规范：Checkstyle
- 缺陷扫描：SpotBugs
- 分层与依赖方向：代码评审、CR 与治理台账闭环落实（本模板**不默认**挂载自动结构测试）
- 可选扩展：若团队需要机器校验依赖方向，可自行引入 ArchUnit 等库并编写测试类，**非**本模板默认真伪路径

## 例外管理

历史项目可在 `docs/00-governance/legacy-baseline.md` 登记临时豁免，必须设置清退时间。
