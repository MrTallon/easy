# oAuth2 实战

基于 oAuth2 协议的密码模式，使用 Spring Security 框架，通过 RBAC 自定义认证，实现用户认证授权操作

所谓登录，就是拿令牌，客户端把令牌路由到对应的服务，服务再给到认证服务器，然后鉴权


[Spring Security oAuth2](https://tallon.ink/archives/f1d083c7.html#more)

## 运行方法

1. mysql和redis环境（可通过doc下docker-compose运行）
2. 将doc下sql文件导入easy-oauth数据库
3. 修改数据库连接池地址
4. doc下json文件导入postman
5. 测试各个请求接口
