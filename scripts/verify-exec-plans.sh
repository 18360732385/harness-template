#!/usr/bin/env bash
# 检查 harness-collab/exec-plans 下除 templates 外的 Markdown 是否包含 plan_vs_impl 区块（轻量提示，非自然语言解析）。
set -eu
shopt -s globstar nullglob
root="$(cd "$(dirname "$0")/.." && pwd)"
plans_dir="$root/harness-collab/exec-plans"
if [[ ! -d "$plans_dir" ]]; then
  echo "SKIP: no $plans_dir"
  exit 0
fi
any=0
for f in "$plans_dir"/**/*.md; do
  [[ -f "$f" ]] || continue
  if [[ "$f" == *"/templates/"* ]]; then
    continue
  fi
  any=1
  if ! grep -qi 'plan_vs_impl' "$f"; then
    echo "ERROR: missing 'plan_vs_impl' section in $f"
    exit 1
  fi
done
if [[ "$any" -eq 0 ]]; then
  echo "OK: no non-template exec plans under $plans_dir (skipped)"
fi
exit 0
