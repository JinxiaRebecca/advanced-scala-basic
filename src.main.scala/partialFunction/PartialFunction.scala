package partialFunction

/**
 * partial function mainly for pattern matching, only has one parameter
 */
object PartialFunction extends App {

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 41
    case 3 => 999
  }

  println(aPartialFunction(2))

  // FP utilities
  val lifted = aPartialFunction.lift // Int => Option[Int]

  println(lifted(98))

  aPartialFunction.orElse[Int, Int] {
    case 45 => 67
  }

  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  val aMappedList = List(1, 2, 5).map {
    case 1 => 42
    case 2 => 77
    case 5 => 69
  }

  println(aMappedList)

  val chatBot: PartialFunction[String, String] = {
    case "hello" => "Hi my name is nut"
    case "goodbye" => "goodbye"
  }

  scala.io.Source.stdin.getLines().map(chatBot).foreach(println)

}
