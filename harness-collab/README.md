# AI 协作与方法论文档包（harness-collab）

本目录集中存放 **Harness 模板**中的内容（**按研发流程排序**的编号子目录 + 根目录 `AGENTS.md`、`func.md`）：

1. **AI/人协作输入输出**：`01-product-specs`、`02-design-docs`、`03-exec-plans`、`func.md`、`AGENTS.md`。
2. **方法论文档（可读模板）**：[`05-methodology/`](05-methodology/README.md)，含连续编号 **00–05**（治理、架构、工程、AI 流程、04 占位跳转、规则镜像）；**接口说明**见 [`04-api-docs/`](04-api-docs/README.md)。
3. **迁入老服务专用（零改业务代码）**：[`06-adapters/`](06-adapters/README.md)（冲突裁决条文、决策记录模板、可复制到 `.cursor/rules` 的片段）。

根目录 **不再** 保留单独的 `docs/`；若迁入已有项目且对方根目录已有 `docs/`，可只复制本目录下的子树（例如仅 `01-product-specs` + `07-cursor-rules`），避免覆盖对方整棵文档树。

## 规则与方法论文档：编辑顺序（防三份漂移）

本仓库存在三处与 Cursor 规则相关的内容，**修改时必须按顺序同步**：

1. **权威编辑**：仓库根 [`.cursor/rules/*.mdc`](../.cursor/rules/)。
2. **整包复制用副本**：在同一提交内执行（PowerShell 示例）  
   `Copy-Item -Force .cursor/rules/*.mdc harness-collab/07-cursor-rules/`
3. **Markdown 镜像**（供跨仓复制与阅读）：更新 [harness-collab/05-methodology/05-rules/](05-methodology/05-rules/) 下对应 `.md`（内容与 `.mdc` 对齐，可略去 YAML frontmatter；触发范围说明写在镜像文首）。

CI 会对 `harness-collab/03-exec-plans` 中非模板的计划文件做 **plan_vs_impl** 关键字检查（见根目录 [`scripts/verify-exec-plans.sh`](../scripts/verify-exec-plans.sh)）。

## func.md 权威路径约定

| 场景 | 建议 |
|------|------|
| **本模板仓库** | 以 `harness-collab/func.md` 为唯一功能资产表。 |
| **迁入已有项目且根目录已有 `func.md`** | 可保留根目录 `func.md` 为权威；在 `.cursor/rules` 与团队规范中统一「读取 func」路径，或合并到本目录 `func.md`。 |
| **多模块单体仓** | 在团队文档中写死单一权威路径，避免 AI 误读。 |

## 迁入其它 Git 仓库的步骤（建议）

1. 复制整个 `harness-collab/` 到目标仓库根目录（与目标 `README` 同级），或**按需**仅复制 `01-product-specs`、`02-design-docs`、`03-exec-plans`、`05-methodology` 等子目录。
2. **Cursor 规则**：将仓库根 `.cursor/rules/` 与本模板根 `.cursor/rules/` 对齐（也可将 `07-cursor-rules/*.mdc` 复制到 `.cursor/rules/`，**以模板源 `.mdc` 为准**）。
3. **可选**：在目标仓库根增加短 `AGENTS.md` 入口，链向 `harness-collab/AGENTS.md`。
4. 目标仓库若需治理/架构/API 模板，可复制 `05-methodology/`；若仅需 AI 工作流，可跳过 `05-methodology/00-governance` 等子目录。
5. **历史规范可能与 harness 冲突时**：复制 [`06-adapters/`](06-adapters/README.md) 并按其中步骤粘贴条文、登记 `legacy-baseline`、可选增加 Cursor 规则片段（**不修改业务源码**）。

## 本目录结构（按流程）

| 路径 | 说明 |
|------|------|
| `AGENTS.md` | 完整 AI 协作协议与输出物约定 |
| `func.md` | 功能资产总表（建议任务开始前先读） |
| `01-product-specs/` | 产品/需求规格模板与实例 |
| `02-design-docs/` | 技术设计模板与实例 |
| `03-exec-plans/` | 执行计划与验收模板 |
| `04-api-docs/` | 接口文档（`templates/` + `modules/`，按模块），见 [04-api-docs/README.md](04-api-docs/README.md) |
| `05-methodology/` | 方法论文档 00–05（见 [05-methodology/README.md](05-methodology/README.md)） |
| `06-adapters/` | 老服务迁入：条文与决策模板（见 [06-adapters/README.md](06-adapters/README.md)） |
| `07-cursor-rules/` | `.mdc` 副本；**权威编辑**仍为仓库根 `.cursor/rules` |

## 路径映射（历史书签）

| 旧路径 | 新路径 |
|--------|--------|
| `/docs/00-governance/` … | `/harness-collab/05-methodology/00-governance/` … |
| `/docs/01-architecture/` … | `/harness-collab/05-methodology/01-architecture/` … |
| `/docs/02-engineering/` … | `/harness-collab/05-methodology/02-engineering/` … |
| `/docs/04-api-standards/` … | 接口正文：`/harness-collab/04-api-docs/`；占位：`/harness-collab/05-methodology/04-api-standards/README.md` |
| `/harness-collab/docs/03-ai-workflow/` | `/harness-collab/05-methodology/03-ai-workflow/` |
| `/harness-collab/docs/05-rules/` | `/harness-collab/05-methodology/05-rules/` |
| `harness-collab/product-specs` 等（无编号） | `harness-collab/01-product-specs` … `07-cursor-rules`（见上表） |
| `harness-collab/api-doc` | `harness-collab/04-api-docs` |

返回仓库根：[README.md](../README.md)
