package patternMatching

/**
 * pattern matching often used in below case:
 * constants
 * wildcards
 * case classes
 * tuple
 * some special magic like above
 */
object PatternMatching extends App {

  class Person(val name: String, val age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] = Some(person.name, person.age)

    def unapply(age: Int): Option[String] = Some(if (age < 21) "minor" else "major")
  }

  val bob = new Person("Bob", 21)
  val greeting = bob match {
    case Person(name, age) => s"Hi, my name is$name, I'm $age yo."
  }

  println(greeting)

  val legalStatus = bob.age match {
    case Person(status) => s"My legal status is $status"
  }
  println(legalStatus)


  object even {
    def unapply(num: Int): Boolean = num % 2 == 0

  }

  object singleDigit {
    def unapply(number: Int): Boolean = {
      number <= 10 && number >= -10
    }

  }

  val num = 2
  val matchProperty = num match {
    case even() => s"it's a even"
    case singleDigit() => s"it's single digit"
    case _ => "nothing"
  }

  println(matchProperty)

  //customer return types for unapply

  abstract class Wrapper[T] {
    def isEmpty: Boolean
    def get: T
  }

  object PersonWrapper {
    def unapply(person: Person): Wrapper[String] = new Wrapper[String] {
      def isEmpty = false
      def get: String = person.name
    }
  }

  val name = bob match {
    case PersonWrapper(name) => s"My name is $name"
    case _ => "nothing"
  }

  println(name)

}
