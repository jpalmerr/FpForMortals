//implicit class DoubleOps(x: Double) {
//  def sin: Double = java.lang.Math.sin(x)
//
//  def cos: Double = java.lang.Math.cos(x)
//}

/*
preferred due to run time cost of above^.

 each time the extension method is called,
 an intermediate DoubleOps will be constructed and then thrown away.
 */

implicit final class DoubleOps(private val x: Double) extends AnyVal {
  def sin: Double = java.lang.Math.sin(x)

  def cos: Double = java.lang.Math.cos(x)
}

(1.0).sin
(2.0).cos

/* Polymorphic Functions

The more common kind of function is a polymorphic function, which lives in a typeclass. A typeclass is a trait that:
- holds no state
- has a type parameter
- has at least one abstract method (primitive combinators)
- may contain generalised methods (derived combinators)
- may extend other typeclasses

There can only be one implementation of a typeclass for any given type parameter, a property known as typeclass coherence.
 */

trait OrderingA[T] {
  def compare(x: T, y: T): Int
  def lt(x: T, y: T): Boolean = compare(x, y) < 0
  def gt(x: T, y: T): Boolean = compare(x, y) > 0
}
trait NumericA[T] extends Ordering[T] {
  def plus(x: T, y: T): T
  def times(x: T, y: T): T
  def negate(x: T): T
  def zero: T
  def abs(x: T): T = if (lt(x, zero)) negate(x) else x
}

// We can now write functions for types that “have a” Numeric typeclass

def signOfTheTimesA[T](t: T)(implicit N: NumericA[T]): T = {
  import N._
  times(negate(abs(t)), t)
}

/*
By defining boilerplate on the companion of the typeclass
we can obtain the implicit with less noise
 */

/*
However, we never need to write the boilerPlate by using simulcrum
see: Ordering
 */

