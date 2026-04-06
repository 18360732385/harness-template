# API 文档（`harness-collab/04-api-docs`）

接口说明与 **`05-methodology/`** 中的流程、门禁说明分离：**具体 URL/入参/出参/变更** 在此目录维护（**API 标准与格式约定的正文仅维护在此处**），便于按模块高频提交；编写格式以 [`templates/api-doc-template.md`](templates/api-doc-template.md) 为准。

## 目录约定

| 路径 | 说明 |
|------|------|
| `templates/api-doc-template.md` | 单接口文档结构模板（勿删；可复制为新文件起点） |
| `modules/<业务模块>/` | 按模块划分的 `.md` 文件，例如 `modules/order/create-order.md` |

新增或变更接口时：在对应模块子目录下**新增或更新** Markdown，字段与表格要求见 Cursor 规则 **12-api-doc-sync**（`data` 对象须展开子字段等）。

## 与 func / 追溯

- [`harness-collab/func.md`](../func.md) 中「关联追溯」列建议填写本目录下具体路径，例：`04-api-docs/modules/order/create-order.md`。
- 需求与设计 ID（REQ/DES）仍按 [`AGENTS.md`](../AGENTS.md) 与 `01-product-specs` / `02-design-docs` 约定。

返回：[harness-collab/README.md](../README.md)
