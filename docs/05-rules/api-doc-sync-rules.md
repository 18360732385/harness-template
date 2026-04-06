# API 文档同步规则

## 权威来源与同步规则

- 权威来源：`.cursor/rules/12-api-doc-sync-rules.mdc`
- 本文件定位：可复制的文档版本
- 维护原则：先改 `.mdc`，再同步本文件

## 必须同步的触发条件

- 入参结构变更
- 返回参数变更
- URL 变更
- 请求方式变更

## 文档要求

- 使用统一模板：`docs/04-api-standards/api-doc-template.md`
- data 为对象时，必须展开全部子字段（`data.xxx` / `data.obj.xxx`）
- 功能说明需覆盖核心业务逻辑和时序

## 交付门禁

不满足文档同步要求时，不得视为完成交付。
