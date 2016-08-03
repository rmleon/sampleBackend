package controllers

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

/**
  * Dummy index.  Without this page, the monitoring service thinks the app is failing.
  * @author Ricardo Leon
  * @since 8/3/16
  */
class IndexController @Inject() extends Controller {
  def index = Action {
    Ok(Json.obj("success" -> true))
  }
}
