# ELK基础部署过程

## 基础环境

Linux 安装好 Docker Compose 环境



## 前期准备

1. 修改系统内核参数
   1. `sysctl -w vm.max_map_count=262144` 
   2. `sysctl -p`
2. 创建文件夹
   1. `mkdir -p /mydata/elasticsearch/data`
   2. `mkdir -p /mydata/logstash`
3. 赋权
   1. `chmod 777 /mydata/elasticsearch/data`



## 配置文件

1. logstash.conf (放入`/mydata/logstash`文件夹下)

   ```conf
   input {
     tcp {
       mode => "server"
       host => "0.0.0.0"
       port => 4560
       codec => json_lines
       type => "debug"
     }
     tcp {
       mode => "server"
       host => "0.0.0.0"
       port => 4561
       codec => json_lines
       type => "error"
     }
     tcp {
       mode => "server"
       host => "0.0.0.0"
       port => 4562
       codec => json_lines
       type => "business"
     }
     tcp {
       mode => "server"
       host => "0.0.0.0"
       port => 4563
       codec => json_lines
       type => "record"
     }
   }
   filter{
     if [type] == "record" {
       mutate {
         remove_field => "port"
         remove_field => "host"
         remove_field => "@version"
       }
       json {
         source => "message"
         remove_field => ["message"]
       }
     }
   }
   output {
     elasticsearch {
       hosts => ["es:9200"]
       action => "index"
       codec => json
       index => "easy-elk-%{type}-%{+YYYY.MM.dd}"
       template_name => "easy-elk"
     }
   }
   ```

   

## docker-compose 脚本

```yaml
version: '3'
services:
  elasticsearch:
    image: elasticsearch:6.4.0
    container_name: elasticsearch
    environment:
      - "cluster.name=elasticsearch" #设置集群名称为elasticsearch
      - "discovery.type=single-node" #以单一节点模式启动
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m" #设置使用jvm内存大小
      - TZ=Asia/Shanghai
    volumes:
      - /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins #插件文件挂载
      - /mydata/elasticsearch/data:/usr/share/elasticsearch/data #数据文件挂载
    ports:
      - 9200:9200
      - 9300:9300
  kibana:
    image: kibana:6.4.0
    container_name: kibana
    links:
      - elasticsearch:es #可以用es这个域名访问elasticsearch服务
    depends_on:
      - elasticsearch #kibana在elasticsearch启动之后再启动
    environment:
      - "elasticsearch.hosts=http://es:9200" #设置访问elasticsearch的地址
      - TZ=Asia/Shanghai
    ports:
      - 5601:5601
  logstash:
    image: logstash:6.4.0
    container_name: logstash
    environment:
      - TZ=Asia/Shanghai
    volumes:
      - /mydata/logstash/logstash.conf:/usr/share/logstash/pipeline/logstash.conf #挂载logstash的配置文件
    depends_on:
      - elasticsearch #kibana在elasticsearch启动之后再启动
    links:
      - elasticsearch:es #可以用es这个域名访问elasticsearch服务
    ports:
      - 4560:4560
      - 4561:4561
      - 4562:4562
      - 4563:4563
```



## 安装插件

1. 中文分词器IKAnalyzer
   1. `docker exec -it elasticsearch /bin/bash`
   2. `elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.4.0/elasticsearch-analysis-ik-6.4.0.zip `
   3. `exit`
   4. `docker restart elasticsearch`
2. json_lines
   1. `docker exec -it logstash /bin/bash`
   2. `logstash-plugin install logstash-codec-json_lines`
   3. `exit`
   4. `docker restart logstash`











## 复制进文件

```yaml
logstash:
  host: 192.168.0.115
logging:
  level:
    root: debug
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration>
<configuration>
    <!--引用默认日志配置-->
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>
    <!--使用默认的控制台日志输出实现-->
    <include resource="org/springframework/boot/logging/logback/console-appender.xml"/>
    <!--应用名称-->
    <springProperty scope="context" name="APP_NAME" source="spring.application.name" defaultValue="springBoot"/>
    <!--日志文件保存路径-->
    <property name="LOG_FILE_PATH" value="${LOG_FILE:-${LOG_PATH:-${LOG_TEMP:-${java.io.tmpdir:-/tmp}}}/logs}"/>
    <!--LogStash访问host-->
    <springProperty name="LOG_STASH_HOST" scope="context" source="logstash.host" defaultValue="localhost"/>

    <!--DEBUG日志输出到文件-->
    <appender name="FILE_DEBUG"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--输出DEBUG以上级别日志-->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level>
        </filter>
        <encoder>
            <!--设置为默认的文件日志格式-->
            <pattern>${FILE_LOG_PATTERN}</pattern>
            <charset>UTF-8</charset>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--设置文件命名格式-->
            <fileNamePattern>${LOG_FILE_PATH}/debug/${APP_NAME}-%d{yyyy-MM-dd}-%i.log</fileNamePattern>
            <!--设置日志文件大小，超过就重新生成文件，默认10M-->
            <maxFileSize>${LOG_FILE_MAX_SIZE:-10MB}</maxFileSize>
            <!--日志文件保留天数，默认30天-->
            <maxHistory>${LOG_FILE_MAX_HISTORY:-30}</maxHistory>
        </rollingPolicy>
    </appender>

    <!--ERROR日志输出到文件-->
    <appender name="FILE_ERROR"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--只输出ERROR级别的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <encoder>
            <!--设置为默认的文件日志格式-->
            <pattern>${FILE_LOG_PATTERN}</pattern>
            <charset>UTF-8</charset>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--设置文件命名格式-->
            <fileNamePattern>${LOG_FILE_PATH}/error/${APP_NAME}-%d{yyyy-MM-dd}-%i.log</fileNamePattern>
            <!--设置日志文件大小，超过就重新生成文件，默认10M-->
            <maxFileSize>${LOG_FILE_MAX_SIZE:-10MB}</maxFileSize>
            <!--日志文件保留天数，默认30天-->
            <maxHistory>${LOG_FILE_MAX_HISTORY:-30}</maxHistory>
        </rollingPolicy>
    </appender>

    <!--DEBUG日志输出到LogStash-->
    <appender name="LOG_STASH_DEBUG" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level>
        </filter>
        <destination>${LOG_STASH_HOST}:4560</destination>
        <encoder charset="UTF-8" class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <timestamp>
                    <timeZone>Asia/Shanghai</timeZone>
                </timestamp>
                <!--自定义日志输出格式-->
                <pattern>
                    <pattern>
                        {
                        "project": "easy-docker",
                        "level": "%level",
                        "service": "${APP_NAME:-}",
                        "pid": "${PID:-}",
                        "thread": "%thread",
                        "class": "%logger",
                        "message": "%message",
                        "stack_trace": "%exception{20}"
                        }
                    </pattern>
                </pattern>
            </providers>
        </encoder>
        <!--当有多个LogStash服务时，设置访问策略为轮询-->
        <connectionStrategy>
            <roundRobin>
                <connectionTTL>5 minutes</connectionTTL>
            </roundRobin>
        </connectionStrategy>
    </appender>

    <!--ERROR日志输出到LogStash-->
    <appender name="LOG_STASH_ERROR" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <destination>${LOG_STASH_HOST}:4561</destination>
        <encoder charset="UTF-8" class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <timestamp>
                    <timeZone>Asia/Shanghai</timeZone>
                </timestamp>
                <!--自定义日志输出格式-->
                <pattern>
                    <pattern>
                        {
                        "project": "easy-docker",
                        "level": "%level",
                        "service": "${APP_NAME:-}",
                        "pid": "${PID:-}",
                        "thread": "%thread",
                        "class": "%logger",
                        "message": "%message",
                        "stack_trace": "%exception{20}"
                        }
                    </pattern>
                </pattern>
            </providers>
        </encoder>
        <!--当有多个LogStash服务时，设置访问策略为轮询-->
        <connectionStrategy>
            <roundRobin>
                <connectionTTL>5 minutes</connectionTTL>
            </roundRobin>
        </connectionStrategy>
    </appender>

    <!--业务日志输出到LogStash-->
    <appender name="LOG_STASH_BUSINESS" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>${LOG_STASH_HOST}:4562</destination>
        <encoder charset="UTF-8" class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <timestamp>
                    <timeZone>Asia/Shanghai</timeZone>
                </timestamp>
                <!--自定义日志输出格式-->
                <pattern>
                    <pattern>
                        {
                        "project": "easy-docker",
                        "level": "%level",
                        "service": "${APP_NAME:-}",
                        "pid": "${PID:-}",
                        "thread": "%thread",
                        "class": "%logger",
                        "message": "%message",
                        "stack_trace": "%exception{20}"
                        }
                    </pattern>
                </pattern>
            </providers>
        </encoder>
        <!--当有多个LogStash服务时，设置访问策略为轮询-->
        <connectionStrategy>
            <roundRobin>
                <connectionTTL>5 minutes</connectionTTL>
            </roundRobin>
        </connectionStrategy>
    </appender>

    <!--接口访问记录日志输出到LogStash-->
    <appender name="LOG_STASH_RECORD" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>${LOG_STASH_HOST}:4563</destination>
        <encoder charset="UTF-8" class="net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder">
            <providers>
                <timestamp>
                    <timeZone>Asia/Shanghai</timeZone>
                </timestamp>
                <!--自定义日志输出格式-->
                <pattern>
                    <pattern>
                        {
                        "project": "easy-docker",
                        "level": "%level",
                        "service": "${APP_NAME:-}",
                        "class": "%logger",
                        "message": "%message"
                        }
                    </pattern>
                </pattern>
            </providers>
        </encoder>
        <!--当有多个LogStash服务时，设置访问策略为轮询-->
        <connectionStrategy>
            <roundRobin>
                <connectionTTL>5 minutes</connectionTTL>
            </roundRobin>
        </connectionStrategy>
    </appender>

    <!--控制框架输出日志-->
    <logger name="org.slf4j" level="INFO"/>
    <logger name="springfox" level="INFO"/>
    <logger name="io.swagger" level="INFO"/>
    <logger name="org.springframework" level="INFO"/>
    <logger name="org.hibernate.validator" level="INFO"/>

    <root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
        <!--<appender-ref ref="FILE_DEBUG"/>-->
        <!--<appender-ref ref="FILE_ERROR"/>-->
        <appender-ref ref="LOG_STASH_DEBUG"/>
        <appender-ref ref="LOG_STASH_ERROR"/>
    </root>

    <logger name="com.macro.mall.tiny.component" level="DEBUG">
        <appender-ref ref="LOG_STASH_RECORD"/>
    </logger>

    <logger name="com.macro.mall" level="DEBUG">
        <appender-ref ref="LOG_STASH_BUSINESS"/>
    </logger>
</configuration>
```

