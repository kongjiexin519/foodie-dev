# 天天吃货电商项目

## 接口文档地址
http://localhost:8088/doc.html

## 打开前端项目
- terminal
    - 进入tomcat/bin目录 
    - 输入./startup.sh 启动tomcat
- 打开网址
http://localhost:8080/foodie-shop/

#####  Failed to connect to github.com port 443: Operation timed out
git config --global http.sslBackend "openssl" 
##### LibreSSL SSL_connect: SSL_ERROR_SYSCALL in connection to github.com:443  
git config --global --unset http.proxy  
 
## 购物车存储形式
#### Cookie
- 无需登录、无须查库、保持在浏览器端
- 优点：性能好、访问快，没有和数据库交互
- 缺点：
    - 换电脑购物车数据会丢失
    - 电脑被其它人登录、隐私安全
#### Session
- 用户登录后，购物车数据放入用户会话
- 优点：初期性能好，访问快
- 缺点：
    - session基于内存，用户量庞大影响服务器性能
    - 只能存在于当前会话，不适用集群与分布式系统
#### 数据库
- 用户登录后，购物车数据存入数据库
- 优点：数据持久化，可在任何地点任何时间访问
- 缺点：频繁读写数据库，造成数据库压力
#### Redis
- 用户登录后，购物车数据存入redis缓存
- 优点：
    - 数据持久化，可在任何地点任何时间访问
    - 频繁读写只基于缓存，不会造成数据库压力
    - 适用于集群与分布式系统，可扩展性强