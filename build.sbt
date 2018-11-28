scalaVersion := "2.12.7"

scalacOptions += "-Ypartial-unification" // 2.11.9+

transitiveClassifiers in ThisBuild := Seq("sources", "jar", "javadoc")
