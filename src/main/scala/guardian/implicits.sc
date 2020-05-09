def alert(msg: String): Unit = println(msg)

implicit def intToString(i: Int) = i.toString

alert(7) // contrived example but :)