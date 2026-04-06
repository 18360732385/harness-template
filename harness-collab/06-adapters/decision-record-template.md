# 规范冲突决策记录（单条模板）

> 每行冲突一条记录；可复制本文件为 `adapters/decisions/YYYY-MM-DD-short-title.md` 或纳入团队 Wiki。

| 字段 | 填写 |
|------|------|
| **日期** | YYYY-MM-DD |
| **主题** | 简短标题 |
| **冲突描述** | 历史规范 / 老代码习惯是什么；`harness-collab` 对应要求是什么 |
| **决议** | `采纳 harness` / `暂时保留历史` / `折中（说明）` |
| **若保留历史** | 对应 `legacy-baseline` 条目 ID 或拟新增的 baseline 摘要；**清退时间或条件** |
| **影响范围** | 模块 / 接口 / 文档 |
| **AI 提示摘要** | 一句话写入任务模板或 PR 说明，避免误用 |

**示例（决议为暂时保留历史时）：**

- 决议：暂时保留历史（仅 X 模块包结构不按 `architecture-constraints` 拆四层）
- baseline：已登记 `legacy-baseline` §…；清退条件：下一次大版本重构 Y 完成前须移除
