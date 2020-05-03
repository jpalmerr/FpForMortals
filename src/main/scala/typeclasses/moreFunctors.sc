// align

import cats.implicits._

Map("foo" -> List(1)) alignCombine Map("foo" -> List(1), "bar" -> List(2))
// Map(foo -> List(1, 1), bar -> List(2))

// Map[K, Int] simply tally their contents when merging

Map("foo" -> 1) alignCombine Map("foo" -> 1, "bar" -> 2)
// Map(foo -> 2, bar -> 2)

Map("foo" -> 1) padZip Map("foo" -> 1, "bar" -> 2)
// Map(foo -> (Some(1),Some(1)), bar -> (None,Some(2)))

Map("foo" -> 1, "bar" -> 2) padZip Map("foo" -> 1)
// Map(foo -> (Some(1),Some(1)), bar -> (Some(2),None))

/* Bifunctor, Bifoldable and Bitraverse

Cats provides variations of Functor, Foldable and Traverse for structures that require two functions, not just one.
 */

val a: Either[String, Int] = Left("fail")
val b: Either[String, Int] = Right(13)

b.bimap(_.toUpperCase, _ * 2) // Right(26)
a.bimap(_.toUpperCase, _ * 2) // Left(FAIL)

a.bifoldMap(_.length, identity) // Int = 4
b.leftMap(_.toUpperCase) // Right(13)

// leaving the contents of the Right untouched ^

a.bifoldMap(_.length, identity) // Int = 4

a.bitraverse(s => Option(s.length), i => Option(i))
b.bitraverse(s => Option(s.length), i => Option(i * 2)) // Some(Right(26))

/* Filters

FunctorFilter adds the ability to discard entries from the functor with its
.mapFilter method and related convenience methods.
Similarly to Align, FunctorFilter must be able to provide a Functor.
*/

// `FunctorFilter[F]` allows you to `map` and filter out elements simultaneously.

val m: Map[Int, String] = Map(1 -> "one", 3 -> "three")
val l: List[Int] = List(1, 2, 3, 4)
def asString(i: Int): Option[String] = m.get(i) // cats get is basically getOrElse

l.mapFilter(asString) // List(one, three)

