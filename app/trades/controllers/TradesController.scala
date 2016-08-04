package trades.controllers

import java.util.UUID
import javax.inject.Inject

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsError, JsSuccess, Json}
import play.api.mvc.{Action, BodyParsers, Controller}
import trades.models.TradesTable
import trades.repositories.TradesRepository

import scala.concurrent.Future


class TradesController @Inject()(repository: TradesRepository)
  extends Controller {

  import trades.models.TradesJson.{tradesRowsReads, tradesRowsWrites}

  private val ok       = Ok(Json.obj("success" -> true))
  private val notFound = NotFound(Json.obj("success" -> false, "message" -> "trade not found"))

  def list(start: Int, limit: Int) = Action.async { implicit rs =>
    repository.count.zip(repository.all(start, limit)).map {
      case (count, list) =>
        Ok(Json.toJson(list)).withHeaders("X-Total-Count" -> count.toString)
    }
  }

  def create = Action.async(BodyParsers.parse.json) { implicit rs =>
    rs.body.validate[TradesTable.TradesRow] match {
      case JsSuccess(trade, _) =>
        val tradeWithKey = trade.copy(id = UUID.randomUUID())
        repository.create(tradeWithKey).map { insertedRecords =>
          if (insertedRecords > 0)
            ok
          else
            InternalServerError(Json.obj("success" -> false, "message" -> "Could not create trade"))
        }
      case JsError(errors) =>
        Future(BadRequest(Json.obj("success" -> false, "message" -> JsError.toJson(errors))))
    }
  }

  def delete(id: String) = Action.async { implicit rs =>
    repository.deleteById(java.util.UUID.fromString(id)).map(res =>
      if (res > 0)
        Ok(Json.obj("success" -> true))
      else
        notFound
    )
  }

  def update(id: String) = Action.async(BodyParsers.parse.json) { rs =>
    rs.body.validate[TradesTable.TradesRow] match {
      case JsSuccess(trade, _) if java.util.UUID.fromString(id) == trade.id =>
        repository.updateById(trade).map { updatedRecords =>
          if (updatedRecords > 0)
            ok
          else
            notFound
        }
      case JsSuccess(_, _) =>
        Future(notFound)
      case JsError(errors) =>
        Future(BadRequest(Json.obj("success" -> false, "message" -> JsError.toJson(errors))))
    }

  }

}