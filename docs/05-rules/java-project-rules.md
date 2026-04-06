# Java Project Rules（可复制到 Project Rule）

## 权威来源与同步规则

- 权威来源：`.cursor/rules/10-java-project-rules.mdc`
- 本文件定位：便于复用到外部仓库的文档镜像
- 维护原则：以 `.mdc` 为准，本文件仅做同步副本

## 分层约束

- Controller 仅负责协议与参数校验，不写业务逻辑。
- Service 负责业务编排，不依赖 Controller。
- Domain 保持业务核心纯净，不依赖基础设施实现。
- Infrastructure 负责数据与外部系统访问，不反向影响应用层。

## 分层依赖示例

- 允许：`interfaces -> application -> domain`
- 允许：`infrastructure -> domain`
- 禁止：`application -> interfaces`
- 禁止：`domain -> infrastructure`

## 编码规范

- 遵循阿里巴巴《Java 开发手册》。
- 命名语义化，避免无意义常量，优先使用枚举表达业务状态。
- 方法保持单一职责，控制复杂度。
- 异常处理要可观测、可定位、可追踪。
- DTO/VO 与领域模型职责分离。
- 事务边界必须明确，幂等场景必须定义幂等策略。

## 注释规范

- 类注释包含：职责、作者、创建时间。
- 方法注释包含：功能、入参、出参、异常。
- 复杂代码块给出中文说明，强调“为什么”。

## 可测试性

- 核心业务具备单测。
- 跨模块调用具备集成测试。
- 缺陷修复必须补回归测试。

## 验收证据

- `mvn clean verify` 通过。
- 分层依赖无违规、关键规范无阻塞问题。
