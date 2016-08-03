package models

import slick.lifted.TableQuery

/**
  * This file was partially auto-generated through Slick 3.
  * @author Ricardo Leon
  * @since 8/1/16
  */
object TradesTable extends {
  final val profile = slick.driver.PostgresDriver
} with TradesTable

/**
  * Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.)
  **/
trait TradesTable {

  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /**
    * Collection-like TableQuery object for table Trades
    **/
  lazy val Trades = TableQuery[Trades](tag => new Trades(tag))

  val profile: slick.driver.JdbcProfile

  import profile.api._

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Trades.schema

  /**
    * GetResult implicit for fetching TradesRow objects using plain SQL queries
    **/
  implicit def GetResultTradesRow(implicit e0: GR[java.util.UUID], e1: GR[java.sql.Date], e2: GR[String], e3: GR[Long], e4: GR[scala.math.BigDecimal]): GR[TradesRow] = GR {
    prs => import prs._
      TradesRow.tupled((<<[java.util.UUID], <<[java.sql.Date], <<[String], <<[String], <<[Long], <<[scala.math.BigDecimal]))
  }

  /**
    * Entity class storing rows of table Trades
    *
    * @param id        Database column id SqlType(uuid), PrimaryKey
    * @param date      Database column date SqlType(date)
    * @param symbol    Database column symbol SqlType(varchar), Length(100,true)
    * @param direction Database column direction SqlType(trade_direction)
    * @param quantity  Database column quantity SqlType(int8)
    * @param price     Database column price SqlType(numeric) */
  case class TradesRow(id: java.util.UUID, date: java.sql.Date, symbol: String, direction: String, quantity: Long, price: scala.math.BigDecimal)

  /** Table description of table trades. Objects of this class serve as prototypes for rows in queries. */
  class Trades(_tableTag: profile.api.Tag) extends profile.api.Table[TradesRow](_tableTag, "trades") {
    /** Database column id SqlType(uuid), PrimaryKey */
    val id       : Rep[java.util.UUID]        = column[java.util.UUID]("id", O.PrimaryKey)
    /** Database column date SqlType(date) */
    val date     : Rep[java.sql.Date]         = column[java.sql.Date]("date")
    /** Database column symbol SqlType(varchar), Length(100,true) */
    val symbol   : Rep[String]                = column[String]("symbol", O.Length(100, varying = true))
    /** Database column direction SqlType(trade_direction).  Either "B" or "S" */
    val direction: Rep[String]                = column[String]("direction")
    /** Database column quantity SqlType(int8) */
    val quantity : Rep[Long]                  = column[Long]("quantity")
    /** Database column price SqlType(numeric) */
    val price    : Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("price")

    def * = (id, date, symbol, direction, quantity, price) <> (TradesRow.tupled, TradesRow.unapply)

    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(date), Rep.Some(symbol), Rep.Some(direction), Rep.Some(quantity), Rep.Some(price)).shaped.<>({ r => ; r._1.map(_ => TradesRow.tupled((r._1.get, r._2.get, r._3.get, r._4.get, r._5.get, r._6.get))) }, (_: Any) => throw new Exception("Inserting into ? projection not supported."))
  }

}