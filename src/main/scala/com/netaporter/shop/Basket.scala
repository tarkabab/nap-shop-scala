package com.netaporter.shop

case class Basket(items: Map[Product, Int]) {
  // TODO: Implement
  def add(item: Product, qty: Int): Basket = ???

  // TODO: Implement
  def remove(item: Product, qty: Int): Basket = ???

  // TODO: Implement
  def total: Double = ???
}
