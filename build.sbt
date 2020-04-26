name := "FpForMortals"

version := "0.1"

scalaVersion := "2.13.2"

scalacOptions in ThisBuild ++= Seq(
  "-language:_",
  "-Xfatal-warnings",
  "-Ymacro-annotations"
)
libraryDependencies ++= Seq(
  "org.typelevel"        %% "simulacrum" % "1.0.0",
  "org.typelevel"        %% "cats-core" % "2.1.1"
)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
