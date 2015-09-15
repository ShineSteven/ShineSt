package model


import play.api.libs.json.Json


/**
 * Created by shinesteven on 2015/7/24.
 */
case class Person(name: String)

object Person {
  implicit val personFormat = Json.format[Person]

}