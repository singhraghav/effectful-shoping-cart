ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

ThisBuild / name := "effectful-shopping-cart"

//resolvers += Resolver.url("", url(""))

lazy val models = (project in file("models"))
  .settings(
    scalacOptions ++= List("-Ymacro-annotations", "-Yrangepos", "-Wconf:cat=unused:info")
  )
lazy val catsShoppingCart = (project in file("cats-shopping-cart")).dependsOn(models)

lazy val root = (project in file(".")).aggregate(models, catsShoppingCart)
  .settings(
    scalacOptions ++= List("-Ymacro-annotations", "-Yrangepos", "-Wconf:cat=unused:info")
  )
