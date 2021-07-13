# Cactus

基于Spring Boot 2.x 的项目脚手架，集成常用功能模块，提供常见业务的实现。

### 项目整体结构
![image-20201221002740508](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201221002740508.png)


### 主要功能

Cactus 主要功能：

1. Swagger
2. MyBatis-Plus
3. Redis
4. Log4j2
5. 跨域访问
6. 全局异常处理
7. 多数据源切换
8. Hutool
9. 自监控
10. 启停脚本

11. 打包脚本


### 模块集成

#### 1. Swagger（默认开启）

配置文件位置：com.cloudwise.cactus/config/SwaggerConfig.java

使用时需改动点：

SwaggerConfing 中要扫描的包名：
![image-20201220223205553](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220223205553.png)

application.yml 文件中已有默认配置，如无特殊需求，无需改动；

访问地址：http://localhost:8080/swagger-ui/index.html

#### 2. MyBatis-Plus

配置文件位置：com.cloudwise.cactus/config/MyBatisPlusConfig.java

使用时需改动点：

a. mapper 所在包名，若数据库不是 MySql，需修改数据库类型；
![image-20201220223657465](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220223657465.png)

b. application.yml 中 mapper 和 实体类所在位置
![image-20201220223901115](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220223901115.png)

c. 代码自动生成在 UnitTest 中的 CodeGeneratorTest，使用时修改数据库连接，要为哪些表生成代码，生成文件位置，父包名，其他配置如有特殊需求根据需求修改；
![image-20201220230048243](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220230048243.png)

引用的包
![image-20201221001111199](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201221001111199.png)

#### 3. Redis

配置文件所在位置：com.cloudwise.cactus/config/RedisConfig.java

工具类所在位置：com.cloudwise.cactus/util/RedisService.java

application.yml 中的配置：

​	a. 单机：

​		![image-20201220225142028](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220225142028.png)

​	b. 集群：

​		![image-20201220225418845](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220225418845.png)

使用时需改动点：

a. 使用单机则启用配置文件中的单机配置并修改相应 host, port, password，其他属性根据需求修改；

b. 使用集群则启用配置文件中的集群配置并修改相应集群节点信息和 host, port, password，其他配置如有特殊需求根据需求修改；

引用的包：
![image-20201221001158143](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201221001158143.png)

#### 4. Redis

配置文件所在位置：com.cloudwise.cactus.config.KafkaConfig.java

功能测试代码所在位置：com.cloudwise.cactus.controller.KafkaDemo

#### 5. Log4j2

配置文件所在位置：config/log4j2-spring.xml

使用时默认无需修改，如有特殊需求修改配置文件即可，配置文件每一个配置项均已添加备注，根据备注修改即可；

#### 6. 跨域访问

配置在 com.cloudwise.cactus/config/WebMvcConfig.java

默认 dev profile 开启，根据需求进行修改
![image-20201220230756461](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220230756461.png)

#### 7. 全局异常处理

文件所在位置：com.cloudwise.cactus/supports/GlobalExceptionHandler.java

默认无需改动，有特殊需求可根据需求改动；

#### 8. 多数据源切换

使用时启用 application.yml 中的多数据源切换配置并修改相应数据库连接

![image-20201220233510477](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220233510477.png)

使用哪个数据源只需在方法上加 @DS 注解，指定数据源即可
![image-20201220233617278](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220233617278.png)

更多使用方式见该文档：https://gitee.com/baomidou/dynamic-datasource-spring-boot-starter/blob/master/README.md

引用的包：
![image-20201221001246972](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201221001246972.png)

#### 9. Hutool 工具包
包含类型转换，加解密，定时任务，内存缓存，布隆过滤器，office操作，二维码，图形验证码等一些列各种工具。

Hutool 详细使用文档：https://hutool.cn/docs/#/

引用的包（包可单独引用，此处引用了 all 包）：
![image-20201221001330741](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201221001330741.png)

#### 10. 自监控

默认开启，若不使用，删除 pom 文件中的引用并去除启动类上的 @EnableAdminServer 注解，访问地址：http://localhost:8080/applications
![image-20201222173810900](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201222173810900.png)

![image-20201220234957173](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220234957173.png)
​    

Admin 页面的日志默认显示 info 日志，若需修改显示的日志文件，可修改 application.yml 中的改配置
​![image-20201220235159154](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201220235159154.png)

**Cactus Admin 使用**

![image-20201221000130391](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201221000130391.png)

![image-20201221000850063](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blogimage-20201221000850063.png)

#### 11. 启停脚本

位置：bin 目录下 start.sh 和 stop.sh

使用时需修改包名；

#### 12. 打包脚本

位置：main/assembly/assembly.xml

### 使用方式

1. 从 git 下载 cactus-admin, cactus 三个项目

2. 进入 spring-boot-admin-server-ui 项目，并执行 mvn clean install 命令将其安装至本地；

3. 进入 cactus-admin 项目，并执行 mvn clean install 命令将其安装至本地；

4. 进入 cactus 根目录，执行 mvn clean 命令；

5. 执行 mvn archetype:create-from-project 生成项目脚手架；

   ![](https://pic-1300537304.cos.ap-nanjing.myqcloud.com/blog20201130161207.png)

6. 进入 target/generated-sources/archetype 文件夹，执行 mvn install 命令将脚手架安装至本地仓库；

7. 执行 mvn archetype:crawl 命令在本地仓库生成模板目录；

8. 创建项目文件夹，在文件夹内执行 mvn archetype:generate -DarchetypeCatalog=local 命令，跟着提示走即可；

   **PS: 1-7 步为一次性工作，若脚手架有更新需再执行一次 4-7 步；**

