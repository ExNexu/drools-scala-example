name := "drools-scala-example"

organization := "us.bleibinha"

version := "0.0.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "com.thoughtworks.xstream" % "xstream" % "1.4.7",
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"
)

val droolsVersion = "6.0.1.Final"

resolvers += "JBoss public" at "http://repository.jboss.org/nexus/content/groups/public/"

libraryDependencies ++= {
  "org.kie" % "kie-api" % droolsVersion ::
  List("drools-compiler", "drools-core","drools-jsr94", "drools-decisiontables", "knowledge-api")
    .map("org.drools" % _ % droolsVersion)
}

initialCommands := "import us.bleibinha.droolsscalaexample._"
