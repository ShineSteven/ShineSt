package controllers

import model.Person
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.welcome("Your new application is ready."))
  }

  val personForm: Form[Person] = Form {
    mapping {
      "name" -> text
    }(Person.apply)(Person.unapply)
  }



}
