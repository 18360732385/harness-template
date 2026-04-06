#!/usr/bin/env bash
# 弱自动化文档门禁提示：对比 base...head 的文件变更，在变更 Java 时提醒核对 func / 04-api-docs。
# 用法：
#   bash scripts/verify-doc-gates.sh <base_sha> [head_sha]
#   head 默认 HEAD。未传 base 且未设置 VERIFY_DOC_GATES_BASE 时跳过。
# 严格模式：export DOC_GATES_STRICT=1 时在未满足提示条件时返回非零（CI 默认不启用，见 ci-verify advisory 步骤）。
set -eu
root="$(cd "$(dirname "$0")/.." && pwd)"
cd "$root"

base="${VERIFY_DOC_GATES_BASE:-${1:-}}"
head="${2:-HEAD}"
if [[ -z "$base" ]]; then
  echo "SKIP: no base ref (pass base_sha as arg or set VERIFY_DOC_GATES_BASE)"
  exit 0
fi
if ! git rev-parse "$base^{commit}" >/dev/null 2>&1; then
  echo "SKIP: invalid base commit $base"
  exit 0
fi
if ! git rev-parse "$head^{commit}" >/dev/null 2>&1; then
  echo "SKIP: invalid head commit $head"
  exit 0
fi

changed="$(git diff --name-only "$base" "$head" || true)"
if [[ -z "$changed" ]]; then
  echo "OK: empty diff"
  exit 0
fi

if ! echo "$changed" | grep -q '^src/main/java/.*\.java$'; then
  echo "OK: no src/main/java changes"
  exit 0
fi

advisory=0
if ! echo "$changed" | grep -q '^harness-collab/func\.md$'; then
  echo "ADVISORY: src/main/java changed but harness-collab/func.md not in diff — confirm per quality-gates.md / func.md" >&2
  advisory=1
fi

if echo "$changed" | grep -qE '^src/main/java/.*/controller/.*\.java$'; then
  if ! echo "$changed" | grep -q '^harness-collab/04-api-docs/'; then
    echo "ADVISORY: controller layer Java changed but no harness-collab/04-api-docs/ paths in diff — confirm API doc sync" >&2
    advisory=1
  fi
fi

if [[ "$advisory" -eq 0 ]]; then
  echo "OK: doc gate advisories not triggered"
  exit 0
fi

exit "${DOC_GATES_STRICT:-0}"
