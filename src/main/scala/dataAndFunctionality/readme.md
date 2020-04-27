# Data and Functionality

FP takes a different approach to OOP, defining data and functionality separately.

## Algebraic Data Type (ADT)

The fundamental building blocks of data types are
- final case class also known as products
- sealed abstract class also known as coproducts 
- case object and Int, Double, String (etc) values

```
Here, we prefer abstract class to trait in order to get better binary compatibility
and to discourage trait mixing.
```

