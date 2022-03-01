# Durian

## Common

**Common** 是一个 Java 应用环境中使用的常用编程组件库，包含：

* [Json](https://github.com/njdi/durian/wiki/Durian-Common-Json)
* [Bean]()
* [Uuid]()

## Xbatis

[Xbatis](https://github.com/njdi/durian/wiki/Xbatis%EF%BC%9ASpringBoot-%E6%95%B0%E6%8D%AE%E7%AE%A1%E7%90%86%E6%A1%86%E6%9E%B6) 是一个 SpringBoot 应用环境中使用的数据管理框架，它基于 MyBatis 实现，支持 MySQL，可以使用更加 Java
的方式实现业务逻辑中的 CRUD 操作。

## Config

[Config](https://github.com/njdi/durian/wiki/Config%EF%BC%9A%E7%94%A8%E6%88%B7%E5%B1%9E%E6%80%A7%E9%85%8D%E7%BD%AE%E6%A1%86%E6%9E%B6) 是一个用户属性配置框架，它基于 **Xbatis** 实现，可以在 **SpringBoot** 应用环境中使用，对于某一个配置属性：

* 属性可以有默认值
* 不同的用户可以有不同的属性值
* 可以动态更新属性值
