package com.netaporter.shop

case class Basket(items: Map[Product, Int]) {

  def add(item: Product, qty: Int): Basket = {
    require(qty > 0, "Quantity should be greater than zero")

    val sumQuantity = qty + items.getOrElse(item, 0)
    Basket(items.updated(item, sumQuantity))
  }

  def remove(item: Product, qty: Int): Basket = {
    require(items.contains(item), "The item should be already in the Basket")
    require(items(item) >= qty, "The quantity of the item to be removed should be less than or equal to the quantity of the item in the Basket")

    val reducedQuantity = items(item) - qty
    if(reducedQuantity == 0)
      Basket(items - item)
    else
      Basket(items.updated(item, reducedQuantity))
  }

  def total: Double = items
    .map { case (item: Product, qty: Int) => qty * item.price }
    .sum
    .toDouble

  def contains(item: Product): Boolean = items.contains(item)

  def count(item: Product): Int = items.getOrElse(item, 0)
}
