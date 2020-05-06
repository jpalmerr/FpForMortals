| **Effect** | **Underlying** | **Transformer** | **Typeclass** |
| :---:        |     :---:      |      :---:    |     :---:      |
|optionality| F[Option[A]] | OptionT | MonadError |
|errors| F[Either[E, A]] | EitherT | ApplicativeLocal |
|a runtime value| A => F[B] | ReaderT | FunctorListen |
|journal / multitask evolving state| S => F[(S, A)] | WriterT | MonadState |
|keep calm & carry on| F[Ior[E, A]]| IorT | MonadChronicle |
