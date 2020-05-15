# IO

```scala
// delayed evaluation of side-effecting / non-total code block
    def apply[A](a: =>A): IO[A] = ...
    // eager evaluation of an existing value
    def pure[E, A](a: A): IO[A] = ...
    // create a failed IO
    def raiseError[A](error: Throwable): IO[A] = ...
    // convert a delayed Future into an IO
    def fromFuture[A](iof: IO[Future[A]])(implicit C: ContextShift[IO]): IO[A] = ...
    // asynchronously sleeps for a specific period of time
    def sleep(d: FiniteDuration)(implicit T: Timer[IO]): IO[Unit] = ...
```

IO provides a typeclass instance for `MonadError[Throwable, ?]`
along with new typeclasses that are introduced by cats-effect

# Bracket

`.bracket` is the most powerful part of the interface, allowing us to:
- define how we obtain a resource
- what we do with it
- and anything we need to do to release it

In addition to success and failure, a calculation can also be canceled,
and we may cleanup differently depending on these three scenarios with `.bracketCase`

# Defer

Defer is the higher kinded equivalent of `Eval.always`

```scala
 @typeclass trait Defer[F[_]] {
    def defer[A](fa: => F[A]): F[A]
}
```
and is useful when we want to avoid an expensive calculation until it is necessary.

# Sync

*Something used at work a lot!*

Sync refines the Bracket (and MonadError) error type to Throwable
and introduces `.suspend`,
- which is effectively the same as `Defer.defer` but explicitly for effects 

and
 
`.delay` 
- as the mechanism for side-effecting blocks of code.

`Sync.delay` is the generalised version of IO { ... }
  
``` 
@typeclass trait Sync[F[_]] extends Bracket[F, Throwable] with Defer[F] {
    def suspend[A](thunk: => F[A]): F[A]
    def delay[A](thunk: => A): F[A] = ...
}
```

# Effect 

`Effect` is the opposite of `LiftIO`

means that the effect can be converted into the concrete IO implementation.
```
  @typeclass trait Effect[F[_]] extends Async[F] {
    def toIO[A](fa: F[A]): IO[A]
}
```

# Concurrent

``` 
  @typeclass trait Concurrent[F[_]] extends Async[F] {
    def race[A, B](fa: F[A], fb: F[B]): F[Either[A, B]] = ...
    ...
}
```

# Concurrent Effect

```
@typeclass trait ConcurrentEffect[F[_]] extends Concurrent[F] with Effect[F]
```

