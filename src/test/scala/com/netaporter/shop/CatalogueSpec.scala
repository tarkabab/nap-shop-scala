package com.netaporter.shop

import org.scalatest.FunSpec

class CatalogueSpec extends FunSpec {

  describe("The companion's load method") {
    it("should parse the file specified in its parameter") {
      Catalogue.load("/catalogue.csv")
    }
    it("should throw a NullPointerException, in case it cannot find the file specified") {
      val exception = intercept[NullPointerException] {
        Catalogue.load("this file does not exists")
      }
    }
  }

  describe("The companion's parse method") {
    it("should return a Catalogue from the passed stream of Strings") {
      val line = "1,Short Sleeve Jumper,£9.99"
      Catalogue.parse(Seq(line))
    }
    it("should ignore lines, which cannot be interpreted as a Product") {
      val line1 = "this line does not define a Product"
      val line2 = "#productId,#productName,#price"
      Catalogue.parse(Seq(line1, line2))
    }
  }

  describe("The findProduct method") {
    it("should return a Product wrapped in an Option when a Product with the given id exists") {
      val products = Array(
        "1,Short Sleeve Jumper,£9.99",
        "2,Shoulder Bag,£9.99",
        "3,Skinny Jeans,£45.00"
      ).toSeq
      val searchId = 1

      val catalogue = Catalogue.parse(products)
      val searchResult = catalogue.findProduct(searchId)
      assert(searchResult.isDefined)
      val product = searchResult.get
      assert(product.id == searchId)
    }
    it("should return a None when a Product with the given id does not exists") {
      val products = Array(
        "1,Short Sleeve Jumper,£9.99",
        "2,Shoulder Bag,£9.99",
        "3,Skinny Jeans,£45.00"
      ).toSeq
      val searchId = 4

      val catalogue = Catalogue.parse(products)
      val searchResult = catalogue.findProduct(searchId)
      assert(searchResult.isEmpty)
    }
  }

}
