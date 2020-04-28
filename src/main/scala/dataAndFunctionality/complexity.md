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