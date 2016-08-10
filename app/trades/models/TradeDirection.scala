package trades.models

/**
  * @author Ricardo Leon
  */
object TradeDirection extends Enumeration {
  type TradeDirection = Value
  val Buy = Value("B")
  val Sell = Value("S")
}
