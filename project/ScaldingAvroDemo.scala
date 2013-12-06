import sbt._
import Keys._

object ScaldingAvroDemo extends Build {

  import Dependencies._
  import BuildSettings._

  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  lazy val project = Project("scalding-avro-demo", file("."))
    .settings(buildSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        Libraries.scaldingCore,
        Libraries.scaldingAvro,
        Libraries.hadoopCore,
        Libraries.specs2
      )
    )
}
