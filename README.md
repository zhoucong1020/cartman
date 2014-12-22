Cartman
=======

##Cartman是什么？
---
Cartman是java语言中用于实现Restful服务的简便框架，
能够使用简短的代码创建Restful服务，并且内置集成了自动化文档、自动化测试、声明式结果缓存等多种功能，
可以使用一套统一的注解和统一的编程范式创建Restful服务。

更重要的是，Cartman并不依赖任何应用容器启动，Cartman依赖netty实现了nio的服务容器，
您可以像启动普通java程序一样启动Cartman。

##起步
```
public static void main(String[] args) {
    Cartman.get("/hello", (req, res) -> res.body("Hello World"));
}

//if static import
public static void main(String[] args) {
    get("/hello", (req, res) -> res.body("Hello World"));
}
```

Cartman支持使用lambda表达式创建Restful服务，可以快速实现服务接口，但您必须自行处理参数读取和结果写入。

```
package cartman.test.service;

@Service("/api")
public class HelloService {
    @ServiceMethod(value = "/user", method = RequestMethod.GET)
    public String hello(
        @Param("uid") String uid
    ) {
        return "hello user " + uid;
    }
}
```

```
public static void main(String[] args) {
    Cartman.scan("cartman.test.service");
}
```

Cartman支持使用POJO形式创建Restful服务，可以实现参数自动反序列化并注入，结果自动序列化，并且支持自动文档生成。