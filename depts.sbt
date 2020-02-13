val doobieVersion = "0.8.8"

libraryDependencies ++= Seq(
    // Start with this one
  "org.tpolecat" %% "doobie-core" % doobieVersion,
  // And add any of these as needed
  "org.tpolecat" %% "doobie-h2"        % doobieVersion, // H2 driver 1.4.197 + type mappings.
  "org.tpolecat" %% "doobie-hikari"    % doobieVersion, // HikariCP transactor.
  "org.tpolecat" %% "doobie-postgres"  % doobieVersion, // Postgres driver 42.2.5 + type mappings.
  "org.tpolecat" %% "doobie-specs2"    % doobieVersion % Test, // Specs2 support for typechecking statements.
  "org.tpolecat" %% "doobie-scalatest" % doobieVersion % Test, // ScalaTest support for typechecking statements.
  "org.tpolecat" %% "doobie-h2"        % doobieVersion % Test
)

libraryDependencies ++= Seq(
    "com.typesafe.slick" %% "slick"          % "3.3.2"
  , "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2"
  , "com.h2database"     % "h2"              % "1.4.197"
)

libraryDependencies ++= Seq("org.slf4j" % "slf4j-simple" % "1.7.25")