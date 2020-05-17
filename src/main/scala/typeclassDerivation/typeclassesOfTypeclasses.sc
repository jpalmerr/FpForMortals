import cats.Functor
import cats.implicits._
import cats._
import cats.data._
import simulacrum._
// the simplest way to derive a typeclass is to reuse one that already exists

final case class Foo(s: String)
object Foo {
  implicit val equal: Eq[Foo] = Eq[String].contramap(_.s)
}

Foo("hello") == Foo("world") // Boolean = false

// However, not all typeclasses can have an instance of Contravariant.

