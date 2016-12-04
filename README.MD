# Shine St Blog code
### 架構
```
Apache -- Play Framework -- MariaDB
          |
          +--------------- Amazon S3
```
後端程式使用 Scala 開發，ui使用 `semantic-ui`，

貼文內容來源為 Amazon S3，

用 markdown 編輯 post 內容後，於 `localhost:9000/backend/add_post` 後台新增貼文。

### pre package
* Scala: `2.11.8`
* Java: `1.8.77`
* MariaDB: `10.1.14`
* apache server: `2.4.18`
* curl

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
  `keywords` varchar(30) DEFAULT NULL,
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

##### manager
```
CREATE TABLE `manager` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL,
  `alias_name` varchar(30) NOT NULL,
  `password` varchar(100) NOT NULL,
  `create_at` datetime NOT NULL,
  `status` TINYINT,
PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;
```

##### brief
```
CREATE TABLE `brief` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `content` varchar(100) NOT NULL,
PRIMARY KEY (`id`),
KEY `fk_post_id` (`post_id`),
CONSTRAINT `fk_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
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
1. 須先 install 我的[common](https://github.com/ShineSteven/common)程式

2. package use dist
```
activator clean dist
```
之後將 `target/universal/shinest.zip` copy 到 server 上做 unzip

##### run
需在`-Dplay.crypto.secret` 此參數加入你自己的 secret key (隨意)
```
./shinest/bin/blog -Dconfig.resource=production/application.conf -Dlogger.resource=production/logback.xml -Dplay.crypto.secret="${secret key}"
```

### backstage
##### manager
manager table password sample，使用sha256做hash
```
insert into manager (account,alias_name,password,create_at,status) values('administrator','Administrator', sha2(concat('administrator','test'),256),now(),1);
```

### note
* CHARSET is `UTF-8`
* view 的所有 url 皆使用儲存在 `apache` 上的靜態頁，故需要執行`etc/makeStatic.sh`，batch程式還未撰寫，需手動加`curl` 指令上去。
* 動態頁url可參考 `conf/routes`
