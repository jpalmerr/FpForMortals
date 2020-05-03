/*
Foldable has a method named .combineAllOption which is like .fold but takes a Semigroup instead of a Monoid,
returning an Option if the collection is empty
(recall that Semigroup does not have a empty)
 */

import cats.kernel.instances.int._
import cats.kernel.instances.string._
import cats.kernel.Semigroup

Semigroup[String].combineAllOption(List("One ", "Two ", "Three"))
// Some(One Two Three)
Semigroup[String].combineAllOption(List.empty)
// None

Semigroup[Int].combineAllOption(List(1, 3, 7)) // Some(11)