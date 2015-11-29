package com.netaporter.shop

import org.scalatest.FunSpec

class CatalogueSpec extends FunSpec {

  val productString1 = "1,Short Sleeve Jumper,£9.99"
  val productString2 = "2,Shoulder Bag,£9.99"
  val productString3 = "3,Skinny Jeans,£45.00"
  val productStringSequence = Seq(productString1, productString2, productString3)

  describe("The companion's load method") {
    it("should parse the file specified in its parameter") {
      Catalogue.load("/catalogue.csv")
    }
    it("should throw NullPointerException, in case it cannot find the file specified") {
      val exception = intercept[NullPointerException] {
        Catalogue.load("this file does not exists")
      }
    }
  }

  describe("The companion's parse method") {
    it("should return Catalogue from the passed stream of Strings") {
      Catalogue.parse(productStringSequence)
    }
  }

  describe("The companion's parseProduct method") {
    it("should return Product wrapped in an Option from a string") {
      val parsed = Catalogue.parseProduct(productString1)
      assert(parsed.isDefined)
    }
    it("should return None when the string cannot be interpreted as a Product") {
      val line1 = "this line does not define a Product"
      val line2 = "#productId,#productName,#price"
      val parsed1 = Catalogue.parseProduct(line1)
      assert(parsed1.isEmpty)
      val parsed2 = Catalogue.parseProduct(line2)
      assert(parsed2.isEmpty)
    }
  }

  describe("The findProduct method") {
    it("should return Product wrapped in an Option when a Product with the given id exists") {
      val searchId = 1

      val catalogue = Catalogue.parse(productStringSequence)
      val searchResult = catalogue.findProduct(searchId)
      assert(searchResult.isDefined)
      val product = searchResult.get
      assert(product.id == searchId)
    }
    it("should return None when a Product with the given id does not exists") {
      val searchId = 4

      val catalogue = Catalogue.parse(productStringSequence)
      val searchResult = catalogue.findProduct(searchId)
      assert(searchResult.isEmpty)
    }
  }

}
