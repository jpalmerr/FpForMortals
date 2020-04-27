/*
The rule of thumb is that every <- (called a generator) is a nested flatMap call,
with the final generator a map containing the yield body.

If there is no yield, the compiler will use foreach instead of flatMap, which is only useful for side-effects.
 */

val a, b, c = Option(1)

for { i <- a ; j <- b } println(s"$i $j")
a.foreach { i => b.foreach { j => println(s"$i $j") } }

/*
If the context (C[_]) of a for comprehension doesn’t provide its own map and flatMap, all is not lost.
If an implicit cats.FlatMap[T] is available for T, it will provide map and flatMap.
 */
