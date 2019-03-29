# 多表关系映射

## 目录说明

* **simple**: 单表操作，通过 Spring Data JPA 进行 CRUD 操作。
* **one-to-one**: 一对一
* **one-to-one-shared-pk**: 一对一，子表主外键共享
* **one-to-many**: 一对多
* **many-to-many**: 多对多
* **many-to-many-extra-columns**: 多对多，关联表含扩展字段

> 以上目录，各自独立。

## 环境约定

* JDK 1.8
* Intellij IDEA ce 2018.3
* Maven 3.6.0
* Spring Boot 2.1.3.RELEASE
* Spring Data JPA, H2, Lombok

## 数据库

当前使用 H2 内存数据库，配置方式如下：

```sql
## jpa settings
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.show-sql=true
```

如需使用实体数据库，可参考如下：

```sql
## datasource settings
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.password=demo
spring.datasource.url=jdbc:h2:tcp://127.0.0.1/~/jpa-h2
spring.datasource.username=demo
## jpa settings
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=false
spring.jpa.show-sql=true
```
