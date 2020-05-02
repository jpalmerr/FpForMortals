package typeclasses

import cats._
import cats.implicits._
import typeclasses.TradeTemplate.zero


sealed abstract class Currency
case object EUR extends Currency
case object USD extends Currency

final case class TradeTemplate(
                                payments: List[java.time.LocalDate],
                                ccy: Option[Currency],
                                otc: Option[Boolean]
                              )

object TradeTemplate {
  implicit val monoid: Monoid[TradeTemplate] = Monoid.instance(
    TradeTemplate(Nil, None, None),
    (a, b) => TradeTemplate(a.payments |+| b.payments, a.ccy |+| b.ccy, a.otc |+| b.otc)
  )

  val zero = Monoid[TradeTemplate].empty

  // must have an instance of Monoid[TradeTemplate] so ^ compiles

  // must have an instance of Monoid[[Option[Currency]] and Monoid[Option[Boolean]] so ^ compiles

  def lastWins[A]: Monoid[Option[A]] = Monoid.instance(
    None,
    {
      case (None, None) => None
      case (only, None) => only
      case (None, only) => only
      case (_, winner) => winner
    })

  implicit val monoidCcy: Monoid[Option[Currency]] = lastWins
  implicit val monoidOtc: Monoid[Option[Boolean]] = lastWins

}

object Example extends App {

  // now try it out

  import java.time.{LocalDate => LD}

  val templates = List(
    TradeTemplate(Nil, None, None),
    TradeTemplate(Nil, Some(EUR), None),
    TradeTemplate(List(LD.of(2019, 5, 2)), Some(USD), None),
    TradeTemplate(List(LD.of(2020, 5, 2)), None, Some(true)),
    TradeTemplate(List(LD.of(2020, 8, 3)), None, Some(false)),
  )

  println(templates.foldLeft(zero)(_ |+| _))
  // TradeTemplate(List(2019-05-02, 2020-05-02, 2020-08-03),Some(USD),Some(false))

  println(templates.combineAll)
  // TradeTemplate(List(2019-05-02, 2020-05-02, 2020-08-03),Some(USD),Some(false))
}
