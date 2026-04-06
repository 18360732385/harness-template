# AI 协作归集包（harness-collab）

本目录集中存放 **与 AI/人协作相关的输入输出**：需求规格、技术设计、执行计划、功能资产表、AI 交付手册，以及与 Cursor Rules 对齐的 Markdown 镜像。工程类文档（治理、架构、API 标准等）仍在仓库根目录的 `docs/` 下，便于将本目录 **整体复制到存量仓库** 时减少对现有 `docs/`、`src/` 的覆盖冲突。

## func.md 权威路径约定

| 场景 | 建议 |
|------|------|
| **本模板仓库** | 以 `harness-collab/func.md` 为唯一功能资产表；根目录不再放置 `func.md`。 |
| **迁入已有项目且根目录已有 `func.md`** | 可保留根目录 `func.md` 为权威；此时请在本仓库的 `.cursor/rules` 与团队规范中把「读取 func」统一改为根路径，或将根目录内容逐步迁移到 `harness-collab/func.md` 并删除重复。 |
| **多模块单体仓** | 任选「单文件总表」或「每模块子目录一份 func」之一，在团队文档中写死一处，避免 AI 误读旧路径。 |

## 迁入其它 Git 仓库的步骤（建议）

1. 复制整个 `harness-collab/` 到目标仓库根目录（与 `README` 同级）。
2. **Cursor 规则**：将目标仓库根目录下 `.cursor/rules/` 与本模板根目录 `.cursor/rules/` 对齐（可把 `harness-collab/cursor-rules/*.mdc` 复制到 `.cursor/rules/`，**以源模板 `.mdc` 为准并随模板更新同步**）。
3. **可选**：在目标仓库根增加短 [`AGENTS.md`](../AGENTS.md) 式入口，链向 `harness-collab/AGENTS.md`。
4. 目标仓库若已有 `docs/`，无需合并本模板的 `docs/00-governance` 等，除非你需要工程治理文档；仅复制 `harness-collab` 即可最小侵入接入 AI 工作流。

## 本目录结构（简）

| 路径 | 说明 |
|------|------|
| `AGENTS.md` | 完整 AI 协作协议与输出物约定 |
| `func.md` | 功能资产总表（路径约定见上文） |
| `product-specs/` | 产品/需求规格模板与实例 |
| `design-docs/` | 技术设计模板与实例 |
| `exec-plans/` | 执行计划与验收模板 |
| `docs/03-ai-workflow/` | AI 交付作战手册 |
| `docs/05-rules/` | Rules 的 Markdown 镜像（与 `.cursor/rules` 同步维护） |
| `cursor-rules/` | `.mdc` 副本，便于整包带走；**权威编辑位置仍为仓库根 `.cursor/rules`** |

## 路径映射（旧 → 新）

若你看到旧文档或书签仍使用下列路径，请改为右侧新路径：

| 旧路径 | 新路径 |
|--------|--------|
| `/AGENTS.md`（完整正文） | `/harness-collab/AGENTS.md`（根目录 `AGENTS.md` 仅为入口） |
| `/func.md` | `/harness-collab/func.md` |
| `/product-specs/` | `/harness-collab/product-specs/` |
| `/design-docs/` | `/harness-collab/design-docs/` |
| `/exec-plans/` | `/harness-collab/exec-plans/` |
| `/docs/03-ai-workflow/` | `/harness-collab/docs/03-ai-workflow/` |
| `/docs/05-rules/` | `/harness-collab/docs/05-rules/` |
