# Docker 部署笔记

## 1. Dockerfile 运行

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

## 3. Docker 加密认证

开放 2375 接口会导致服务器的安全漏洞，因此只可以在测试环境用。  
生产环境需使用加密的 tcp 连接，以 Https 的方式与客户端连接。

```shell
# 创建 ca 文件夹，存放 CA 私钥和公钥
mkdir -p /usr/local/ca
cd /usr/local/ca
# 生成 CA 私钥和公钥
openssl genrsa -aes256 -out ca-key.pem 4096
openssl req -new -x509 -days 365 -key ca-key.pem -sha256 -out ca.pem
# 生成 server-key.pem
openssl genrsa -out server-key.pem 4096
# CA 签署公钥（本地局域网）
openssl req -subj "/CN=192.168.207.129" -sha256 -new -key server-key.pem -out server.csr
# 配置白名单，允许任何拥有证书的ip访问
echo subjectAltName = IP:192.168.207.129,IP:0.0.0.0 >> extfile.cnf
# 密钥仅用于服务器身份验证
echo extendedKeyUsage = serverAuth >> extfile.cnf
# 生成签名证书
openssl x509 -req -days 365 -sha256 -in server.csr -CA ca.pem -CAkey ca-key.pem \ -CAcreateserial -out server-cert.pem -extfile extfile.cnf
# 生成客户端 key.pem
openssl genras -out key.pem 4096
openssl req -subj '/CN=client' -new -key key.pem -out client.csr
# 使密钥适合客户端身份验证
echo extendedKeyUsage = clientAuth >> extfile.cnf
echo extendedKeyUsage = clientAuth > extfile-client.cnf
# 生成签名证书
openssl x509 -req -days 365 -sha256 -in client.csr -CA ca.pem -CAkey ca-key.pem \ -CAcreateserial -out cert.pem -exffile extfile-client.cnf
# 删除多余文件
rm -v client.csr server.csr extfile.cnf extfile-client.cnf
# 修改密钥权为只读权限
chmod -v 0400 ca-key.pem key.pem server-key.pem
chmod -v 0444 ca.pem server.cert.pem cert.pem
# 归集服务器证书
cp server-*.pem /etc/docker
cp ca.pem /etc/dpcker
# 修改 docker 配置
vim /lib/systemd/system/docker.service
# 注释手动添加 ExecStart 内容，添加如下
ExecStart=/usr/bin/dockerd --tlsverify --tlscacert=/usr/local/ca/ca.pem --tlscert=/usr/local/ca/server-cert.pem --tlskey=/usr/local/ca/server-key.pem -Htcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
# 重启 docker 服务
systemctl daemon-reload
systemctl restart docker
# 开放2375端口
/sbin/iptables -I INPUT -p tcp --dport 2375 -j ACCEPT
# 重启 docker
systemctl restart docker
# 将 /usr/local/ca 目录下的 ca.pem,ca-key.pem,cert.pem,key.pem 复制到本地
# 修改 pom 文件docker地址为  https
```






