package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.JSApp

object ScalaJSExample extends JSApp {

  def main(): Unit = {
    val canvas = dom.document.getElementById("canvas")
      .asInstanceOf[html.Canvas]
    val renderer = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.width = dom.window.innerWidth
    canvas.height = dom.window.innerHeight

    renderer.fillStyle = "#f8f8f8"
    renderer.fillRect(0, 0, canvas.width, canvas.height)

    renderer.fillStyle = "black"
    var down = false
    canvas.onmousedown =
      (e: dom.MouseEvent) => down = true

    canvas.onmouseup =
      (e: dom.MouseEvent) => down = false

    val rect = canvas.getBoundingClientRect()

    canvas.onmousemove = {
      (e: dom.MouseEvent) => if (down) {
        renderer.fillRect(
          e.clientX - rect.left,
          e.clientY - rect.top,
          5, 5
        )
      }
    }
  }
}