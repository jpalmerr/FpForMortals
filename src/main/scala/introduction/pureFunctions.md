Pure functions have three properties

Total: return a value for every possible input
Deterministic: return the same value for the same input
Inculpable: no (direct) interaction with the world or program state.

We write pure functions by avoiding exceptions,
and interacting with the world only through a safe F[_] execution context.