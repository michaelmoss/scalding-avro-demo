import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    ScalaToolsSnapshots,
    "Concurrent Maven Repo" at "http://conjars.org/repo" // For Scalding, Cascading etc
  )

  object V {
    val scalding  = "0.9.0rc4"
    val hadoop    = "0.20.2"
    val specs2    = "1.12.4.1" // -> "1.13" when we bump to Scala 2.10.0
    // Add versions for your additional libraries here...
  }

  object Libraries {
    val scaldingCore = "com.twitter"                %%  "scalding-core"       % V.scalding
    val scaldingAvro = "com.twitter"                %%  "scalding-avro"       % V.scalding
    val hadoopCore   = "org.apache.hadoop"          % "hadoop-core"           % V.hadoop
    // Add additional libraries from mvnrepository.com (SBT syntax) here...

    // Scala (test only)
    val specs2       = "org.specs2"                 %% "specs2"               % V.specs2       % "test"
  }
}