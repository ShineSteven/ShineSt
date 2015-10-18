## ShineSt Tech Blog(Beta)

### Version
Scala -> `2.11.7`
Java -> `1.8.60`
MaraiDB -> `10.0.21`

### Intro
Post meta data store to db, blog's post content and image use amazon s3

DB Schema

 Field        | Type         | Null | Key | Default | Extra          
--------------|--------------|------|-----|---------|----------------
id           | int(11)      | NO   | PRI | NULL    | auto_increment
 title        | varchar(100) | NO   |     | NULL    |               
 content_file | varchar(200) | YES  |     | NULL    |                
 create_at    | datetime     | NO   |     | NULL    |                
 update_at    | datetime     | YES  |     | NULL    |     
### Config Setting
`conf/application.conf`
```
DB {
  "ip" : "${key ip}",
  "database" : "${key database}",
  "account" : "${key account}",
  "pw" : "${key password}"
}


blog.bucket {
  "name" : "${key bucket name}"
}
```

### Aws Credentials
1. [add aws IAM user credentials](http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/getting-started-signup.html)
2. add credentials file to
 * Linux, OS X, or Unix: `~/.aws/credentials`
 * Windows : `C:\Users\USERNAME \.aws\credentials`


### Package
This file will be packaged with your application, when using  
`activator clean assembly`.

### Run
`java -jar -Dhttp.port=80 -Dhttp.address=${your ip} /usr/local/web/ShineSt-assembly-1.0-SNAPSHOT.jar`
