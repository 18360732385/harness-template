# cursor-rules（副本）

此目录下的 `*.mdc` 与仓库根目录 **`.cursor/rules/*.mdc` 内容一致**，用于将 `harness-collab/` 整包复制到其他项目时一并带走规则草稿。

## 编辑顺序（勿只改本目录）

1. 在模板仓库根目录的 **`.cursor/rules/`** 修改并定稿。  
2. 执行：`Copy-Item -Force .cursor/rules/*.mdc harness-collab/cursor-rules/`（PowerShell，在仓库根）。  
3. 将语义同步到 **`harness-collab/methodology/05-rules/*.md`**（镜像文档，见 [harness-collab/README.md](../README.md)）。

## 文内「本目录」

`.mdc` 文件中的「本目录」指 **仓库根的 `.cursor/rules`**，而非 `harness-collab/cursor-rules`。
