There are two effectful operations that we almost always want to run in parallel:

- `.map` over a collection of effects, returning a single effect.
This is achieved by `.traverse.`

- running a fixed number of effects with `.mapN`
  and combining their output,delegating to `.map2`.
  
However, in practice, neither of these operations execute in parallel by default.

The reason is that if our `F[_]` is implemented by a Monad...
combinator laws for mapsmust be satisfied

**Monad is explicitly forbidden from running effects in parallel.**

However, if we have an F[_] that is not monadic, then it may implement in parallel.

`Parallel` type class

- gives us a way of moving from the current (sequential) context into a parallel one where
  `.traverse` and `.mapN` run effects in parallel
  
Monadic programs can then request an implicit `Parallel` in addition to their Monad

```scala
def foo[F[_]: Monad: Parallel]: F[Unit] = ???
```    

You then have access to convenience methods like `.parMapN`