package example

import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.JSApp
import scalatags.JsDom.all._

object ScalaJSExample extends JSApp {
  def main(): Unit = {
    hello()
    userInput()
    search()
    searchHi()
    weather()
  }

  def hello(): Unit = {
    val target = dom.document.getElementById("hello")
      .asInstanceOf[html.Div]
    val (animalA, animalB) = ("fox", "dog")
    target.appendChild(
      div(
        h2("Hello, World!"),
        p(
          "The quick brown ", b(animalA),
          " jumps over the lazy ", i(animalB), "."
        )
      ).render
    )
  }

  def userInput(): Unit = {
    val target = dom.document.getElementById("userInput")
      .asInstanceOf[html.Div]
    val box = input(
      `type` := "text",
      placeholder := "Type here!"
    ).render
    val output = span.render
    box.onkeyup = (e: dom.Event) => {
      output.textContent = box.value.toUpperCase
    }
    target.appendChild(
      div(
        h2("Capital Box"),
        p("Type here and have it capitalized!"),
        div(box),
        div(output)
      ).render
    )
  }

  def searchRender(renderListing: (html.Input, Seq[String]) => dom.Element, listing: Seq[String]): Unit = {
    val target = dom.document.getElementById("search")
      .asInstanceOf[html.Div]
    val box = input(
      `type` := "text",
      placeholder := "Type here!"
    ).render
    val output = div(renderListing(box, listing)).render
    box.onkeyup = (e: dom.Event) => {
      output.innerHTML = ""
      output.appendChild(renderListing(box, listing))
    }
    target.appendChild(
      div(
        h2("Search Box"),
        p("Type here to filter the list of things below!"),
        div(box),
        output
      ).render
    )
  }

  def search(): Unit = {
    searchRender((box: html.Input, listing: Seq[String]) => ul(
      for {
        fruit <- listing
        if fruit.toLowerCase.startsWith(
          box.value.toLowerCase
        )
      } yield li(fruit)
    ).render, Seq(
      "Apple", "Apricot", "Banana", "Cherry",
      "Mango", "Mangosteen", "Mandarin",
      "Grape", "Grapefruit", "Guava"
    ))
  }

  def searchHi(): Unit = {
    searchRender((box: html.Input, listing: Seq[String]) => ul(
      for {
        fruit <- Seq(
          "Apple", "Apricot", "Banana", "Cherry",
          "Mango", "Mangosteen", "Mandarin",
          "Grape", "Grapefruit", "Guava"
        )
        if fruit.toLowerCase.startsWith(
          box.value.toLowerCase
        )
      } yield {
        val (first, last) = fruit.splitAt(box.value.length)
        li(
          span(
            backgroundColor := "yellow",
            first
          ),
          last)
      }
    ).render, Seq(
      "Apple", "Apricot", "Banana", "Cherry",
      "Mango", "Mangosteen", "Mandarin",
      "Grape", "Grapefruit", "Guava"
    ))
  }

  def weather(): Unit = {
    import dom.ext._

    import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
    val target = dom.document.getElementById("weather")
      .asInstanceOf[html.Div]
    val url = "http://api.openweathermap.org/data/2.5/weather?q=Singapore"
    val get = Ajax.get(url)
    get.onFailure { case t =>
      target.appendChild(
        pre("Error: " + t).render
      )
    }
    get.onSuccess { case xhr =>
      target.appendChild(
        pre(xhr.responseText).render
      )
    }
  }
}