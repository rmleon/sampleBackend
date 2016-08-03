package repositories

import javax.inject.Inject

import models.TradesTable
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * @author Ricardo Leon
  * @since 8/1/16
  */
class TradesRepository @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  private val db       = dbConfig.db

  import dbConfig.driver.api._

  def create(in: TradesTable.TradesRow): Future[Int] =
    db.run(TradesTable.Trades += in)

  def getById(id: java.util.UUID): Future[TradesTable.TradesRow] =
    db.run(TradesTable.Trades.filter(_.id === id).result.head)

  def updateById(in: TradesTable.TradesRow): Future[Int] =
    db.run(TradesTable.Trades.filter(_.id === in.id).update(in))

  def deleteById(id: java.util.UUID): Future[Int] =
    db.run(TradesTable.Trades.filter(_.id === id).delete)

  def all(start: Int, limit: Int): Future[Seq[TradesTable.TradesRow]] =
    db.run(TradesTable.Trades.drop(start).take(limit).to[Seq].result)

  def count: Future[Int] =
    db.run(TradesTable.Trades.length.result)
}
