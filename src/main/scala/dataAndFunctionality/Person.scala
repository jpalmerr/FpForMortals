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
