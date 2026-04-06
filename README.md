# Java Harness Engineering 模板仓库

本仓库提供一套可直接复用的 Harness Engineering 工程基线，适用于：

- 新项目（Bootstrap）：快速从 0 到 1 建立规范驱动的 AI 研发流程。
- 历史项目（Retrofit）：在不破坏存量交付节奏下渐进接入门禁和文档体系。

## 目录总览

- `AGENTS.md`：AI 协作主协议（需求、设计、计划、实现、测试、复盘）。
- `func.md`：功能资产登记（变更前先检索复用）。
- `docs/`：治理、架构、工程流程、AI 工作流、API 标准。
- `docs/05-rules/`：可直接复用的 user/project rules 文本版本。
- `product-specs/`：产品规格文档模板与实例。
- `design-docs/`：技术设计文档模板与实例。
- `exec-plans/`：执行计划模板与实例。
- `.cursor/rules/`：User Rules 与 Java Project Rules。
- `config/`：Checkstyle 与 SpotBugs 配置。
- `src/main/java/.../controller|service|domain|repository`：分层包骨架占位。
- `src/test/java/`：测试根目录（本模板无强制示例类；按业务补充单测与集成测试）。

## 仓库根目录结构（当前）

下列树状结构列出**当前仓库**各目录及主要文件（模板快照；**不含** `.git/` 与本地 **`target/`**（Maven 构建产出，一般不入库）。若本地执行过 `mvn` 构建，根下会出现 `target/`，可整体忽略。

```text
├── .cursor/                                                      # Cursor IDE 配置与规则
│   └── rules/                                                    # 持久化 Rules（.mdc，供 AI 与团队对齐）
│       ├── 01-user-rules.mdc                                    # 用户协作：需求/设计/计划/测试与文档门禁
│       ├── 10-java-project-rules.mdc                            # Java 工程：分层、编码、可测试性
│       ├── 11-testing-and-quality-rules.mdc                     # 测试策略与质量门禁（verify、JaCoCo 等）
│       └── 12-api-doc-sync-rules.mdc                             # API 变更时文档与 func.md 同步
├── .github/                                                      # GitHub 仓库级配置
│   └── workflows/                                                # GitHub Actions 工作流目录
│       └── ci-verify.yml                                        # CI：Maven verify / 矩阵档等（见文件内说明）
├── config/                                                       # 质量工具本地配置（不依赖默认随包路径）
│   ├── checkstyle/                                               # Checkstyle 规则集目录
│   │   ├── checkstyle.xml                                       # 默认 Checkstyle 配置（与 pom 绑定）
│   │   └── checkstyle-strict.xml                                # 更严格规则集（可按 profile 切换）
│   └── spotbugs/                                                 # SpotBugs 辅助配置
│       └── exclude.xml                                          # 误报/基线类排除清单（存量渐进治理用）
├── design-docs/                                                  # 技术设计文档（实例与模板）
│   └── templates/                                                # 设计文档模板目录
│       └── template.md                                          # 标准技术设计文档骨架
├── docs/                                                         # 可读文档：治理、架构、流程、规范
│   ├── 00-governance/                                            # 治理：门禁、基线、工具模板说明
│   │   ├── legacy-baseline.md                                   # 历史项目接入基线与阶段策略
│   │   ├── quality-gates.md                                      # 质量门禁分档（observe/warn/enforce）
│   │   └── quality-tooling-templates.md                         # Checkstyle/SpotBugs/JaCoCo 等说明与引用
│   ├── 01-architecture/                                          # 架构约束与分层约定
│   │   └── architecture-constraints.md                          # 分层边界、依赖方向（文档约定；无内置自动结构测试）
│   ├── 02-engineering/                                           # 工程实践与改造手册
│   │   ├── dev-workflow.md                                       # 本地开发、分支与合入前置检查
│   │   └── retrofit-playbook.md                                  # 存量系统渐进接入 Harness 的步骤
│   ├── 03-ai-workflow/                                           # AI 辅助交付流程
│   │   └── ai-delivery-playbook.md                               # 从需求到复盘的可复制工作流
│   ├── 04-api-standards/                                        # 对外/对内接口文档规范
│   │   └── api-doc-template.md                                  # 接口说明模板（含 data 子字段展开要求）
│   └── 05-rules/                                                 # 与 .cursor/rules 对齐的 Markdown 副本（可复制到 docs）
│       ├── api-doc-sync-rules.md                                 # API 文档同步规则（文本版）
│       ├── java-project-rules.md                                  # Java 分层与编码规则（文本版）
│       ├── testing-and-quality-rules.md                          # 测试与质量规则（文本版）
│       └── user-rules.md                                         # 用户协作规则（文本版）
├── exec-plans/                                                    # 执行计划：任务分解与验收条目
│   └── templates/                                                # 执行计划模板目录
│       └── template.md                                          # 单计划文档骨架（映射需求/设计/验收）
├── product-specs/                                                 # 产品/需求规格
│   └── templates/                                                # 规格模板目录
│       ├── retrofit-template.md                                  # 历史项目改造类需求模板
│       └── template.md                                          # 通用产品规格模板
├── src/                                                          # 源码与测试根目录（Maven 标准布局）
│   ├── main/                                                     # 主代码与资源
│   │   └── java/                                                 # Java 源码根
│   │       └── com/
│   │           └── example/
│   │               └── harness/                               # 示例根包（可整体替换）
│   │                   ├── controller/                         # 【分层】入站适配
│   │                   │   └── package-info.java
│   │                   ├── domain/                             # 【分层】领域
│   │                   │   └── package-info.java
│   │                   ├── repository/                         # 【分层】出站适配
│   │                   │   └── package-info.java
│   │                   └── service/                            # 【分层】应用服务
│   │                       └── package-info.java
│   └── test/                                                     # 测试根（本模板默认无示例测试类，按业务补充）
│       └── java/                                                 # 测试 Java 根（建议与 main 包结构对称）
├── .gitignore                                                    # Git 忽略规则（构建产物、IDE 文件等）
├── AGENTS.md                                                     # AI 协作主协议（闭环流程与门禁）
├── func.md                                                       # 功能资产清单（变更前检索、变更后登记）
├── pom.xml                                                       # Maven 工程定义：父 POM、插件、profiles、属性
└── README.md                                                     # 本文件：仓库说明与快速上手
```

（若本地已 `git init`，还会有 `.git/` 目录；上表未逐一枚举其他编辑器或 OS 产生的隐藏/临时文件。）

## `src` 目录建议结构（建议）

以下为**建议**采用的 Java 工程布局，便于与本模板中的分层约定（见 `docs/01-architecture/architecture-constraints.md`）一致；落地时可将 `<根包>` 替换为实际包名（如 `com.company.project`）。

```text
src/                               # Maven 标准源码根（main 运行代码 + test 测试代码）
├── main/                          # 生产环境构建所需的代码与资源
│   ├── java/                      # Java 源码目录
│   │   └── <根包>/                # 你的应用根包（与集团/域名约定一致）
│   │       ├── controller/        # 【建议】控制器与入站适配（HTTP 等）
│   │       ├── service/           # 【建议】业务编排与用例实现
│   │       ├── domain/            # 【建议】领域模型、领域规则
│   │       └── repository/        # 【建议】持久化、外部系统适配（Mapper/Client 等）
│   └── resources/                 # 【建议】application.yml、静态资源等（按需）
└── test/                          # 测试源码与资源（默认与 main 对称）
    └── java/                      # 测试用 Java 源码目录
        └── <根包>/                # 【建议】与 main 使用同一根包，便于测试组织
            └── ...                # 【建议】按业务或分层划分子包，放单测与集成测试（需自动结构校验时可自行引入 ArchUnit 等）
```

## 推荐使用流程

1. 在 `product-specs/` 编写需求规格。
2. 在 `design-docs/` 输出技术设计。
3. 在 `exec-plans/` 拆分可执行计划。
4. 按 `AGENTS.md` 流程执行开发与测试。
5. 通过质量门禁（Checkstyle、SpotBugs、测试、JaCoCo 等；分层依赖依文档与评审落实）。
6. 同步 API 与功能文档（`docs/04-api-standards`、`func.md`）。

## 模板使用入口导航

### 第一步：明确规则与流程
- 先读：`AGENTS.md`
- 再读：`.cursor/rules/*.mdc`（权威规则）
- 参考：`docs/05-rules/*`（可复制副本）

### 第二步：产出三大核心文档
- 需求规格：`product-specs/templates/template.md`
- 技术设计：`design-docs/templates/template.md`
- 执行计划：`exec-plans/templates/template.md`

### 第三步：执行与验收
- 研发流程：`docs/02-engineering/dev-workflow.md`
- AI 交付：`docs/03-ai-workflow/ai-delivery-playbook.md`
- 门禁治理：`docs/00-governance/quality-gates.md`
- 统一验证：`mvn clean verify`

### 第四步：新老项目分档
- 历史项目：`mvn clean verify -Pharness-legacy`
- 新项目：`mvn clean verify -Pharness-new`
- 可选安全扫描：`mvn clean verify -Psecurity-scan`

## 质量门禁

- 静态检查：Checkstyle、SpotBugs
- 分层约定：`docs/01-architecture/architecture-constraints.md`（代码评审与治理；本模板不设自动依赖方向测试）
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
- 历史项目接入：先对齐文档与规则，再分阶段开启门禁，结合 `docs/00-governance/legacy-baseline.md` 管理豁免。
- 中性原则：模板只提供工程能力与流程，不包含任何业务域模型与业务逻辑。

## 说明

- 当前仓库已包含可直接执行的正式配置：`.cursor/rules/*.mdc`、`pom.xml`、`config/*`、`src/main/java/*`（测试代码按业务在 `src/test/java/` 补充）。
- `.cursor/rules/*.mdc` 是规则权威来源，`docs/05-rules/` 与 `docs/00-governance/quality-tooling-templates.md` 为可复制模板副本。
