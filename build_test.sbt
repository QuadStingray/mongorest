Defaults.itSettings

Test / parallelExecution := false

IntegrationTest / parallelExecution := false

Test / scalacOptions ++= Seq("-Yrangepos")

// Test
libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test

libraryDependencies += "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "3.5.4" % Test

val circeVersion     = "0.14.4"
val sttClientVersion = "3.8.10"

libraryDependencies += "com.softwaremill.sttp.client3" %% "circe"             % sttClientVersion % Test
libraryDependencies += "io.circe"                      %% "circe-core"        % circeVersion     % Test
libraryDependencies += "io.circe"                      %% "circe-generic"     % circeVersion     % Test
libraryDependencies += "io.circe"                      %% "circe-parser"      % circeVersion     % Test
