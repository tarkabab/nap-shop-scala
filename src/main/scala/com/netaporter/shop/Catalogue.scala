package com.netaporter.shop

import scala.util.Try

case class Catalogue(products: Seq[Product]) {
  def findProduct(id: Int): Option[Product] = products.find(_.id == id)
}

object Catalogue {
  def load(csvResourceUrl: String): Catalogue = {
    val url = getClass.getResourceAsStream(csvResourceUrl)
    val src = scala.io.Source.fromInputStream(url)
    parse(src.getLines.toList)
  }

  private[shop] def parse(csvLines: Seq[String]): Catalogue =  {
    val products = csvLines.flatMap(parseProduct)
    Catalogue(products)
  }

  private[shop] def parseProduct(line: String): Option[Product] = {
    // expected format: "3,Skinny Jeans,£45.00"
    line.split(',') match {
      case Array(id, name, prize) =>
        Try(Product(id.toInt, name, BigDecimal(prize.drop(1)))).toOption
      case _ =>
        None
    }
  }

}
