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

## 可选横切包（与 `README.md` 中 `src` 建议结构一致）

以下为常见工程化子包，**非强制**，与四层分层并列于同一根包下即可：

- **启动类**：`<根包>/<AppName>Application.java`（Spring Boot 入口）。
- **config**：`@Configuration`、Bean 装配、Web/WebClient 等框架扩展；可含与 `spring-boot-configuration-processor` 配合的 `*Properties`。
- **common**：跨层复用的常量、无状态工具、与协议相关的轻量模型（如统一响应封装）；**不得**承载领域规则或与具体业务用例强绑定。
- **exception**（可选）：业务异常类型、全局异常处理；也可统一放在 `controller` 子包，由团队选定一种方式贯穿全项目。

**依赖注意：** `common`、`config` 不应反向依赖 `controller` / `service` 的业务实现；全局异常处理器若依赖 `service`，仅用于编排错误码转换等薄逻辑时需控制耦合，避免将用例逻辑卷入横切层。

## 约束落地方式

- 编码规范：Checkstyle
- 缺陷扫描：SpotBugs
- 分层与依赖方向：代码评审、CR 与治理台账闭环落实（本模板**不默认**挂载自动结构测试）
- 可选扩展：若团队需要机器校验依赖方向，可自行引入 ArchUnit 等库并编写测试类，**非**本模板默认真伪路径；分步操作见 [**archunit-recipe.md**](archunit-recipe.md)。

## 例外管理

历史项目可在 `harness-collab/05-methodology/00-governance/legacy-baseline.md` 登记临时豁免，必须设置清退时间。
