package lazyLoad

//
object Lazy extends App {

  def sum(a: Int, b: Int): Int = {
    println("3. sum 被调用")
    a + b
  }
  lazy val result: Int = sum(13, 34)
  println("1. 函数调用")
  println("2. result = " + result)
  println("4. result = " + result)

}
