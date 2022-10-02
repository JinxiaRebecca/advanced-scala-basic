package partialFunction


trait MySet[A] extends (A => Boolean) {

  def contains(ele: A): Boolean

  def +(ele: A): MySet[A]

  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def foreach(f: A => Unit): Unit

  def apply(ele: A): Boolean = contains(ele)

  def filter(f: A => Boolean): MySet[A]


  def -(ele: A): MySet[A]

  def --(anotherSet: MySet[A]): MySet[A]

  def &(anotherSet: MySet[A]): MySet[A]


}

class EmptySet[A] extends MySet[A] {
  override def contains(ele: A): Boolean = false

  override def +(ele: A): MySet[A] = new NonEmptySet[A](ele, this)

  override def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  override def foreach(f: A => Unit): Unit = ()

  //remove an element
  override def -(ele: A): MySet[A] = this

  //difference with another set
  override def --(anotherSet: MySet[A]): MySet[A] = this

  //intersection with another set
  override def &(anotherSet: MySet[A]): MySet[A] = this

  override def filter(f: A => Boolean): MySet[A] = this

}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  override def contains(ele: A): Boolean = ele == head || tail.contains(ele)

  override def +(ele: A): MySet[A] =
    if (this contains ele) this
    else new NonEmptySet[A](ele, this)

  override def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head

  override def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  override def foreach(f: A => Unit): Unit = {
    f(head)

  }

  override def filter(f: A => Boolean): MySet[A] = {
    val filteredTail = tail filter f
    if (f(head)) filteredTail + head
    else filteredTail
  }

  override def -(ele: A): MySet[A] =
    if (head == ele) tail
    else tail - ele + head

  override def --(anotherSet: MySet[A]): MySet[A] = ???

  override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)



}

object MySet {
  def apply[A](values: A*): MySet[A] = {
    def buildSet(valSeq: Seq[A], acc: MySet[A]): MySet[A] =
      if (valSeq.isEmpty) acc
      else buildSet(valSeq.tail, acc + valSeq.head)

    buildSet(values.toSeq, new EmptySet[A])
  }
}
