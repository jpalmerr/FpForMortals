The biggest problem with `Future` is that it eagerly schedules work during construction.

- `Future` conflates the definition of a program with interpreting it
  (i.e. running it).
  
`Future` is also suboptimal from a performance perspective:

every time `.flatMap` is called
- a closure is submitted to an Executor
- resulting in unnecessary thread scheduling and context switching