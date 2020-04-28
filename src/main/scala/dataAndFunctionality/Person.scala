package dataAndFunctionality

/* Refined Data Types

Refined allows us to define Person using adhoc refined types to capture requirements exactly,
written A Refined B.
 */

import eu.timepit.refined
import refined.api.Refined

import refined.numeric.Positive
import refined.collection.NonEmpty

final case class RefinedPerson(name: String Refined NonEmpty,
                               age: Int Refined Positive
                              )

import refined.W
import refined.boolean.And
import refined.collection.MaxSize

object Workspace {

  type Name = NonEmpty And MaxSize[W.`10`.T] // The W notation is short for “witness”. This syntax will be much simpler in Scala 2.13, which has support for literal types:

  def maxSize(t: Int): t.type = t
  final val bar: 23 = maxSize(23)


  final case class Person(
                           name: String Refined Name,
                           age: Int Refined Positive
                         )

}
