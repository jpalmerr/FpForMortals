# Complexity

When our complexity is “infinity in, infinity out” we should introduce restrictive data types and validation closer to the point of input with Refined.

```
(A => C) => ((B => C) => C)

(c ^ (c ^ b)) ^ (c ^ a)
  = c ^ ((c ^ b) * (c ^ a))
  = c ^ (c ^ (a + b))

convert back to types

(Either[A, B] => C) => C

so only have to ask our users to go from

Either[A, B] => C
```

Same line of reasoning can show

```
A => B => C

is equivalent to

(A, B) => C
```

This is known as _curring_.

# Prefer Coproduct over Product

An archetypal modelling problem that comes up a lot is when there are mutually exclusive configuration parameters
a, b and c. 

The product `(a: Boolean, b: Boolean, c: Boolean)` has complexity 8 whereas the coproduct

```
 sealed abstract class Config
  object Config {
    case object A extends Config
    case object B extends Config
    case object C extends Config
}
```
has a complexity of 3.

It is better to model these configuration parameters as a coproduct rather than allowing 5 invalid states to exist.

 If a random sample of a data type has a low probability of being valid, it is a sign that the data is modelled incorrectly.