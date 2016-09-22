package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.timers.RawTimers
import scala.scalajs.js.{Date, JSApp}

case class Point(x: Int, y: Int) {
  def +(p: Point) = Point(x + p.x, y + p.y)

  def /(d: Int) = Point(x / d, y / d)
}

object ScalaJSExample extends JSApp {
  def main(): Unit = {
    val canvas = dom.document.getElementById("canvas")
      .asInstanceOf[html.Canvas]
    val renderer = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = dom.window.innerWidth
    canvas.height = dom.window.innerHeight

    val gradient = renderer.createLinearGradient(
      canvas.width / 2 - 100, 0, canvas.width / 2 + 100, 0
    )
    gradient.addColorStop(0, "red")
    gradient.addColorStop(0.5, "green")
    gradient.addColorStop(1, "blue")
    renderer.fillStyle = gradient
    //renderer.fillStyle = "black"

    renderer.textAlign = "center"
    renderer.textBaseline = "middle"

    def render() = {
      val date = new Date()
      renderer.clearRect(
        0, 0, canvas.width, canvas.height
      )
      renderer.font = "75px sans-serif"
      renderer.fillText(
        date.toLocaleTimeString(),
        canvas.width / 2,
        canvas.height / 2
      )
    }
    //    dom.setInterval(render _, 1000)
    RawTimers.setInterval(render _, 1000)
  }
}