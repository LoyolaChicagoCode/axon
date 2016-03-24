package object axon {

  trait MarkupLike

  // todo: Probably belongs in a base class.. mainly used for element constrution
  // from Any* (to allow for DSL-like usage; see main.scala)

  def unbundle(nested: Seq[Any]): Seq[MarkupLike] = {
    nested map {
      item =>
        item match {
          case (key: String, value: String) => Attribute(key, value)
          case (key: String, value: Int) => Attribute(key, value)
          case (key: String, value: Double) => Attribute(key, value)
          case text: String => Text(text)
          case other: MarkupLike => other
          case _ => new Text("Error matching " + item.toString)
        }
    }
  }

  // We want to support common leaf attribute uses where T = String, Int, Double

  case class Attribute[T](key: String, value: T) extends MarkupLike

  // for XML character data

  case class Text(text: String) extends MarkupLike

}
