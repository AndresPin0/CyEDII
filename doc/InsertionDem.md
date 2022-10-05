# Insertion Demostration


```Scala

@tailrec
def insertTailRec(h: Int, t: List[Int], acc: List[Int]): List[Int] = t match {
    case Nil => acc.reverse ++ (h :: Nil)
    case ht :: tt => {
        if (h <= ht) acc.reverse ++ (h :: t)
        else insertTailRec(h, tt, ht :: acc)
    }
}

```

## Contexto:

```Scala
h // Es el objeto que estamos evaluando
t // El resto de la lista sin contar h
acc // Es el acumulado de los elementos menores a h hasta el momento
```
Teniendo una lista compuesta por números enteros, de la forma $L(h,t)$ tal que $h$ es un elemento que pertenece a $L$ y $t$ es una lista de la forma $L(h,t)$.


Dada la lista $S = (s_1, s_2, s_3, … , s_n)$ compuesta por números enteros, aplicamos la función $insertTailRec(s_1,s_2,s_3, … ,s_n) =  insertTailRec(h, insertTailRec(s_2,s_3, … ,s_n))$.

## Caso base:

Cuando $n=0$, es decir $|S|=0$:

* $insertTailRec({}) = 0$

## Paso inductivo:

* $insertTailRec(s_1,s_2,s_3,...,s_k) = insert(s_1,insertTailRec(s_2,s_3,...,s_k))$

Se espera que el algoritmo sirva para todo k+1:

* $insertTailRec(s_1,s_2,s_3,...,s_{k+1}) = insert(s_1,insertTailRec(s_2,s_3,...,s_{k+1}))$

* $insertTailRec(s_1,s_2,s_3,...,s_k,s_{k+1}) = insert(s_1,insertTailRec(s_2,s_3,...,s_k,s_{k+1}))$


Por la propiedad de un conjunto de elementos desordenados, podemos alterar el orden del conjunto:

* $insertTailRec(s_1,s_2,s_3,...,s_k,s_{k+1}) =insertTailRec(s_{k+1},s_1,s_2,s_3,...,s_k)$

Por paso inductivo, sabemos que $insertTailRec(L)$ tal que $|L| = k+1$ , es:

* $insertTailRec(s_{k+1},s_1,s_2,s_3,...,s_k) =insertTailRec(s_{k+1},insertTailRec(s_1,insertTailRec(s_2,s_3,...,s_k)))$

* $insertTailRec(s_1,s_2,s_3,...,s_k,s_{k+1}) =insert(s_{k+1},insertTailRec(s_1,s_2,s_3,...,s_k))$

* $insertTailRec(s_1,s_2,s_3,...,s_k,s_{k+1}) =insertTailRec(s_{k+1}::insertTailRec(s_1,s_2,s_3,...,s_k))$

* $insertTailRec(s_1,s_2,s_3,...,s_k,s_{k+1}) =insertTailRec(s_{k+1},s_1,s_2,s_3,...,s_k)$

Por lo tanto: 

* $insertTailRec(s_1,s_2,s_3,...,s_k,s_{k+1}) =insertTailRec(s_1,s_2,s_3,...,s_k,s_{k+1})$

