// Traverse is what happens when we cross a Functor with a Foldable

/*
  Given a function which returns a G effect, thread this effect
  through the running of this function on all the values in F,
  returning an F[B] in a G context.
 */

import cats.implicits._
def parseInt(s: String): Option[Int] = Either.catchOnly[NumberFormatException](s.toInt).toOption

List("1", "2", "3").traverse(parseInt)
// Option[List[Int]] = Some(List(1, 2, 3))

List("1", "two", "3").traverse(parseInt)
// Option[List[Int]] = None

/*
.flatTraverse and .flatSequence are useful for cases where
we want to flatten the results of the calculation.
 */

val x: List[Option[Int]] = List(Some(1), Some(2))
val y: List[Option[Int]] = List(None, Some(2))
x.sequence // Option[List[Int]] = Some(List(1, 2))
y.sequence // None
