import org.scalatest.funsuite.AnyFunSuite

/** scala test class for testing the sorting algorithms implemented*/

class AlgorithmTest extends AnyFunSuite {
  test("Insertion Sort") {
    val A = List(5, 2, 44, 3, 2, 77, 6, 5, 98, 11, 10, 1)
    val B = List(1, 2, 2, 3, 5, 5, 6, 10, 11, 44, 77, 98)
    assert(Main.insertionSort(A) == B)
  }

  test("Insertion Sort Tail Rec") {
    val A = List(29, 10, 14, 37, 14)
    val B = List(10, 14, 14, 29, 37)
    assert(Main.insertionSortTailRec(A) == B)
  }

  test("Shell Sort") {
    val A = List(12, 34, 54, 2, 3)
    val B = List(2, 3, 12, 34, 54)
    assert(Main.shellSortTrigger(A) == B)
  }

  test("Quick Sort") {
    val A = List(2, 3, 8, 7, 1, 2, 2, 2, 7, 3, 9, 8, 2, 1, 4)
    val B = List(1, 1, 2, 2, 2, 2, 2, 3, 3, 4, 7, 7, 8, 8, 9)
    assert(Main.quickSort(A) == B)
  }

  test("Bucket Sort") {
    val A = List(11, 9, 21, 8, 17, 19, 13, 1, 24, 12)
    val B = List(1, 8, 9, 11, 12, 13, 17, 19, 21, 24)
    assert(Main.bucketSort(A) == B)
  }
}