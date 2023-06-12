libraryDependencies += "org.typelevel" %% "cats-core" % "2.9.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.0"
libraryDependencies += "dev.profunktor" %% "redis4cats-effects" % "1.4.1"
libraryDependencies += "com.github.cb372" %% "cats-retry" % "3.1.0"

val http4sVersion = "1.0.0-M39"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-ember-client" % http4sVersion,
  "org.http4s" %% "http4s-ember-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl"          % http4sVersion,
  "org.http4s" %% "http4s-circe"        % http4sVersion
)

libraryDependencies += "co.fs2" %% "fs2-core" % "3.7.0"


addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full)