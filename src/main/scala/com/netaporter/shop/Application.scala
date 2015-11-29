package com.netaporter.shop

/**
 * Entry to the shop application.
 */
object Application {
  def main(args: Array[String]): Unit = {
    val catalogue = Catalogue.load("catalogue.csv")
    var basket    = Basket(Map.empty)

    println("************************************")
    println("* Welcome to the Net-A-Porter Shop *")
    println("************************************")
    println("Enter \"quit\" to Quit")
    println("Enter \"add <ProductId>\" to add to basket")
    println("Enter \"remove <ProductId>\" to remove from basket")
    println("Enter \"list\" to show a list of products in the catalogue")
    println("Enter \"basket\" to show a list of products in the basket")
    println("Enter \"total\" to show the total value of the basket")

    var finished  = false

    while(!finished) {
      readLine.split("[ \t]+").toList match {
        case "add" :: IntParam(productId) :: IntParam(qty) :: _ =>
          withProduct(catalogue, productId) { product =>
            basket = basket.add(product, qty)
          }

        case "add" :: IntParam(productId) :: _ =>
          withProduct(catalogue, productId) { product =>
            basket = basket.add(product, 1)
          }

        case "remove" :: IntParam(productId) :: IntParam(qty) :: _ =>
          withProduct(catalogue, productId) { product =>
            basket = basket.remove(product, qty)
          }

        case "remove" :: IntParam(productId) :: _ =>
          withProduct(catalogue, productId) { product =>
            basket = basket.remove(product, 1)
          }

        case "list" :: _ =>
          catalogue.products.foreach(println)

        case "basket" :: _ =>
          basket.items.foreach {
            case (product, qty) =>
              println(qty + "x " + product)
          }

        case "total" :: _ =>
          println("Total: £" + basket.total)

        case "quit" :: _ =>
          finished = true

        case _ =>
          println("I don't understand!")
      }

      println()
    }
  }

  private object IntParam {
    import scala.util.Try

    def unapply(str: String): Option[Int] =
      Try(str.toInt).toOption
  }

  private def withProduct(catalogue: Catalogue, productId: Int)(func: Product => Unit): Unit = {
    catalogue.findProduct(productId) match {
      case Some(product) =>
        func(product)
        println("OK!")

      case None =>
        println("Product " + productId + " not found.")
    }
  }
}
