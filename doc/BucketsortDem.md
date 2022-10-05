# Bucketsort Demostration


```Scala
@tailrec
def createOrderedList(buckets: List[List[Int]], acc: List[Int]): List[Int] = {
  buckets match {
    case Nil => acc
    case h :: t => createOrderedList(t, acc ++ h)
  }
}
```
## Contexto:

```Scala
buckets // Es una lista de listas ordenadas.
acc // Es el acumulado de los elementos hasta el momento. (inicia vacia)
```
Teniendo una lista compuesta por números enteros, de la forma $L(h,t)$ tal que $h$ es un elemento que pertenece a $L$ y $t$ es una lista de la forma $L(h,t)$.

Dada la lista $L = (l_1, l_2, l_3, … , l_m)$ compuesta por una lista ordenada de enteros tal que $S = (s_1, s_2, s_3, … , s_n)$, aplicamos la función $createOrderedList(l_1, l_2, l_3, … , l_m)$.

## Caso base:

Cuando $m=0$, es decir $|L|=0$:

* $createOrderedList({}) = 0$

## Paso inductivo:

* $createOrderedList(l_1, l_2, l_3, … , l_m) = createOrderedList(l_1) :: createOrderedList(l_2, l_3, … , l_m)$

* $createOrderedList(l_1, l_2, l_3, … , l_k) = createOrderedList(l_1) :: createOrderedList(l_2, l_3, … , l_k)$

Se espera que el algoritmo sirva para todo k+1:

* $createOrderedList(l_1, l_2, l_3, … , l_k, l_{k+1}) = createOrderedList(l_1,l_2, l_3, … , l_k, l_{k+1})$

* $createOrderedList(l_1, l_2, l_3, … , l_k, l_{k+1}) = createOrderedList(l_1,l_2, l_3, … , l_k):: createOrderedList(l_{k+1}))$

$createOrderedList(l_{k+1})=l_{k+1}::Nil$

* $createOrderedList(l_1, l_2, l_3, … , l_k, l_{k+1}) = createOrderedList((l_1, l_2, l_3, … , l_k)::l_{k+1})$

Por lo tanto:

* $createOrderedList(l_1, l_2, l_3, … , l_k, l_{k+1}) = createOrderedList(l_1, l_2, l_3, … , l_k,l_{k+1})$




