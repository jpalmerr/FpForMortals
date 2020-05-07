name := "FpForMortals"

version := "0.1"

scalaVersion := "2.13.1"

scalacOptions in ThisBuild ++= Seq(
  "-language:_",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)
libraryDependencies ++= Seq(
  "org.typelevel"        %% "simulacrum"    % "1.0.0",
  "org.typelevel"        %% "cats-core"     % "2.1.1",
  "org.typelevel"        %% "mouse"         % "0.24",
  "eu.timepit"           %% "refined"       % "0.9.14",
  "eu.timepit"           %% "refined-cats"  % "0.9.14",
  "eu.timepit" %% "refined-shapeless"       % "0.9.14",
  "org.typelevel"        %% "cats-mtl-core" % "0.7.1",
  "org.typelevel"        %% "cats-free"     % "2.1.1",
  "org.typelevel"        %% "cats-effect"   % "2.1.3",
  "com.chuusai"          %% "shapeless"     % "2.3.3"

)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
