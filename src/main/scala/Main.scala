import scala.annotation.tailrec

/**
 * This is the main class for the sorting algorithms
 *
 * @author [Santiago Barraza]
 * @author [Andrés Pino]
 * @author [Sebastián López]
 */

object Main extends App {
  /**
   * List of sorting algorithms implemented
   *
   * 1. Insertion Sort
   * 2. Insertion Sort Tail Rec
   * 3. Shell Sort
   * 4. Quick Sort
   * 5. Bucket Sort
   */
  val A = List(5, 2, 44, 3, 2, 77, 6, 5, 98, 11,10,1)

  /**
   * Insertion Sort Algorithm Implementation
   * @param A List of Integers to be sorted
   * @param makeSort Function to be used for sorting
   * @return Sorted List of Integers
   */
    def sort(A: List[Int], makeSort: List[Int] => List[Int]): List[Int] = {
    makeSort(A)
  }

  /**
   * Insertion Sort Algorithm Implementation
   * @param A List of Integers to be sorted
   * @return Sorted List of Integers
   */
  def insertionSort(A: List[Int]): List[Int] = {

    /**
     * Insertion Sort Algorithm Implementation
     * @param h Head of the List to be sorted
     * @param t Tail of the List to be sorted
     * @return Sorted List of Integers
     */
    def insert(h: Int, t: List[Int]): List[Int] = t match {
      case Nil => h :: Nil
      case ht :: tt => {
        if (h <= ht) h :: t
        else ht :: insert(h, tt)
      }
    }

    A match {
      case Nil => Nil
      case h :: t => insert(h,insertionSort(t))
    }
  }

  /**
   * Insertion Sort Tail Rec Algorithm Implementation
   * @param A List of Integers to be sorted
   * @return Sorted List of Integers
   *         Note: This is a tail recursive implementation of Insertion Sort
   *         which is more efficient than the normal implementation
   *         of Insertion Sort. This is achieved by using the @tailrec annotation
   *         which will throw an error if the function is not tail recursive
   *         and hence will not be optimized by the compiler
   *         This is a good way to ensure that the function is tail recursive
   *         and hence will be optimized by the compiler
   */
  def insertionSortTailRec(A: List[Int]): List[Int] = {

    /**
     * Insertion Sort Tail Rec Algorithm Implementation
     * @param h Head of the List to be sorted
     * @param t Tail of the List to be sorted
     * @param acc Accumulator for the sorted list
     * @return Sorted List of Integers
     *
     */
    @tailrec
    def insertTailRec(h: Int, t: List[Int], acc: List[Int]): List[Int] = t match {
      case Nil => acc.reverse ++ (h :: Nil)
      case ht :: tt => {
        if (h <= ht) acc.reverse ++ (h :: t)
        else insertTailRec(h, tt, ht :: acc)
      }
    }

    A match {
      case Nil => Nil
      case h :: t => insertTailRec(h, insertionSortTailRec(t), List())
    }
  }

  /**
   * Shell Sort Algorithm Implementation
   * @param A List of Integers to be sorted
   * @return Sorted List of Integers
   */
  def shellSortTrigger(A: List[Int]): List[Int] = {
    if(A.size%2==0) shellSort(0::A,1).tail
    else shellSort(A,1)
  }

  /**
   * Shell Sort Algorithm Implementation
   * @param A List of Integers to be sorted
   * @param iter Iteration of the algorithm
   * @return Sorted List of Integers
   */
  @tailrec
  def shellSort(A: List[Int], iter: Int): List[Int] = {
    @tailrec
    def extractByGap(A: List[Int], gap: Int, acc: List[Int], iter: Int): List[Int] = {
      A match {
        case Nil => acc.reverse
        case h :: t => {
          if (iter % gap == 0) extractByGap(t, gap, h :: acc, iter + 1)
          else extractByGap(t, gap, acc, iter + 1)
        }
      }
    }

    /**
     * Insertion Sort implementation to Shell Sort Algorithm
     * @param A List of Integers to be sorted
     * @param elementsGot Number of elements got from the list
     * @param acc Accumulator for the sorted list
     * @param gap Gap to be used for sorting
     * @param iter Iteration of the algorithm
     * @return Sorted List of Integers
     */
    @tailrec
    def insertShellSort(A: List[Int], elementsGot: List[Int], acc: List[Int], gap: Int, iter: Int): List[Int] = A match {
      case Nil => acc.reverse
      case h :: t => {
        elementsGot match {
          case Nil => Nil
          case eh :: et => {
            if (iter % gap == 0) insertShellSort(t, et, eh :: acc, gap, iter + 1)
            else insertShellSort(t, elementsGot, h :: acc, gap, iter + 1)
          }
        }
      }
    }

    val gap = (math.floor((A.size / (math.pow(2, iter))))).toInt
    A match {
      case Nil => Nil
      case h :: t => {
        if (gap == 0) A
        else {
          val elementsGot = insertionSort(extractByGap(A, gap, List(), 0))
          val semiOrderedList = insertShellSort(A, elementsGot, List(), gap, 0)
          shellSort(semiOrderedList, iter + 1)
        }
      }
    }
  }

  /**
   * Quick Sort Algorithm Implementation (Partition)
   * @param A List of Integers to be sorted (List to be sorted)
   * @return Sorted List of Integers (Sorted List)
   */
  def quickSort(A: List[Int]): List[Int] = A match {
    case Nil => Nil
    case h :: t => val (left, right) = t.partition(_ < h)
      quickSort(left) ++ (h :: quickSort(right))
  }

  /**
   * Bucket Sort Algorithm Implementation
   * @param A List of Integers to be sorted (List to be sorted)
   * @return Sorted List of Integers (Sorted List)
   */
  def bucketSort(A: List[Int]): List[Int] = {
    /**
     * Bucket Sort Algorithm Implementation
     * @param bucketsNumber Number of buckets to be used for sorting (Number of buckets)
     * @param buckets List of buckets to be used for sorting (List of buckets)
     * @return Sorted List of Integers (Sorted List)
     */
    @tailrec
    def generateBuckets(bucketsNumber: Int, buckets: List[List[Int]]): List[List[Int]] = {
      if (bucketsNumber == 0) List()::buckets
      else generateBuckets(bucketsNumber - 1, List()::buckets)
    }

    /**
     * Bucket Sort Algorithm Implementation
     * @param buckets List of buckets to be used for sorting (List of buckets)
     * @return Sorted List of Integers (Sorted List)
     */
    @tailrec
    def sortBuckets(buckets: List[List[Int]], acc: List[List[Int]]): List[List[Int]] = {
      buckets match {
        case Nil => acc.reverse
        case h :: t =>{
          sortBuckets(t, insertionSort(h)::acc)
        }
      }
    }

    /**
     * Bucket Sort Algorithm Implementation
     * @param buckets List of buckets to be used for sorting (List of buckets)
     * @param acc Accumulator for the sorted list
     * @return Sorted List of Integers (Sorted List) Note: This function is used to flatten the list of buckets
     *         into a single list, Example:  List(List(1,2,3),List(4,5,6)) => List(1,2,3,4,5,6)
     */

    @tailrec
    def createOrderedList(buckets: List[List[Int]], acc: List[Int]): List[Int] = {
      buckets match {
        case Nil => acc
        case h :: t => createOrderedList(t, acc ++ h)
      }
    }

    /**
     * Bucket Sort Algorithm Implementation (Insertion Sort for buckets)
     *
     * @param A List of Integers to be sorted (List to be sorted)
     * @param buckets List of buckets to be used for sorting (List of buckets)
     * @param r Range of the list to be sorted (Range of the list)
     * @return Sorted List of Integers (Sorted List) Example: A = List(1,2,3,4,5,6,7,8,9,10)
     *         buckets = List(List(),List(),List(),List(),List(),List(),List(),List(),List(),List())
     *
     *
     */
    @tailrec
    def insertInBuckets(A: List[Int], buckets: List[List[Int]], r: Int): List[List[Int]] = {
      A match {
        case Nil => buckets
        case h :: t => {
          val position = (h / r).floor.toInt
          insertInBuckets(t, insert(buckets, h, position, List(), 0), r)
        }
      }
    }

    /**
     * Bucket Sort Algorithm Implementation (Insertion Sort for buckets)
     * @param buckets List of buckets to be used for sorting (List of buckets)
     * @param element Element to be inserted in the bucket (Element to be inserted)
     * @param position Position of the bucket to be used for sorting (Position of the bucket)
     * @param acc Accumulator for the sorted list
     * @param count Counter for the position of the bucket
     * @return Sorted List of Integers (Sorted List)*
     *         Example: insert(List(List(1,2,3),List(4,5,6)),7,1,List(),0)
     *         Result: List(List(1,2,3),List(4,5,6,7))
     */
    @tailrec
    def insert(buckets: List[List[Int]],element: Int, position: Int, acc: List[List[Int]], count: Int): List[List[Int]] = {
      buckets match {
        case Nil => acc.reverse
        case h::t => {
          if(count == position) insert(t, element, position, (h++List(element))::acc, count + 1)
          else insert(t, element, position, h::acc, count + 1)
        }
      }
    }

    A match {
      case Nil => Nil
      case h::t => {
        if(A.max==A.min) A
        else {
          val bucketsNumber = Math.sqrt(A.size).toInt
          val r = ((A.max - A.min) / bucketsNumber).ceil.toInt
          val buckets = generateBuckets(bucketsNumber, List())
          val bucketsWithElements = insertInBuckets(A, buckets, r)
          val orderedBuckets = sortBuckets(bucketsWithElements, List())
          createOrderedList(orderedBuckets, List())
        }
      }
    }
  }
  println(sort(A, insertionSort))
  println(sort(A, insertionSortTailRec))
  println(sort(A, shellSortTrigger))
  println(sort(A, quickSort))
  println(sort(A,bucketSort))

}
