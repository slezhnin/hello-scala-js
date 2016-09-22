import scala.language.postfixOps

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "Scala.js Playground",
    scalaVersion := "2.11.8"
  )
  .settings(
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.9.0",
      "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
      "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
    )
  )
  .settings(testFrameworks += new TestFramework("utest.runner.Framework"))
  .settings(
    skip in packageJSDependencies := false,
    jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js",
    jsDependencies += RuntimeDOM
  )
  .settings(
    persistLauncher in Compile := true,
    persistLauncher in Test := false
  )

lazy val npmInstall = taskKey[Unit]("Runs npm install")
npmInstall := println("""cmd /c "npm install source-map-support" """ !)
