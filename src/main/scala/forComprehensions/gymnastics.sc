import scala.concurrent.Future
import scala.concurrent.ExecutionContext
import cats.implicits._

// fallback

/*
Say we are calling out to a method that returns an Option.
If it is not successful we want to fallback to another method (and so on and so on),
like when we’re using a cache

We have to be careful not to run both queries
 */

implicit val ec = ExecutionContext.global

def getFromRedis(s: String): Future[Option[String]] = ???
def getFromSql(s: String): Future[Option[String]] = ???

def queries(key: String): Future[Option[String]] = {
  for {
    cache <- getFromRedis(key)
    res <- cache match {
      case Some(_) => cache.pure[Future]
      case None => getFromSql(key)
    }
  } yield res
}

// early exit

// Say we have some condition that should exit early with a successful value

def getA: Future[Int] = ???
def getB: Future[Int] = ???

for {
  a <- getA
  c <- if (a <= 0) 0.pure[Future]
  else for { b <- getB } yield a * b
} yield c

// pure rather than Future