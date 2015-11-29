package com.netaporter.shop

case class Catalogue(products: Seq[Product]) {
  // TODO: Complete
  def findProduct(id: Int): Option[Product] = ???
}

object Catalogue {
  def load(csvResourceUrl: String): Catalogue = {
    val url = getClass.getResourceAsStream(csvResourceUrl)
    val src = scala.io.Source.fromInputStream(url)
    parse(src.getLines.toList)
  }

  // TODO: Implement
  def parse(csvLines: Seq[String]): Catalogue = ???
}
