| **Typeclass** | **Method** | **From** | **Given** | **To** |
| :---:         |     :---:      |      :---:    |     :---:      |     :---:      |
| Functor       |    map     | F[A]    | A => B    | F[B]   |
| Applicative   | pure       | A       |           | F[A]   |
| Monad         | flatMap    | A       | A => F[B] | F[B]   |
| Traverse      | sequence   | F[G[A]] | A => B    | G[F[A]]|

`Band` has the law that the `.combine` operation of the same two elements is idempotent
- i.e. gives the same value.

Examples are anything that can only be one value, such as Unit, least upper bounds, or a Set.

Band provides no further methods yet users can make use of the guarantees for performance optimisation.

`Semilattice` goes one further and adds the additional guarantee that the order of the parameters in `.combine` does not matter.

A `Group` is a Monoid where:
- every value has an inverse, that when combined gives the `.empty` element.

For example, every `Int` has an inverse which is its negated value.

