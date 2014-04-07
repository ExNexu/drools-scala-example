name := "drools-scala-example"

organization := "us.bleibinha"

version := "0.0.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
)

initialCommands := "import us.bleibinha.droolsscalaexample._"

