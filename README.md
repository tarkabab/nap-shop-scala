# Net-A-Porter Shop Technical Exercise

You have been given a partially implemented e-Commerce application. We would like you to finish the implementation to provide some basic shopping features.

## Running the Application

The Shop has been implemented as a command-line application.
The main method is in `Application.scala`.
The application uses SBT and can be run with the command:

~~~ bash
sbt run
~~~

When running, the application will shows a list of options,
`Application.scala` has been implemented to read the input and
invoke the appropriate partially implemented method.

We would like you to finish the application be completing the following scenarios.

## Exercises

### Exercise 1 - Loading and listing Products

In this exercise you need to implement the `Catalogue.parse` method
to read CSV data in the format provided in `catalogue.csv`
(see `Catalogue.scala`).

The data is read from the file for you and passed to the method
as a `List` of lines, for example:

~~~ scala
Catalogue.parse(List(
  "#productId,#productName,#price",
  "1,Short Sleeve Jumper,£9.99",
  ...))
~~~

You'll need to add fields to the `Product` case class
to hold data from each of the three columns.

Complement your code by writing unit tests
using a testing framework of your choice.

### Exercise 2 - Basic basket functionality

In this exercise you build on what you've done in Exercise 1.
We would like you to implement some basic features of a shopping basket
(see `Basket.scala`):

* Implement the ability to add a product to the basket
* Implement the ability to remove a product from the basket
* Implement the ability to get the total value of the basket

You should also implement one method in `Catalogue`:

* Implement the `Catalogue.findProduct` method to locate a product by ID

Complement your code by writing unit tests
using a testing framework of your choice.

### Notes

The application has been created without any specific libraries.
If you wish, you have total freedom to use any Scala version or libraries
to help you complete the exercises. Simply update `build.sbt` as necessary.

A good implementation will be:

 - written using idiomatic Scala;
 - written in a type-safe and functional style;
 - easy for junior-to-intermediate developers to read and understand.
