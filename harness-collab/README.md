# AI 协作与方法论文档包（harness-collab）

本目录集中存放 **Harness 模板**中的内容（**按研发流程排序**的编号子目录 + 根目录 `AGENTS.md`、`func.md`）：

1. **AI/人协作输入输出**：`01-product-specs`、`02-design-docs`、`03-exec-plans`、`func.md`、`AGENTS.md`。
2. **方法论文档**：[`05-methodology/`](05-methodology/README.md)（连续编号 **00–05**：治理、架构、工程、AI 流程、API 标准索引、规则说明的 Markdown 导出）；**接口 Markdown 正文**在 [`04-api-docs/`](04-api-docs/README.md)。
3. **迁入老服务辅助**：[`06-adapters/`](06-adapters/README.md)（冲突裁决条文、决策记录模板、可选 Cursor 规则片段）。

## Cursor 规则：单一事实来源与同步

修改规则时**只改一处源文件**，再同步到另外两处，避免三套内容不一致：

1. **权威来源**：仓库根 [`.cursor/rules/*.mdc`](../.cursor/rules/)。
2. **整包复制**：同一提交内执行（PowerShell，在仓库根）  
   `Copy-Item -Force .cursor/rules/*.mdc harness-collab/07-cursor-rules/`
3. **Markdown 导出**（便于无 Cursor 环境阅读或跨仓粘贴）：更新 [`05-methodology/05-rules/`](05-methodology/05-rules/) 下对应 `.md`（与 `.mdc` 语义对齐，可略去 YAML frontmatter）。

复制命令与「本目录」指代说明见 [`07-cursor-rules/README.md`](07-cursor-rules/README.md)；**勿**在三处各自写长段重复说明。

CI 对 `03-exec-plans` 中非模板 `.md` 检查 **plan_vs_impl**（[`scripts/verify-exec-plans.sh`](../scripts/verify-exec-plans.sh)）。

## func.md 权威路径约定

| 场景 | 建议 |
|------|------|
| **本模板仓库** | 以 `harness-collab/func.md` 为唯一功能资产表。 |
| **迁入已有项目且根目录已有 `func.md`** | 可保留根目录 `func.md` 为权威；在 `.cursor/rules` 与团队规范中统一「读取 func」路径，或合并到本目录 `func.md`。 |
| **多模块单体仓** | 在团队文档中写死单一权威路径，避免 AI 误读。 |

## 迁入其它 Git 仓库的步骤（建议）

1. 将本目录复制到目标仓库根（与目标 `README` 同级），或按需只复制 `01-product-specs`、`02-design-docs`、`03-exec-plans`、`05-methodology` 等。**若对方已有根目录 `docs/` 或自有文档树，请按需摘子目录复制，避免无意覆盖。**
2. **Cursor 规则**：对齐仓库根 `.cursor/rules/`（可将 `07-cursor-rules/*.mdc` 复制过去，**以模板 `.cursor/rules` 为源**）。
3. **可选**：在目标仓库根增加短 `AGENTS.md`，链接 `harness-collab/AGENTS.md`。
4. 仅需 AI 工作流时可跳过 `05-methodology/00-governance` 等子目录；需要治理/架构模板时复制完整 `05-methodology/`。
5. **与存量内部规范冲突时**：使用 [`06-adapters/`](06-adapters/README.md)，并登记 `legacy-baseline`（**不修改业务源码**）。

## 本目录结构（按流程）

| 路径 | 说明 |
|------|------|
| `AGENTS.md` | 完整 AI 协作协议与输出物约定 |
| `func.md` | 功能资产总表（建议任务开始前先读） |
| `01-product-specs/` | 产品/需求规格模板与实例 |
| `02-design-docs/` | 技术设计模板与实例 |
| `03-exec-plans/` | 执行计划与验收模板 |
| `04-api-docs/` | 接口文档（`templates/` + `modules/`），见 [04-api-docs/README.md](04-api-docs/README.md) |
| `05-methodology/` | 方法论文档 00–05，见 [05-methodology/README.md](05-methodology/README.md) |
| `06-adapters/` | 老仓库迁入条文与决策模板，见 [06-adapters/README.md](06-adapters/README.md) |
| `07-cursor-rules/` | `.mdc` 副本；编辑以根 `.cursor/rules` 为准 |

## 旧链接速查（外部书签）

若外部文档或旧 PR 仍使用早期路径，可按下表对照（子路径其余部分不变）：

| 若见到 | 请改为 |
|--------|--------|
| 仓库根 `docs/<本模板方法论路径>/…` | `harness-collab/05-methodology/<同上子路径>/…` |
| `harness-collab/docs/…`、`harness-collab/product-specs` 等无编号目录 | `harness-collab/05-methodology/…` 或 `01-product-specs`～`07-cursor-rules`（见上表） |
| `harness-collab/api-doc` | `harness-collab/04-api-docs` |

返回仓库根：[README.md](../README.md)
