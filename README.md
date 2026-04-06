# Java Harness Engineering 模板仓库

本仓库提供一套可直接复用的 Harness Engineering 工程基线，适用于：

- 新项目（Bootstrap）：快速从 0 到 1 建立规范驱动的 AI 研发流程。
- 历史项目（Retrofit）：在不破坏存量交付节奏下渐进接入门禁和文档体系。

## 目录总览

- `AGENTS.md`：入口，链向完整协议 `harness-collab/AGENTS.md`。
- `harness-collab/`：AI 协作文档归集区（按研发流程：`01-product-specs`→`06-adapters`，另含根目录 `AGENTS.md`、`func.md`；其中 `05-methodology/` 为方法论 00–04）。迁入存量库时可按需整包或摘子目录复制；**Cursor 规则以仓库根 `.cursor/rules` 为准**。
- `.cursor/rules/`：Cursor 权威规则（`.mdc`）；复制到其它仓库时参见 `harness-collab/README.md`。
- `config/`：Checkstyle 与 SpotBugs 配置。
- `src/main/java/.../controller|service|domain|repository`：分层包骨架占位；落地时可按建议结构补充 `config`、`common` 等横切包。
- `src/test/java/`：测试根目录（本模板无强制示例类；按业务补充单测与集成测试）。

## 仓库根目录结构（当前）

下列树状结构列出**当前仓库**各目录及主要文件（模板快照；**不含** `.git/` 与本地 **`target/`**（Maven 构建产出，一般不入库）。若本地执行过 `mvn` 构建，根下会出现 `target/`，可整体忽略。

```text
├── .cursor/                                         # Cursor IDE 配置与规则
│   └── rules/                                       # 持久化 Rules（.mdc，供 AI 与团队对齐）
│       ├── 01-user-rules.mdc                        # 用户协作：需求/设计/计划/测试与文档门禁
│       ├── 10-java-project-rules.mdc                # Java 工程：分层、编码、可测试性
│       ├── 11-testing-and-quality-rules.mdc         # 测试策略与质量门禁（verify、JaCoCo 等）
│       └── 12-api-doc-sync-rules.mdc                # API 变更时文档与 func.md 同步
├── .github/                                         # GitHub 仓库级配置
│   └── workflows/                                   # GitHub Actions 工作流目录
│       └── ci-verify.yml                            # CI：Maven verify / 矩阵档等（见文件内说明）
├── config/                                          # 质量工具本地配置（不依赖默认随包路径）
│   ├── checkstyle/                                  # Checkstyle 规则集目录
│   │   ├── checkstyle.xml                           # 默认 Checkstyle 配置（与 pom 绑定）
│   │   └── checkstyle-strict.xml                    # 更严格规则集（可按 profile 切换）
│   └── spotbugs/                                    # SpotBugs 辅助配置
│       └── exclude.xml                              # 误报/基线类排除清单（存量渐进治理用）
├── harness-collab/                                  # AI 协作（按研发流程编号的子目录，可整体或按需复制）
│   ├── README.md                                    # 迁入说明、func 优先级、.mdc 复制方式
│   ├── AGENTS.md                                    # 完整 AI 协作协议
│   ├── func.md                                      # 功能资产总表
│   ├── 01-product-specs/                            # 需求规格模板与实例
│   │   └── templates/
│   ├── 02-design-docs/                             # 技术设计模板与实例
│   │   └── templates/
│   ├── 03-exec-plans/                               # 执行计划模板与实例
│   │   └── templates/
│   ├── 04-api-docs/                                 # 接口文档：templates/ + modules/（按模块）
│   ├── 05-methodology/                             # 方法论 00–04：治理、架构、工程、AI 流程、Rules Markdown 导出
│   │   ├── 00-governance/
│   │   ├── 01-architecture/
│   │   ├── 02-engineering/
│   │   ├── 03-ai-workflow/
│   │   └── 04-rules/                                # Rules 的 Markdown 导出（与根 .cursor/rules 同步）
│   └── 06-adapters/                                 # 老服务迁入条文与决策模板
├── src/                                             # 源码与测试根目录（Maven 标准布局）
├── AGENTS.md                                        # 协议入口 → harness-collab/AGENTS.md
├── pom.xml
└── README.md
```

（若本地已 `git init`，还会有 `.git/` 目录；上表未逐一枚举其他编辑器或 OS 产生的隐藏/临时文件。）

## `src` 目录建议结构（建议）

以下为**建议**采用的 Java / Spring Boot 工程布局：在 [`harness-collab/05-methodology/01-architecture/architecture-constraints.md`](harness-collab/05-methodology/01-architecture/architecture-constraints.md) 的分层（controller → service → domain，repository → domain）之上，增加常见的**横切包**（`config`、`common` 等）与**资源文件**约定。将 `<根包>` 替换为实际包名（如 `com.company.project`）。

**约定摘要：** `common`、`config` 仅放通用能力与装配代码，**不承载核心业务规则**；业务状态与规则仍在 `domain` / `service`。团队可将全局异常处理类放在 `exception` 子包，或归入 `controller`（二选一、保持全项目一致即可）。

```text
src/                                       
├── main/
│   ├── java/
│   │   └── <根包>/
│   │       ├── <AppName>Application.java      #  Spring Boot 启动类（如 OrderApplication.java）
│   │       ├── config/                        # 【建议】@Configuration、Bean 装配、框架扩展（Web/WebClient、异步线程池等）
│   │       │   ├── package-info.java          # 【可选】包职责说明（与 Checkstyle/团队规范一致时可保留）
│   │       │   └── ...                        # 如 *Configuration.java、*Properties.java（绑定 configuration-metadata 时配合 spring-boot-configuration-processor）
│   │       ├── common/                        # 【建议】跨层复用：常量、泛型工具、与协议相关的轻量模型（非领域实体）
│   │       │   ├── package-info.java      
│   │       │   ├── constant/                  # 【可选】错误码前缀、系统级常量（避免与业务枚举混放）
│   │       │   ├── util/                      # 【可选】无状态工具类
│   │       │   └── api/                       # 【可选】统一响应封装 ApiResult、分页请求包装等（仅表达协议，不含业务规则）
│   │       ├── exception/                     # 【可选】BusinessException、全局异常处理器 GlobalExceptionHandler 等
│   │       │   └── package-info.java      
│   │       ├── controller/                    # 【建议】HTTP 等入站适配、参数校验
│   │       │   └── package-info.java      
│   │       ├── service/                       # 【建议】用例编排与事务边界
│   │       │   └── package-info.java      
│   │       ├── domain/                        # 【建议】实体、值对象、领域服务、领域事件
│   │       │   └── package-info.java      
│   │       └── repository/                    # 【建议】JPA/MyBatis/Feign 等出站适配
│   │           └── package-info.java      
│   └── resources/                             #  配置文件与静态资源
│       ├── application.yml                    
│       ├── application-test.yml               
│       ├── application-dev.yml                
│       ├── application-prod.yml               
│       ├── static/                            # 【可选】静态资源根（前后端分离项目可空置）
│       ├── templates/                         # 【可选】服务端模板（Thymeleaf 等）
│       └── logback-spring.xml                 # 【可选】日志配置（需与 spring-boot-starter-logging 等搭配）
└── test/
    ├── java/
    │   └── <根包>/
    │       ├── ...                            # 【建议】与 main 对称子包；可按分层或业务域划分单测/集成测试
    │       └── support/                       # 【可选】测试构建器、随机数据、@TestConfiguration 等
    └── resources/
        ├── application-test.yml               # 【建议】测试环境配置（数据源内存化、端口随机等）
        └── data/                              # 【可选】测试数据集、JSON/SQL 片段
```

以上文件名可按团队习惯调整，但建议 **main / test 的 profile 与资源目录** 保持可预测命名，便于 CI 与本地一键运行。

## 推荐使用流程

1. 在 `harness-collab/01-product-specs/` 编写需求规格。
2. 在 `harness-collab/02-design-docs/` 输出技术设计。
3. 在 `harness-collab/03-exec-plans/` 拆分可执行计划。
4. 按 `AGENTS.md` / `harness-collab/AGENTS.md` 流程执行开发与测试。
5. 通过质量门禁（Checkstyle、SpotBugs、测试、JaCoCo 等；分层依赖依文档与评审落实）。
6. 同步 API 与功能文档（`harness-collab/04-api-docs`、`harness-collab/func.md`）。

## 模板使用入口导航

### 第一步：明确规则与流程
- 先读：`AGENTS.md` → `harness-collab/AGENTS.md`
- 再读：`.cursor/rules/*.mdc`（权威规则）
- 参考：`harness-collab/05-methodology/04-rules/*`（Markdown 副本）

### 第二步：产出三大核心文档
- 需求规格：`harness-collab/01-product-specs/templates/template.md`
- 技术设计：`harness-collab/02-design-docs/templates/template.md`
- 执行计划：`harness-collab/03-exec-plans/templates/template.md`

### 第三步：执行与验收
- 研发流程：`harness-collab/05-methodology/02-engineering/dev-workflow.md`
- AI 交付：`harness-collab/05-methodology/03-ai-workflow/ai-delivery-playbook.md`
- 门禁治理：`harness-collab/05-methodology/00-governance/quality-gates.md`
- 统一验证：`mvn clean verify`

### 第四步：新老项目分档
- 历史项目：`mvn clean verify -Pharness-legacy`
- 新项目：`mvn clean verify -Pharness-new`
- 可选安全扫描：`mvn clean verify -Psecurity-scan`

## 质量门禁

- 静态检查：Checkstyle、SpotBugs
- 分层约定：`harness-collab/05-methodology/01-architecture/architecture-constraints.md`（代码评审与治理；本模板不设自动依赖方向测试）
- 覆盖率：JaCoCo（模板默认 `warn` 语义，可按项目阶段切换 `enforce`）
- 文档门禁：API 变更文档同步、功能资产同步
- 统一命令：`mvn clean verify`
- CI：JDK 17/21 矩阵校验 + 可选 `security-scan`（OWASP Dependency-Check）

## 迁移策略（历史项目）

建议按阶段推进：`observe -> warn -> enforce`，逐步收敛历史遗留问题，避免一次性改造风险。

## 新项目使用建议

建议从 `warn` 启动，首个稳定迭代切换至 `enforce`，兼顾效率与质量。

- 历史项目建议：`mvn clean verify -Pharness-legacy`
- 新项目建议：`mvn clean verify -Pharness-new`

## 通用复用指南（模板定位）

- 新项目接入：直接复制本模板，优先启用 `mvn clean verify` 与 `.cursor/rules/*`。
- 历史项目接入：先对齐文档与规则，再分阶段开启门禁，结合 `harness-collab/05-methodology/00-governance/legacy-baseline.md` 管理豁免。
- 中性原则：模板只提供工程能力与流程，不包含任何业务域模型与业务逻辑。

## 说明

- 当前仓库已包含可直接执行的正式配置：`.cursor/rules/*.mdc`、`pom.xml`、`config/*`、`src/main/java/*`（测试代码按业务在 `src/test/java/` 补充）。
- **规则权威与两处同步流程**：见 [`harness-collab/README.md`](harness-collab/README.md) 中 **「Cursor 规则：单一事实来源与同步」**；工程工具说明见 `harness-collab/05-methodology/00-governance/quality-tooling-templates.md`。
