package model

import java.util.Date

case class Post(id: Int, title: String, contentFile: String,content: String, createAt: Option[Date], updateAt: Option[Date])