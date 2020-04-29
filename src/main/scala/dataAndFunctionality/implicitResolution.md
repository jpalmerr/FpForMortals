We’ve discussed implicits a lot:

this section is to clarify what implicits are and how they work.


*Implicit parameters* are when a method requests that a unique instance
of a particular type is in the implicit scope of the caller,
with special syntax for typeclass instances.
 
Implicit parameters are a clean way to thread configuration through an application.

In this example, foo requires that typeclass instances of Numeric and Typeable are available for A,
as well as an implicit Handler object that takes two type parameters

```
def foo[A: Numeric: Typeable](implicit A: Handler[String, A]) = ???
```

*Implicit conversion* is when an implicit def exists.

One such use of implicit conversions is to enable extension methodology.
When the compiler is resolving a call to a method,
- it first checks if the method exists on the type,
- then its ancestors 

If it fails to find a match
- it will search the implicit scope for conversions to other types,
- then search for methods on those types.

*Typeclass derivation*:

 It is possible to chain together many implicit def (including recursively) which is the basis of typeful programming,
 allowing for computations to be performed at compile time rather than runtime.
 
 
 
 If two matching implicits are found in the same phase of implicit resolution,
 an `ambiguous implicit` error is raised. (Something I've seen before)
 
 Implicits are often defined on a trait, which is then extended by an object.
 This is to try and control the priority of an implicit relative to another more specific one, to avoid ambiguous implicits.