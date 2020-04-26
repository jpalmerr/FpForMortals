import cats._
import cats.data._
import cats.implicits._
import simulacrum._
import scala.concurrent.Future
// Say we want to interact with the user over the command line interface.

trait InteractTerminalSync {
  def read(): String
  def write(t: String): Unit
}
trait InteractTerminalAsync {
  def read(): Future[String]
  def write(t: String): Future[Unit]
}

/*
We could write a synchronous version and wrap it with Future
- but now we have to worry about which thread pool we should be using for the work
Or we could Await.result on the Future and introduce thread blocking.
- In either case, it is a lot of boilerplate and we are fundamentally dealing with different APIs that are not unified.
 */


// Higher Kinded Types

/*
We want to define Terminal for a type constructor C[_].
By defining Now to construct to its type parameter (like Id),
we can implement a common interface for synchronous and asynchronous terminals:
 */

trait Terminal[C[_]] {
  def read: C[String]
  def write(t: String): C[Unit]
}

type Now[X] = X

object TerminalSync extends Terminal[Now] {
  def read: String = ???
  def write(t: String): Unit = ???
}

object TerminalAsync extends Terminal[Future] {
  def read: Future[String] = ???
  def write(t: String): Future[Unit] = ???
}

// We can think of C as a Context because we say “in the context of executing Now” or “in the Future”.

/*
 What we need is a kind of execution environment that lets us call a method returning C[T]
 and then be able to do something with the T, including calling another method on Terminal.

 We also need a way of wrapping a value as a C[_].
 */

trait Execution[C[_]] {
  def chain[A, B](c: C[A])(f: A => C[B]): C[B]
  def create[B](b: B): C[B]
}

object Execution {
  implicit class Ops[A, C[_]](c: C[A]) {
    def flatMap[B](f: A => C[B])(implicit e: Execution[C]): C[B] =
      e.chain(c)(f)
    def map[B](f: A => B)(implicit e: Execution[C]): C[B] =
      e.chain(c)(f andThen e.create)

    def echo[C[_]](implicit t: Terminal[C], e: Execution[C]): C[String] =
      for {
        in <- t.read
        _ <- t.write(in)
      } yield in
  }
}

/*
Our Execution has the same signature as a trait in Cats called Monad,
except chain is flatMap and create is pure.

We say that C is monadic when there is an implicit Monad[C] available.

In addition, Cats has the Id type alias.
 */

/* Conclusion

If we write methods that operate on monadic types,
then we can write sequential code that abstracts over its execution context.
 */