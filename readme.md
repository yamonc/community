#资料
##啥啥啥的都没啥用

[github登录页面](https://developer.github.com/apps/building-oauth-apps/)

## 部署
### 依赖
- Git
- JDK
- Maven
- Mysql
## 步骤
- yum update 更新可安装资源
- yum install git 安装git
- mkdir App  创建项目路径
- clone https://github.com/yamonc/community.git 下载项目
- yum install maven 安装maven
- mvn -v  查看maven工具版本
- java -version 查看java版本
- mvn clean compile package  编译打包
## 其他
查看其进程是否存在：ps -aux | grep java
