# Docker 部署笔记

## 1. Dockerfile

服务器配置好 Docker 环境后，
直接将 Springboot 打包好的 jar 包上传，
通过编写 Dockerfile 文件制作镜像

`vim Dockerfile`
 ```shell script
FROM java:8-alpine
ADD docker-1.0.0-SNAPSHOT.jar docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/docker.jar"]
 ```
```shell script
docker build -t docker .
docker run -d -p 8080:8080 --name docker-test docker
```

## 2. idea 集成 Docker

1. Docker 开启远程访问		

   ```shell
   # 修改Docker服务文件
   vim /lib/systemd/system/docker.service
   # 开发端口,修改 ExecStart 如下
   ExecStart=/usr/bin/Dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/Docker.sock
   # 重新加载配置文件
   systemctl daemon-reload
   # 重启服务
   systemctl restart Docker.service
   #查看端口是否开启(如远程服务器需配置入站出站规则)
   netstat -nlptf1
   ```
   
2. 绑定 Docker 命令到 maven 的各个阶段(详见代码案例)

    把Docker分为build、tag、push，然后分别绑定maven的package、deploy阶段。
    
    要执行mvn deploy就可以完成整个build、tag、push操作，  
    执行mvn package就只完成build、tag操作。

    集成之后的maven想要跳过Docker的某个阶段时
    
    - -DskipDockerBuild    跳过 build 阶段
    - -DskipDockerTag    跳过 tag 阶段
    - -DskipDockerPush    跳过 push 阶段
    - -DskipDocker  跳过整个阶段

## 4.idea 整合 Docker 加密认证

开放 2375 接口会导致服务器的安全漏洞，因此只可以在测试环境用。  
生产环境需配置安全策略。

日后再表






