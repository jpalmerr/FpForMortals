# Applicative and Monad

In many ways, Applicative and Monad are the culmination of everything we’ve seen in this chapter.

`.pure` allows us to create effects or data structures from values.

Instances of `Applicative` must meet some laws, effectively asserting that all the methods are consistent:

- Identity
- Homomorphism: 
    - `pure(a) <*> pure(ab) === pure(ab(a))` (where ab is an A => B),
    - i.e. applying a pure function to a pure value is the same as
    applying the function to the value and then using pure on the result.
- Interchange:
    - `pure(a) <*> fab === fab <*> pure(f => f(a))`, (where fab is an F[A => B])
    - i.e. pure is a left and right identity
- Mappy
    - `map(fa)(f) === fa <*> pure(f)`        
    
`Monads` add some extra laws:

- Left Identity:
    - `pure(a).flatMap(f) === f(a)`
- Right Identity:
    - `a.flatMap(pure(_)) === a` 
- Associativity:
    - `fa.flatMap(f).flatMap(g) === fa.flatMap(a => f(a).flatMap(g))`
    - where fa is anF[A],f is anA => F[B]and g is aB => F[C].
   
`Associativity` says that chained flatMap calls must agree with nested flatMap.
However, it does not mean that we can rearrange the order, which would be `commutativity`. 