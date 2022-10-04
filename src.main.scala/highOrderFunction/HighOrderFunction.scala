package highOrderFunction

import scala.annotation.tailrec

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

  def func(i: Int): String => Char => Boolean = {
    def f1(s: String): Char => Boolean = {
      def f2(c: Char): Boolean = {
        if (i == 0 && s == "" && c == '0') false else true
      }

      f2
    }

    f1
  }

  def func1(i: Int): String => Char => Boolean = {
    s => c => if (i == 0 && s == "" && c == '0') false else true
  }

  //  柯里化
  def func2(i: Int)(s: String)(c: Char): Boolean = {
    if (i == 0 && s == "" && c == '0') false else true
  }

  def addCurry(a: Int)(b: Int): Int = a + b

  def recursion(a: Int): Int = {
    if (a == 0) return 1
    recursion(a - 1) * a
  }

  //尾递归
  def tailFact(n: Int): Int = {
    @tailrec
    def loop(n: Int, result: Int): Int = {
      if (n == 0) return result
      loop(n - 1, result * n)
    }
    loop(n, 1)
  }
}
