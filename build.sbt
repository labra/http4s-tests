enablePlugins(JavaAppPackaging)

name := "http4s-tests"

version := "0.1"

scalaVersion := "2.11.8"

val http4sVersion = "0.14.11a"

lazy val http4stest = (project in file("."))
  .enablePlugins(SbtTwirl)

libraryDependencies ++= Seq(
  "org.http4s"     %% "http4s-dsl"            % http4sVersion,
  "org.http4s"     %% "http4s-twirl"          % http4sVersion,
  "org.http4s"     %% "http4s-blaze-server"   % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "ch.qos.logback" % "logback-classic"        % "1.1.3"
)
