# API 文档模板

## 获取用户基本信息

**功能描述：** 详细描述接口的业务用途  
**接口地址：** /api/endpoint  
**请求方式：** GET/POST


## 变更记录
| 版本 | 类型 | 内容 | 操作人 | 更新时间 |
|---|---|---|---|---|
| v0.1 | 创建 | 创建接口 | 张三 | 2026-04-06 11:22:30 |
| v0.2 | 修改 | 新增过滤状态status | 张三 | 2026-04-08 13:21:05 |
| v0.3 | 作废 | 因需求变更，原接口逻辑不适用，作废接口 | 李四 | 2026-04-08 13:22:30 |


## 功能逻辑
通过状态，分页查询用户信息表user，获取用户信息集合。

```mermaid
sequenceDiagram
    participant Client
    participant Server
    Client->>Server: 请求数据
    Server-->>Client: 返回结果
```

### 请求参数

```json
{
  "page": 1,
  "page_size": 10,
  "status": "active"
}
```

| 参数名 | 类型 | 必填 | 说明 | 示例值 |
|---|---|---|---|---|
| page | int | 否 | 页码（默认 1） | 2 |
| page_size | int | 否 | 每页数量（默认 10） | 20 |
| status | string | 否 | 状态过滤 | active |

### 响应参数

```json
{
  "code": 0,
  "message": "获取用户基本信息成功",
  "data": {
    "user_id": 1,
    "username": "admin",
    "email": "admin@example.com",
    "status": "active"
  }
}
```

| 参数名 | 类型 | 必填 | 说明 | 示例值 |
|---|---|---|---|---|
| code | int | 是 | 响应码 | 0 |
| message | string | 是 | 响应消息 | 获取用户基本信息成功 |
| data | object | 是 | 响应数据 | |
| data.user_id | int | 是 | 用户 ID | 1 |
| data.username | string | 是 | 用户名 | admin |
| data.email | string | 是 | 邮箱 | admin@example.com |
| data.status | string | 是 | 用户状态 | active |


### 接口字段与表字段映射
| 接口字段 | 表字段 | 说明 | 操作 |
| --- | --- | --- | --- |
| user_id | user.user_id | 用户id | 查询 |
| username | user.username | 用户名称 | 查询 |
| email | user.email | 用户邮箱 | 查询 |



## 文档同步规则

以下任一变更发生时，必须同步更新 API 文档：

- 入参结构变更
- 返回参数变更
- URL 地址变更
- 请求方式变更
- 接口内部发生数据库表字段操作逻辑变更
