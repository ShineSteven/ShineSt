# Shine St Blog code
架構
```
apache -> play framwrok -> mariadb
```
使用Scala開發，apache放`.html`檔用，

post貼文來源為amazon s3，用markdown編輯post內容，匯出成html後上傳到amazon S3


### note
* CHARSET is `UTF-8`

### pre package
* Scala: `2.11.8`
* Java: `1.8.77`
* MaraiDB: `10.1.14`
* apache server: `2.4.18`


### DB Schema
##### categories
```
CREATE TABLE `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `description` tinytext,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `categories_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `categories` (`id`)
) DEFAULT CHARSET=utf8;
```

##### post
```
CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `content_file` varchar(200) DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category_id` (`category_id`),
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) DEFAULT CHARSET=utf8;
```

### config Setting
##### DB & amazon s3 config on
```
config/production/application.conf
config/application.conf
```

##### apache config
可參考
```
etc/apache/config/httpd.conf
```

或直接用預設的httpd.conf，在尾巴加上
```
<VirtualHost *:80>
RewriteEngine on
RewriteRule "^/$"  "/index.html"
  ProxyPreserveHost On
  ProxyPass "/post/static" !
  ProxyPass "/index.html" !
  ProxyPass "/" "http://127.0.0.1:9000/"
  ProxyPassReverse "/" "http://127.0.0.1:9000/"
</VirtualHost>

```

### AWS Credentials
1. [add aws IAM user credentials](http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/getting-started-signup.html)
2. add credentials file to
 * Linux, OS X, or Unix: `~/.aws/credentials`
 * Windows : `C:\Users\USERNAME \.aws\credentials`


### deploy
##### package
1. 須先install 我的[common](https://github.com/ShineSteven/shinest_common)程式

2. package use dist
```
activator clean dist
```
之後將 `target/universal/shinest.zip` copy到server 上做unzip

##### run
```
./shinest/bin/shinest -Dconfig.resource=production/application.conf -Dlogger.resource=production/logback.xml
```
