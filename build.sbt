import scala.language.postfixOps

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "Scala.js Playground",
    scalaVersion := "2.11.8"
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0"
    )
  )
  .settings(
    skip in packageJSDependencies := false,
    jsDependencies += RuntimeDOM
  )
  .settings(
    persistLauncher in Compile := true,
    persistLauncher in Test := false
  )

lazy val npmInstall = taskKey[Unit]("Runs npm install")
npmInstall := println("""cmd /c "npm install source-map-support" """ !)
