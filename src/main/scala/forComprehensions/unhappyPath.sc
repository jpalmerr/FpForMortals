/*
So far we’ve only looked at the rewrite rules, not what is happening in map and flatMap.
Consider what happens when the for context decides that it cannot proceed any further.

In the Option example, the yield is only called when i,j,k are all defined.
If any of a,b,c are None, the comprehension short-circuits with None but it doesn’t tell us what went wrong
 */
val a, b, c = Option(1)

for {
  i <- a
  j <- b
  k <- c
} yield (i + j + k)

// either - much better for error reporting

val aa = Right(1)
val bb = Right(2)
val cc: Either[String, Int] = Left("sorry, no c")
//val cc = Right(3)

for { i <- aa ; j <- bb ; k <- cc } yield (i + j + k)

/*
Short circuiting for the unhappy path is a common and important theme.

It puts a clear ownership of responsibility for unexpected error recovery
and resource cleanup onto the context
(which is usually a Monad), not the business logic.
 */