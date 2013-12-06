import sbt._
import Keys._

object BuildSettings {

  lazy val basicSettings = Seq[Setting[_]](
    organization  := "Michael Moss",
    version       := "1.0",
    description   := "Scalding Avro Example",
    scalaVersion  := "2.9.3", // -> 2.10.0 when Scalding is ready
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    resolvers     ++= Dependencies.resolutionRepos
  )

  import sbtassembly.Plugin._
  import AssemblyKeys._
  lazy val sbtAssemblySettings = assemblySettings ++ Seq(

    jarName in assembly <<= (name, version) { (name, version) => name + "-" + version + ".jar" },
    
    // Drop these jars
    excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
      val excludes = Set(
        "jsp-api-2.1-6.1.14.jar",
        "jsp-2.1-6.1.14.jar",
        "jasper-compiler-5.5.12.jar",
        "minlog-1.2.jar", // Otherwise causes conflicts with Kyro (which bundles it)
        "janino-2.5.16.jar", // Janino includes a broken signature, and is not needed anyway
        "commons-beanutils-core-1.8.0.jar", // Clash with each other and with commons-collections
        "commons-beanutils-1.7.0.jar",      // "
        "hadoop-core-0.20.2.jar", // Provided by Amazon EMR. Delete this line if you're not on EMR
        "hadoop-tools-0.20.2.jar" // "
      ) 
      cp filter { jar => excludes(jar.data.getName) }
    }

/*
    mergeStrategy in assembly <<= (mergeStrategy in assembly) {
      (old) => {
        case "project.clj" => MergeStrategy.discard // Leiningen build files
        case x => old(x)
      }
    }
*/
  )


  lazy val buildSettings = basicSettings ++ sbtAssemblySettings
}