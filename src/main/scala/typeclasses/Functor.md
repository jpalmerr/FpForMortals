```
 @typeclass trait Functor[F[_]] {
    def map[A, B](fa: F[A])(f: A => B): F[B]
    ...
}
```

The only abstract method is `.map`, and it must compose.
- mapping with f and then again with g

is the same as 

- mapping once with the composition of f and g

```
fa.map(f).map(g) == fa.map(f.andThen(g))
```
 
The `.map` should also perform a `no-op` if the provided function is identity
- (i.e. x => x)

```
fa.map(identity) == fa
fa.map(x => x) == fa
```

Some convenience methods defined on Functor
```
 def void[A](fa: F[A]): F[Unit] = ...
 def fproduct[A, B](fa: F[A])(f: A => B): F[(A, B)] = ...
 def as[A, B](fa: F[A], b: B): F[B] = ...
 def tupleLeft[A, B](fa: F[A], b: B): F[(B, A)] = ...
 def tupleRight[A, B](fa: F[A], b: B): F[(A, B)] = ...
 def unzip[A, B](fab: F[(A, B)]): (F[A], F[B]) = ...
 
 // harder
 def lift[A, B](f: A => B): F[A] => F[B] = ...

```

`void`

- takes an instance of the `F[A]` and always returns an `F[Unit]`,
it forgets all the values whilst preserving the structure.

`fProduct`

- takes the same input as map but returns `F[(A, B)]`,

i.e. it tuples the contents with the result of applying the function.
This is useful when we wish to retain the input.

`as`

- ignores the content of the `F[A]` and replaces it with the `B`.

`tupleLeft`

- pairs the contents of an `F[A]` with a constant `B` on the left

`tupleRight`

- pairs the contents of an `F[A]` with a constant `B` on the right.

`unzip`

- splits a functor of tuples into a tuple of functors.

`lift`

- takes a function `A => B` and returns a `F[A] => F[B]`.
In other words, it takes a function over the contents of an
`F[A]` and returns a function that operates on the `F[A]` directly.

.as, .tupleLeft and .tupleRight are useful when we wish to retain some information that would
otherwise be lost to scope.
