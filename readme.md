# 邮箱验证-后端

前端项目地址：https://github.com/Glong97/Redis-Applications

## 环境

* JDK：1.8
* SpringBoot：2.6.6
* [Redis：6.2.1](https://redis.io/download/#redis-downloads)

* [IDE：IntelliJ IDEA2023.3.3](https://www.jetbrains.com/zh-cn/idea/)

* [Maven：3.6.3](https://dlcdn.apache.org/maven/maven-3/3.6.3/binaries/)

## 开启POP3/SMTP服务

如果邮箱的发送者改成自己的QQ邮箱，则需要在QQ邮箱的【设置】=>【账户】里开启POP3/SMTP服务。并保存生成的授权码（生成的授权码需要配置到application.yml的配置文件中）

## 配置

```yaml
server:
  # 服务端口
  port: 8204
Spring:
  redis:
    # 改成自己redis的ip地址
    host: xxx.xxx.xxxx.xxx
    port: 6379
    database: 0
    timeout: 1800000
    lettuce:
      pool:
        # 最大阻塞等待时间(负数表示没限制)
        max-active: 20
        max-wait: -1
  mail:
    # 配置QQ邮件信息
    host: smtp.qq.com
    # 发送邮件者信箱（可以改成自己的邮箱）
    username: xxxxx@xx.xx
    # IMAP/SMTP服务时邮箱的密文授权码
    password: vbblpmdvmcsfbcbg
    default-encoding: UTF-8
    port: 465
    properties:
      mail:
        debug: true
        smtp:
          socketFactory:
            class: javax.net.ssl.SSLSocketFactory
```

