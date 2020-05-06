import cats._
import cats.data._
import cats.implicits._
import cats.free._
import cats.mtl._
import cats.effect._
import cats.effect.concurrent._
import simulacrum._

/*
If we cannot call side-effecting methods in our business logic, when can we write them?
In a Monad that delays execution until it is interpreted at the application’s entrypoint.
 */


/* Monad Transformer Library

It is typical that a transformer will implement methods named .mapK and .liftF
 */

case class User(name: String)

trait Twitter[F[_]] {
  def getUser(name: String): F[Option[User]]
  def getStars(user: User): F[Int]
}
def T[F[_]](implicit t: Twitter[F]): Twitter[F] = t

def stars[F[_]: Monad: Twitter](name: String): OptionT[F, Int] = for {
  user  <- OptionT(T.getUser(name))
  stars <- OptionT.liftF(T.getStars(user))
} yield stars

/*
An optional value is a special case of a value that may be an error,
where we don’t know anything about the error.
 */

/*
EitherT allows us to use any type we want as the error value,
providing contextual information about what went wrong.
 */

def starsWithError[F[_]: Monad: Twitter](name: String): EitherT[F, String, Int] = for {
  user  <- EitherT.fromOptionF(T.getUser(name), s"user '$name' not found")
  stars <- EitherT.right(T.getStars(user))
} yield stars