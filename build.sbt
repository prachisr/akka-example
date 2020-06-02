name := "concurrency_akka"

version := "0.1"

scalaVersion := "2.12.7"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % "2.5.17")
