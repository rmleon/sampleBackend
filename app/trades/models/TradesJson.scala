package trades.models

import play.api.libs.functional.syntax.{toFunctionalBuilderOps, unlift}
import play.api.libs.json._

/**
  * @author Ricardo Leon
  */
object TradesJson {
  implicit val directionReads  = Reads.enumNameReads(TradeDirection)

  implicit val tradesRowsWrites: Writes[TradesTable.TradesRow] =
    (
      (JsPath \ "id").write[java.util.UUID] and
        (JsPath \ "date").write[java.sql.Date] and
        (JsPath \ "symbol").write[String] and
        (JsPath \ "direction").write[TradeDirection.TradeDirection] and
        (JsPath \ "quantity").write[Long] and
        (JsPath \ "price").write[BigDecimal]
      ).apply(unlift(TradesTable.TradesRow.unapply))

  implicit val tradesRowsReads: Reads[TradesTable.TradesRow] =
    (
      (JsPath \ "id").read[java.util.UUID] and
        (JsPath \ "date").read[java.sql.Date] and
        (JsPath \ "symbol").read[String] and
        (JsPath \ "direction").read[TradeDirection.TradeDirection] and
        (JsPath \ "quantity").read[Long] and
        (JsPath \ "price").read[BigDecimal]
      ).apply(TradesTable.TradesRow.apply _)
}
