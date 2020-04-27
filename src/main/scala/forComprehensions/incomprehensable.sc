import cats.data.OptionT
import cats.implicits._
import scala.concurrent.{ExecutionContext, Future}

implicit val ec = ExecutionContext.global

/*
The context we’re comprehending over must stay the same: we cannot mix contexts.

But when we have nested contexts the intention is usually obvious yet the compiler still doesn’t accept our code.

Hiding the outer context is exactly what a monad transformer does,
and Cats provides implementations for Option and Either named OptionT and EitherT respectively.
 */

def getA: Future[Option[Int]]
def getB: Future[Option[Int]]

val result: OptionT[Future, Int] = for {
  a <- OptionT(getA)
  b <- OptionT(getB)
} yield a * b

val ab: Future[Option[Int]] = result.value

// Monad transformers also allow us to "lift" contexts

def getC: Future[Int]
def getD: Option[Int]
val resultWithOtherContexts = for {
  a <- OptionT(getA)
  b <- OptionT(getB)
  c <- OptionT.liftF(getC)
  d <- OptionT(getD.pure[Future])
} yield (a * b) / (c * d)

val abcd: Future[Option[Int]] = resultWithOtherContexts.value

// We can clean it up with a DSL

def liftFutureOption[A](f: Future[Option[A]]) = OptionT(f)
def liftFuture[A](f: Future[A]) = OptionT.liftF(f)
def liftOption[A](o: Option[A]) = OptionT(o.pure[Future])
def lift[A](a: A)               = liftOption(Option(a))

import mouse.all._

// which applies the function on the right to the value on the left

val tidyResult = for {
  a <- getA    |> liftFutureOption
  b <- getB    |> liftFutureOption
  c <- getC    |> liftFuture
  d <- getD    |> liftOption
  e <- 10      |> lift
} yield e * (a * b) / (c * d)
