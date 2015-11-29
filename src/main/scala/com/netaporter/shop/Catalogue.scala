package com.netaporter.shop

import scala.util.{Failure, Try}

case class Catalogue(products: Seq[Product]) {
  def findProduct(id: Int): Option[Product] = products.find(_.id == id)
}

object Catalogue {
  def load(csvResourceUrl: String): Catalogue = {
    val url = getClass.getResourceAsStream(csvResourceUrl)
    val src = scala.io.Source.fromInputStream(url)
    parse(src.getLines.toList)
  }

  def parse(csvLines: Seq[String]): Catalogue =  {
    val parsedProducts: Seq[Try[Product]] = csvLines.map{ line =>
      line.split(',') match {
        case Array(id, name, prize) => Try(Product(id.toInt, name, BigDecimal(prize.drop(1))))
        case _ => Failure(new IllegalArgumentException("Line does not define a Product: " + line))
      }
    }
    val products = parsedProducts.filter(_.isSuccess).map(_.get)
    Catalogue(products)
  }
}
