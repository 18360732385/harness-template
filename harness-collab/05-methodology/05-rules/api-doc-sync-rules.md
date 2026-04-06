# API 文档同步规则

## 权威来源与同步

- **勿在本文件单独定稿**：权威来源为仓库根 **`.cursor/rules/12-api-doc-sync-rules.mdc`**；同步流程见 [`harness-collab/README.md`](../../README.md) 中「Cursor 规则：单一事实来源与同步」。
- 本文件为 Markdown 导出，供无 Cursor 环境阅读或与 `05-methodology` 一并分发。

## 必须同步的触发条件

- 入参结构变更
- 返回参数变更
- URL 变更
- 请求方式变更
- 鉴权方式、限流规则、错误码语义变更
- 接口业务语义变更（即使字段不变）

## 文档要求

- 使用统一模板：`harness-collab/04-api-docs/templates/api-doc-template.md`
- 接口正文目录：`harness-collab/04-api-docs/modules/`（按模块）
- data 为对象时，必须展开全部子字段（`data.xxx` / `data.obj.xxx`）
- 功能说明需覆盖核心业务逻辑和时序

## 与功能资产联动

- 接口新增/下线时，必须同步更新 `harness-collab/func.md`（或团队约定的 func 权威路径）。
- 接口语义变化时，必须更新上述 func 中对应方法说明与影响范围。

## 交付门禁

不满足文档同步要求时，不得视为完成交付。
