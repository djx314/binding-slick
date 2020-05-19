scalaVersion := "2.12.7"

scalacOptions += "-Ypartial-unification" // 2.11.9+

transitiveClassifiers in ThisBuild := Seq("sources", "jar", "javadoc")

addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.0-M4")