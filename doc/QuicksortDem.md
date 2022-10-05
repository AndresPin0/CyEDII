# Quicksort Demostration



```Scala

def quickSort(A: List[Int]): List[Int] = A match {
    case Nil => Nil
    case h :: t => val (left, right) = t.partition(_ < h)
      quickSort(left) ++ (h :: quickSort(right))
}

```


## Contexto:

```Scala
A // Es el conjunto o lista de enteros 
```


Teniendo una lista compuesta por números enteros, de la forma $L(h,t)$ tal que $h$ es un elemento que pertenece a $L$ y $t$ es una lista de la forma $L(h,t)$.


Dada la lista $S = (s_1, s_2, s_3, … , s_n)$ compuesta por números enteros, aplicamos la función $quickSort(s_1,s_2,s_3, … ,s_n) =  quickSort(h, quickSort(s_2,s_3, … ,s_n))$ siendo $s_1=h$.

## Caso base:

Cuando $n=0$, es decir $|S|=0$:

* $quickSort(Nil) = Nil$

## Paso inductivo:


$quickSort(S)=\left\lbrace\begin{array}{c} leftSide(S) => Lista~de~enteros~menores~que~s_i\\ rightSide(S)=> Lista~de~enteros~mayores~que~s_i \end{array}\right.$

Ya que al usar $val (left, right) = t.partition($ _ $ < h)$ se generan $left$ una lista de valores menores a $h$ y $right$ una lista de los valores mayores a $h$.

---

Siendo entonces:

* $left=leftSide()$

Por lo que $S_{left}=(x \in S~ |~ x< s_i)$ 

* $right=rightSide()$

Por lo que $S_{right}=(x \in S~| ~ x> s_i)$  

---


* $quickSort(s_1, s_2, s_3, … , s_n)=quickSort(quickSort(left)::s_1::quickSort(right))$

Sabemos que en una lista desordenada no importa el orden de los elementos por la propiedad de una lista desordenada:

* $quickSort(s_1, s_2, s_3, … , s_k)=quickSort(quickSort(left)::s_k::quickSort(right))$

Por lo que para $k+1$:

* $quickSort(s_1, s_2, s_3, … , s_k,s_{k+1})=quickSort(quickSort(left)::s_{k+1}::quickSort(right))$

* $quickSort(s_1, s_2, s_3, … , s_k,s_{k+1})=quickSort(quickSort(left)::quickSort(s_{k+1}::right))$

* $quickSort(s_1, s_2, s_3, … , s_k,s_{k+1})=quickSort(left::s_{k+1}::right)$

* $quickSort(s_1, s_2, s_3, … , s_k,s_{k+1})=quickSort(s_1, s_2, s_3, … ,s_{k+1}, s_k)$