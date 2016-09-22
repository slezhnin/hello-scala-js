package example

import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.timers.RawTimers
import org.scalajs.dom
import org.scalajs.dom.html
import scala.util.Random

case class Point(x: Int, y: Int) {
  def +(p: Point) = Point(x + p.x, y + p.y)

  def /(d: Int) = Point(x / d, y / d)
}

@JSExport
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d")
      .asInstanceOf[dom.CanvasRenderingContext2D]

    val W = ctx.canvas.width
    val W2 = W / 2 + 1
    val H = ctx.canvas.height
    var count = 0
    var p = Point(0, 0)
    val corners = Seq(Point(W, H), Point(0, H), Point(W2, 0))

    def clear() = {
      ctx.fillStyle = "black"
      ctx.fillRect(0, 0, W, H)
    }

    def run() = for (i <- 0 until 10) {
      if (count % 3000 == 0) clear()
      count += 1
      p = (p + corners(Random.nextInt(3))) / 2

      val height = (H * 2).toFloat / (H + p.y)
      val r = (p.x * height).toInt
      val g = ((H - p.x) * height).toInt
      val b = p.y
      ctx.fillStyle = s"rgb($g, $r, $b)"

      ctx.fillRect(p.x, p.y, 1, 1)
    }

    //    dom.setInterval(() => run(), 50)
    RawTimers.setInterval(() => run(), 50)
  }
}