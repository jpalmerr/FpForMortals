import cats.implicits._

/* Appendable things

A Semigroup can be defined for a type if two values can be combined.
The operation must be associative

(a |+| b) |+| c == a |+| (b |+| c)
 */

((1 |+| 2) |+| 3) == (1 |+| (2 |+| 3))

/* A Monoid is a Semigroup with an empty element.

a |+| empty == a
*/

(1 |+| 0) == 1

List(1, 2) |+| List(3, 4) // List(1, 2, 3, 4)


