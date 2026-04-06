# modules（按业务模块存放 API 说明）

在此目录下为每个业务域建子目录，并在子目录内新增 `.md` 文件（每个文件可描述一个或多个相关接口，团队自行约定粒度）。

**示例（非强制）：**

```text
modules/
  order/
    README.md           # 可选：模块级说明
    create-order.md     # 接口文档，内容结构见 ../templates/api-doc-template.md
  user/
    profile.md
```

返回：[api-doc/README.md](../README.md)
