# scripts

| 脚本 | 说明 |
|------|------|
| [`verify-exec-plans.sh`](verify-exec-plans.sh) | 校验 `harness-collab/exec-plans` 中非 `templates/` 的 `.md` 是否包含 `plan_vs_impl` 关键字；CI 默认调用。 |

Markdown 全库链接检查可自行接入 [lychee](https://github.com/lycheeverse/lychee) 等工具；本仓库暂不内置。
