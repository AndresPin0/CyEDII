## **Insertion sort**

---

```scala

  def insertionSortTailRec(A: List[Int]): List[Int] = {

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

```

Para el insertion sort se debe tener en cuenta la estructura de la funcion interna, es decir el insert.

En el peor de los casos no hay ningún elemento mayor a $h$, lo que indica que se tuvo que recorrer todos los elementos de este, por lo que la complejidad es:

$$T_{insert}(n) = n$$

Por lo que la complejidad de $insert$ es $O(n)$.

Por otro lado, el algoritmo $insertionSort$ se ejecuta para cada $h$ perteneciente a la lista, y dentro de este se ejecuta $insert$ esta cantidad de de veces, por lo que la complejidad queda expresada:

$$T_{insertionSort} = nT_{insert}(n)$$

$$T_{insertionSort} = n^2$$

Por lo que la complejidad de $insertionSort$ es $O(n^2)$.

## **Shell sort**

---

```scala

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

```

Para el $shellSort$ primero se debe definir la complejidad de los métodos internos:

Para $extractByGap$ se recorre la lista, la lista obteniendo los valores que cumplen con la condición de que su posición sea múltiplo del gap, sin embargo, eso implica que se tenga que recorrer toda la lista, por lo tanto:

$$T_{extractByGap}(n) = n$$

Se sabe que la complejidad temporal del $insertionSort$ es $O(n^2)$, por lo que:

$$T_{insertionSort}(n) = n^2$$

Para el $insertShellSort$ se recorre toda la lista, reemplazando los elementos que fueron ordenados previamente, lo que indica que si complejidad es:

$$T_{insertShellSort}(n) = n$$

Por ultimo, para calcular cuantas veces se ejecuta el $shellSort$ se debe tener en cuenta que se ejecuta hasta que el término $\frac{1}{2^k}$ (siendo $k$ la iteración) sea 0. Se conoce que esto se consigue con $\log(n)$ ejecuciones. Por lo que la complejidad queda de la siguiente manera:

$$T_{shellSort}(n) = (T_{extractByGap}(n) + T_{insertionSort}(n) + T_{insertShellSort}(n))\log(n)$$

$$T_{shellSort}(n) = (n + n^2 + n)\log(n)$$

$$T_{shellSort}(n) = (n^2 + 2n)\log(n)$$

$$T_{shellSort}(n) = n^2\log(n) + 2n\log(n)$$

Por lo tanto la complejidad del $shellSort$ es $O(n^2\log(n))$

## **Quick sort**

---

```scala

def quickSort(A: List[Int]): List[Int] = A match {
  case Nil => Nil
  case h :: t => val (left, right) = t.partition(_ < h)
    quickSort(left) ++ (h :: quickSort(right))
}

```

Este llamado recursivo, se ve a través de un arbol binario, el cual se va dividiendo hasta llegar al caso base, de la siguiente manera:

![Quicksort](https://programmerclick.com/images/795/b3300de4fbef0f13bd6fba1ee6d6877b.JPEG)

En este caso, como se aprecia en la imagen, aparece un $m(n)$ que representa el coste de nuestro algoritmo, por lo que se recorre toda la lista para hacer la partición, se afirma que $m(n) = n$. Por otro lado, se ve en cada nivel se tiene $n$, y la complejidad para llegar al último nivel es $\log_2(n)$ (porque se hacen $2$ particiones en cada llamado recursivo), lo que quiere decir que la complejidad queda expresada:

$$T_{quickSort} = n\log_2(n)$$

La razón por el cual es $\log_2(n)$ la complejidad necesaria para que se llegue al caso base eso porque el caso base se logra cuando:

$$2^k = n$$

Que si se resuelve se obtiene:

$$k = \log_2(n)$$

Por lo que la complejidad de $quickSort$ es $O(n\log(n))$

## **Bucket sort**

---

```scala

  def bucketSort(A: List[Int]): List[Int] = {

    @tailrec
    def generateBuckets(bucketsNumber: Int, buckets: List[List[Int]]): List[List[Int]] = {
      if (bucketsNumber == 0) List()::buckets
      else generateBuckets(bucketsNumber - 1, List()::buckets)
    }

    def sortBuckets(buckets: List[List[Int]]): List[List[Int]] = {
      buckets match {
        case Nil => List()
        case h :: t => insertionSort(h) :: t
      }
    }

    @tailrec
    def createOrderedList(buckets: List[List[Int]], acc: List[Int]): List[Int] = {
      buckets match {
        case Nil => acc
        case h :: t => createOrderedList(t, acc ++ h)
      }
    }

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
        val bucketsNumber = Math.sqrt(A.size).toInt

        val r = ((A.max - A.min) / bucketsNumber).ceil.toInt

        val buckets = generateBuckets(bucketsNumber, List())
        val bucketsWithElements = insertInBuckets(A, buckets, r)
        val orderedBuckets = sortBuckets(bucketsWithElements)

        createOrderedList(orderedBuckets, List())
      }
    }
  }

```

En primer lugar se debe tener en cuenta la complejidad para:

- Crear los buckets

- Insertar los elementos en su respectivo bucket

- Ordenar los buckets

- Unir los buckets ordenados

Entendiendo que la complejidad total del algoritmo es la suma de todas estas.

Se define $n$ como el numero de elementos y $m$ como el número de buckets.

### **Crear los buckets**

Para crear los buckets, tenemos un número de buckets $m$ previamente definido, y precisamente la complejidad es:

$$T_{generateBuckets}(n) = m$$

Esto porque se hacen $m$ iteraciones, una cada vez que se crea un bucket.

### **Insertar los elementos en su respectivo bucket**

En este caso se recorre cada uno de los elementos del arreglo, por lo que se puede inferir un $n$ como uno de los términos de la ecuación de la complejidad, pero para cada elemento se debe recorrer todos los buckets hasta encontrar el indicado según la posición calculada, es decir, que la complejidad para insertar un solo elemento en su bucket es:

$$T_{insert}(n) = m$$

Pero, como se mecionó anteriormente, esto se debe realizar para los $n$ elementos de la lista, por lo que la complejidad queda:

$$T_{insertInBuckets}(n) = nT_{insert}(n)$$

$$T_{insertInBuckets}(n) = mn$$

### **Ordenar todos los buckets**

Se realiza $insertionSort$ para ordenar cada bucket, y se conoce que la complejidad del algoritmo es $O(n^2)$, además, se sabe que hay $m$ buckets, por lo que la complejidad es:

$$T_{sortBuckets} = mn^2$$

**Nota**: Que se multiplique por $n^2$ no quiere decir que sea mucho más eficiente realizar $insertionSort$ directamente en la lista sin dividirla en buckets. Esto porque los $n$ que se encuentran al realizar este ordenamiento sobre los buckets son considerablemente menores que el $n$ de la lista original, al ser cada bucket una partición de esta.

### **Unir los buckets ordenados**

En esta fase final del ordenamiento, lo único que se hace es recorrer cada bucket y concatenar los elementos de estos, lo que dará lugar a la lista ordenada, por lo que la complejidad es:

$$T_{createOrderedList}(n) = m$$

### **Complejidad final del algoritmo**

La complejidad del algoritmo es la suma de los calculados anteriormente, por lo que se obtiene:

$$T_{bucketSort}(n) = T_{generateBuckets}(n) + T_{insertInBuckets}(n) + T_{sortBuckets}(n) + T_{createOrderedList}(n)$$

$$T_{bucketSort}(n) = m + mn + mn^2 + m$$

$$T_{bucketSort}(n) = mn^2 + mn + 2m$$

Por lo que la complejidad es $O(mn^2)$, lo que indica que la complejidad del algoritmo se concentra mayormente en el proceso de ordenar todos los buckets una vez los elementos están almacenados en estos.