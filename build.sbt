scalaVersion := "2.13.2"

//scalacOptions += "-Ypartial-unification" // 2.11.9+

transitiveClassifiers in ThisBuild := Seq("sources", "jar")

scalafmtOnCompile := true

val macros  = (project in file(".") / "modules" / "macros")
val binding = (project in file(".")).dependsOn(macros).aggregate(macros)
