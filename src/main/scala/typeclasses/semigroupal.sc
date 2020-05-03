import cats.implicits._
import cats.{Align, Semigroupal}

Align[List].align(List("a", "b", "c"), List(1,2,3,4))
// List(Both(a,1), Both(b,2), Both(c,3), Right(4))

Semigroupal[List].product(List("a", "b", "c"), List(1,2,3,4))

// cartesian product
/*
List(
(a,1), (a,2), (a,3), (a,4),
(b,1), (b,2), (b,3), (b,4),
(c,1), (c,2), (c,3), (c,4))
 */