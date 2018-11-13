"# demo-FilmRental" 
### 电影租赁系统使用说明


---
###### 配置说明

1. 数据库配置，更改数据库配置文件 resources\jdbc.properties  ，数据表结构有所改变，需要重新导入sql脚本
2. 项目运行时名字默认为 / ，便可正常访问  可用admin admin 登录 


---
###### 项目路径说明

1. Java文件在src\mian\java
2. 配置资源文件  src\mian\resources
3. html文件在  webapp\view ， 引用的资源文件在webapp\static
4. 自己写的js在webapp\static\js 内  包含customer.js（客户管理） 和 util.js（校验和日期转换）
5. webapp\view\common 公共的网页内容部分 菜单栏（sidebar.html） 网页头（header.html）  网页脚（footer.html）
6. webapp\view\customer 与客户管理相关页面  列表页面和创建页面