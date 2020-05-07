# Performance

The biggest problem with Monad Transformers is their performance overhead.

`EitherT` has a reasonably low overhead, with every `.flatMap` call generating a handful of objects,
 but this can impact high throughput applications where every object allocation matters.
 
 