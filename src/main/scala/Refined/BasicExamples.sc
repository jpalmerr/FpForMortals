import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.boolean.{And, Not}
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric._

val i1: Int Refined Positive = 5
i1.value

// val i2: Int Refined Positive = -5  doesn't compile :)

/**
 * Alias for `[[api.RefType.refineM]][P]` with `[[api.Refined]]` as type
 * parameter for `[[api.RefType]]`.
 *
 * Note: `M` stands for '''m'''acro and `V` stands for '''v'''alue class.
 */
val rmv = refineMV[Positive](5)
rmv.value

// suppose the value of x is not known at compile-time

val x = 42

refineV[Positive](x) // Right(42)
refineV[Positive](-x) // Left(Predicate failed: (-42 > 0).)

// refined also contains inference rules

val a: Int Refined Greater[W.`5`.T] = 10
//val b: Int Refined Greater[W.`5`.T] = 4 // wont compile

val b: Int Refined Greater[W.`5`.T] = a
b.value // 10

refineMV[NonEmpty]("Hello")

// custom type
type ZeroToOne = Not[Less[W.`0.0`.T]] And Not[Greater[W.`1.0`.T]]

refineMV[ZeroToOne](0.8)
