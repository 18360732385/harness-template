# cursor-rules（副本）

此目录下的 `*.mdc` 与仓库根目录 **`.cursor/rules/*.mdc` 内容一致**，用于将 `harness-collab/` 整包复制到其他项目时一并带走规则草稿。

- **编辑与定稿**：请在模板仓库根目录的 `.cursor/rules/` 修改，再执行 `Copy-Item .cursor/rules/*.mdc harness-collab/cursor-rules/`（PowerShell）同步本目录。
- **文内「本目录」**：`.mdc` 文件中的「本目录」指 **仓库根的 `.cursor/rules`**，而非 `harness-collab/cursor-rules`。
