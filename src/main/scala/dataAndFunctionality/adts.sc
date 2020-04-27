/*
Recursive ADTs

When an ADT refers to itself, we call it a Recursive Algebraic Data Type.
 */

sealed abstract class List[+A]
case object Nil extends List[Nothing]
final case class ::[+A](head: A, tail: List[A]) extends List[A]

/*
Exhaustivity

It is important that we use sealed abstract class, not just abstract class, when defining a data type.
Sealing a class means that all subtypes must be defined in the same file,
allowing the compiler to know about them in pattern match exhaustivity checks and in macros that eliminate boilerplate
 */

// Alternative Products

sealed abstract class Accepted
final case class AcceptedString(value: String) extends Accepted
final case class AcceptedLong(value: Long) extends Accepted
final case class AcceptedBoolean(value: Boolean) extends Accepted

/*
Pattern matching on these forms of coproduct can be tedious,
which is why Union Types2 are a Scala 3 language feature.
 */

/* Convey Information

Besides being a container for necessary business information, data types can be used to encode constraints
 */

final case class NonEmptyList[A](head: A, tail: List[A]) // can never be empty!

final case class Person private(name: String, age: Int)

object Person {
  def apply(name: String, age: Int): Either[String, Person] = {
    if (name.nonEmpty && age > 0) Right(new Person(name, age))
    else Left(s"bad input: $name, $age")
  }
}

def welcome(person: Person): String =
  s"${person.name} you look wonderful at ${person.age}!"
for {
  person <- Person("", -1)
} yield welcome(person)
for {
  person <- Person("James", 23)
} yield welcome(person)
