package tutorial.webapp

import org.scalajs.dom
import dom.document
import org.scalajs.jquery.jQuery

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


object TutorialApp extends JSApp {
  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  @JSExport
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }

  def addClickedMessageFromJQ(): Unit = {
    appendPar(document.body, "You clicked the button from jQuery!")
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(addClickedMessageFromJQ _)
    jQuery("body").append("<p>Hello World</p>")
    jQuery("""<button type="button">Click me!</button>""")
      .click(addClickedMessage _)
      .appendTo(jQuery("body"))
  }

  def main(): Unit = {
    println("Hello world!")
    appendPar(document.body, "Hello World")
    jQuery(setupUI _)
  }
}
