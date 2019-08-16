## 表结构

![Table](simple.png "表结构")

## POST 请求

```
# 创建
## 初始化
http://localhost:8080/init

# 删除
## 删除全部
http://localhost:8080/deleteAll

## 删除 byID
http://localhost:8080/delete
    - id: 1

# 修改
## 书名重命名
http://localhost:8080/rename
    - id: 1
    - name: Spring in Action 5th

# 查询
## 查询全部
http://localhost:8080/findAll

## 按书名查找 精确查找
http://localhost:8080/find
    - name: Spring Boot in Action

## 按书名查找 模糊查找
http://localhost:8080/find/like
    - name: in

## 按书名查找 startsWith
http://localhost:8080/find/startsWith
    - name: Spring

## 按书名查找 模糊查找 & Top5
http://localhost:8080/find/top5
    - name: in
```

## 参考

* https://spring.io/guides/gs/accessing-data-jpa/
* https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/index.html
