object collectionOperation extends App {

  def arrayOperation(arr: Array[Int], op: Int => Int): Array[Int] = {
    for (ele <- arr) yield op(ele)
  }

}
