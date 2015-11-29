package com.netaporter.shop

import org.scalatest.FunSpec

class BasketSpec extends FunSpec {

  val emptyBasket = Basket(Map[Product, Int]())
  val product1 = Product(1, "dummy product 1", BigDecimal(1.00))
  val product2 = Product(2, "dummy product 2", BigDecimal(10.00))
  val product3 = Product(3, "dummy product 3", BigDecimal(100.00))

  describe("The add method") {
    it("should return with a Basket with the quantity of items added") {
      val product = product1
      val quantity = 5

      val basket = emptyBasket.add(product, quantity)
      assert(basket.items(product) == quantity, "Quantity of items added to empty Basket does not match")

      val basket2 = basket.add(product, quantity)
      assert(basket2.items(product) == 2 * quantity, "Quantity of items added twice does not match")
    }
  }

  describe("The remove method") {
    it("should return with a Basket, with the quantity of items removed") {
      val product = product1
      val quantity = 5
      val removedQuantity = 2
      val expectedQuantity = quantity - removedQuantity

      val basket = emptyBasket
        .add(product, quantity)
        .remove(product, removedQuantity)
      assert(basket.items(product) == expectedQuantity, "Quantity of items removed from Basket does not match")

      val basket2 = basket
        .remove(product, removedQuantity)
      assert(basket2.items(product) == quantity - 2 * removedQuantity, "Quantity of items removed twice from Basket does not match")
    }
    it("should throw an IllegalArgumentException, when the item is not in the Basket") {
      val product = product1
      val quantity = 5

      val exception = intercept[IllegalArgumentException] {
        emptyBasket.remove(product, quantity)
      }
    }
    it("should throw an IllegalArgumentException, when the quantity of items in the Basket is less than the quantity to be removed") {
      val product = product1
      val quantity = 5
      val removedQuantity = 10
      val basket = emptyBasket.add(product, quantity)

      val exception = intercept[IllegalArgumentException] {
        basket.remove(product, removedQuantity)
      }
    }
  }

  describe("The total method") {
    it("should return the total value of the Basket") {
      val quantity1 = 1
      val quantity2 = 3
      val quantity3 = 5

      val basket =
        emptyBasket
        .add(product1, quantity1)
        .add(product2, quantity2)
        .add(product3, quantity3)
      val expectedSum = 1*1 + 3*10 + 5*100

      assert(basket.total == expectedSum, "Sum of Products added to empty Basket does not match with expected")
    }
  }
}
