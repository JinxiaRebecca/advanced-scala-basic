package partialFunction

/**
 * curried function
 * method != function
 */
object CurriedFunction extends App {

  //curried function
  val supperAdder: Int => Int => Int =
    x => y => x + y

  //method
  def curriedMethod(x: Int)(y: Int): Int
  = x + y

  // lift: method => function
  val add4: Int => Int = curriedMethod(4)
  val add5 = curriedMethod(5) _

  val simpleAddFunction = (x: Int, y: Int) => x + y

  def simpleAddMethod(x: Int, y: Int) = x + y

  def curriedAddMethod(x: Int)(y: Int) = x + y

  val add6 = (x: Int) => simpleAddFunction(6, x)
  val add6_1 = simpleAddFunction.curried(6)
  val add6_2 = simpleAddFunction(6, _: Int)

  val add6_3 = simpleAddMethod(6, _: Int) // alternative syntax for turning methods to function value

  val add6_4 = curriedAddMethod(6) _ //PFA
  val add6_5 = curriedAddMethod(6)(_)

  //powerful use of underscores
  def accumulator(a: String, b: String, c: String) = a + b + c

  val insertName = accumulator("Hello, this is ", _: String, ", how about you?")
  println(insertName("Rebecca"))

  //1.  process a list of numbers and return different formats
  def curriedFormatter(s: String)(number: Double) = s.format(number)

  val numbers = List(Math.PI, Math.E)

  val simpleFormatter = curriedFormatter("%4.2f") _ // lift
  val seriousFormatter = curriedFormatter("%8.6f") _
  val preciseFormatter = curriedFormatter("%14.12f") _

  println(numbers.map(curriedFormatter("%4.2f"))) // compiler does eta-expression

  //2.diff between functions and method


}
