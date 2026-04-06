# scripts

| 脚本 | 说明 |
|------|------|
| [`verify-exec-plans.sh`](verify-exec-plans.sh) | 校验 `harness-collab/03-exec-plans` 中非 `templates/` 的 `.md` 是否包含 `plan_vs_impl` 关键字；CI 默认在 `harness-legacy` + JDK 17 任务中调用一次。 |
| [`verify-doc-gates.sh`](verify-doc-gates.sh) | **弱提示**：对比 `base` 与 `head` 提交间 diff；若变更 `src/main/java` 但未改 `harness-collab/func.md`、或变更 `controller` 但未改 `04-api-docs/`，打印 ADVISORY。传参：`bash scripts/verify-doc-gates.sh <base_sha> [head_sha]`。`DOC_GATES_STRICT=1` 时失败退出。PR 上 CI 以 advisory 步骤调用（`continue-on-error`）。 |

Markdown 全库链接检查可自行接入 [lychee](https://github.com/lycheeverse/lychee) 等工具；本仓库暂不内置。
