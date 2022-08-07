package highOrderFunction

object HighOrderFunction extends App {

  def f(num: Int): Int = {
    num
  }

  //1.函数作为值 进行传递
  val f1: Int => Int = f
  val f2 = f _

  //2.函数作为参数 进行传递
  def dualEval(op: (Int, Int) => Int, a: Int, b: Int): Int = {
    op(a, b)
  }

  def add(a: Int, b: Int): Int = a + b

  dualEval(add, 1, 2)
  dualEval(_ - _, 3, 2)

  // 3.函数作为函数返回值
  def f5(): Int => Unit = {
    def f6(a: Int): Unit = {
      println("f6 调用" + a)
    }

    f6
  }

  println(f5()) //函数
  println(f5()(25)) // 结果

  def f7() = {
    def f8(a: Int): Unit = {
      println("f6 调用" + a)
    }

    f8 _
  }

}
