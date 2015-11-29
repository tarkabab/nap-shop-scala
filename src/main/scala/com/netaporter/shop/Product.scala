package com.netaporter.shop

case class Product(id: Int, name: String, price: BigDecimal)

object Product {
  implicit def ordering[A <: Product]: Ordering[A] = Ordering.by(_.id)
}