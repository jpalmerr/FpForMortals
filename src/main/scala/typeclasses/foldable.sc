/*
def count[A](fa: F[A])(p: A => Boolean): Long = ...
  def forall[A](fa: F[A])(p: A => Boolean): Boolean = ...
  def exists[A](fa: F[A])(p: A => Boolean): Boolean = ...
  def find[A](fa: F[A])(f: A => Boolean): Option[A] = ...



.count is a way of counting how many elements are true for a predicate

.forall and .exists return true if all (or any, respectively) element meets the predicate
and may exit early.

.find returns the first element matching the predicate.
 */

import cats.implicits._

List("foo", "fazz").maximumByOption(_.length) // Some(fazz)