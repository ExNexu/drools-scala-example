name := "drools-scala-example"

organization := "us.bleibinha"

version := "0.0.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  //"com.thoughtworks.xstream" % "xstream" % "1.4.7",
  "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
)

libraryDependencies ++= {
  Seq("drools-compiler", "drools-core","drools-jsr94", "drools-decisiontables", "knowledge-api")
    .map("org.drools" % _ % "6.0.1.Final")
}

initialCommands := "import us.bleibinha.droolsscalaexample._"