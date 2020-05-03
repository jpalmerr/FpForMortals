/*
SemigroupK is Semigroup but for type constructors, and MonoidK is the equivalent of Monoid.
The K suffix is for Kind, as in the Higher Kinded Types (HKT)
 */

/*

<*> - higher kinded combine

think of it as operating only at the F[_] level rather than the contents

SemigroupK has the convention that it should ignore failures and “pick the first winner”.
<+> can therefore be used as a mechanism for early exit

(hence why I see it used in our service servers!)

 */

import cats.data.NonEmptyList
import cats.implicits._

Option(1) |+| Option(2) // Some(3)
Option(1) <+> Option(2) // Some(1)
Option.empty[Int] <+> Option(1) // Some(1)

/*
For example,
if we have a NonEmptyList[Option[Int]]
and we want to ignore None values (failures)
and pick the first winner (Some),
we can call .reduceK
 */

NonEmptyList.of(None, None, Some(1), Some(2), None).reduceK // Some(1)

List(Right(1), Left("boo"), Right(2)).unite // List(1, 2)

val list: List[Either[Int, String]] =
  List(Right("hello"), Left(1), Left(2), Right("world"))
list.separate // (List(1, 2),List(hello, world))