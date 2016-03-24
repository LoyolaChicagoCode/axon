
import axon._

object X extends App {


  // All of this is to support the simple XML document I described in our office.

  // macros will probably replace all 3 of these with 3 lines of code!

  case class Complex(name: String, nested: Seq[MarkupLike]) extends MarkupLike

  object Complex extends MarkupLike {

    def apply(nested: Any*) = {
      new Complex("complex", unbundle(nested))
    }

  }

  case class Real(name: String, nested: Seq[MarkupLike]) extends MarkupLike

  object Real extends MarkupLike {

    def apply(nested: Any*) = {
      new Real("real", unbundle(nested))
    }

  }

  case class Imag(name: String, nested: Seq[MarkupLike]) extends MarkupLike

  object Imag extends MarkupLike {

    def apply(nested: Any*) = {
      new Real("imaginary", unbundle(nested))
    }

  }

 
  // This is the composite object style

  val myAppMarkup = Complex(
    Real("value" -> 2.5),
    Imag("value" -> 3.5)
  )

  // This is JSON style

  val myAltMarkup = Complex {
    Real {
      "value" -> 2.5
    }
    Imag {
      "value" -> 3.5
    }
  }

  // TODO: This just prints the AST. We will have a toXML or what not.
  
  println(myAppMarkup)
}
