import cats.data.ValidatedNel

/*
Validated intentionally does not have an instance of any Monad,
restricting itself to success-biased versions of Applicative.

The big advantage of restricting to Applicative is that Validated is explicitly for situations
where we wish to report all failures,
whereas Either is used to stop at the first failure.
To accommodate failure accumulation, a popular form of Validated is ValidatedNel,
having a NonEmptyList[E] in the failure position.
 */

final case class Username(value: String) extends AnyVal
final case class Fullname(value: String) extends AnyVal
final case class Credentials(user: Username, name: Fullname)

def username(in: String): Either[String, Username] =
  if (in.isEmpty) Left("empty username")
  else if (in.contains(" ")) Left("username contains spaces")
  else Right(Username(in))


def realname(in: String): Either[String, Fullname] =
  if (in.isEmpty) Left("empty real name")
  else Right(Fullname(in))

for {
  u <- username("zara turtle")
  r <- realname("")
} yield Credentials(u, r) // Left(username contains spaces)

// we only get first failure as Either is a Monad

import cats.implicits._

def usernameValidated(in: String): ValidatedNel[String, Username] =
  if (in.isEmpty) "empty username".invalidNel
  else if (in.contains(" ")) "username contains spaces".invalidNel
  else Username(in).valid


def realnameValidated(in: String): ValidatedNel[String, Fullname] =
  if (in.isEmpty) "empty real name".invalidNel
  else Fullname(in).valid

(usernameValidated("zara turtle"), realnameValidated("")).mapN (Credentials.apply)

// Invalid(NonEmptyList(username contains spaces, empty real name))