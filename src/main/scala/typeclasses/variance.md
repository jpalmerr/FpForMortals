# Variance

Invariant has a method `.imap` which says that:
 
given
- a function from A to B
- and a function from B to A,
- then we can convert F[A] to F[B].

```scala
final case class Alpha(value: Double)
```
but now we’re faced with the problem that we don’t have any typeclasses for these new types.

If we use the values in JSON documents, we have to write instances of JsEncoder and JsDecoder.
However, JsEncoder has a Contravariant and JsDecoder has a Functor, so we can derive instances. Filling in the contract:

- if you give me a JsDecoder for a Double, and a way to go from a Double to an Alpha
    - then I can give you a JsDecoder for an Alpha”.
- if you give me a JsEncoder for a Double, and a way to go from an Alpha to a Double,
    - then I can give you a JsEncoder for an Alpha.
    
```scala
 object Alpha {
    implicit val decoder: JsDecoder[Alpha] = JsDecoder[Double].map(Alpha(_))
    implicit val encoder: JsEncoder[Alpha] = JsEncoder[Double].contramap(_.value)
}
```    

This is a pattern I see a lot at work. Using circe:

```scala
import io.circe.{ Decoder, Encoder }
import io.circe.generic.semiauto.{ deriveDecoder, deriveEncoder }

final case class PlansResponse(plans: List[PlanData])

object PlansResponse {
  val default: PlansResponse                   = PlansResponse(PlanData.plans)
  implicit val encoder: Encoder[PlansResponse] = deriveEncoder
  implicit val decoder: Decoder[PlansResponse] = deriveDecoder
}
``` 