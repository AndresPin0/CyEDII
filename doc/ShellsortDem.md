# Shellsort Demostration


```Scala
def extractByGap(A: List[Int], gap: Int, acc: List[Int], iter: Int): List[Int] = {
  A match {
    case Nil => acc.reverse
    case h :: t => {
      if (iter % gap == 0) extractByGap(t, gap, h :: acc, iter + 1)
      else extractByGap(t, gap, acc, iter + 1)
    }
  }
}
```
## Contexto:

```Scala
h // Es el objeto que estamos evaluando.
t // El resto de la lista sin contar h.
acc // Es el acumulado de los elementos hasta el momento.
gap // Es una constante que define el espacio entre cada numero para agregarlo al acumulado.
iter // Es el contador de la funcion.
```
Teniendo una lista compuesta por números enteros, de la forma $L(h,t)$ tal que $h$ es un elemento que pertenece a $L$ y $t$ es una lista de la forma $L(h,t)$.

Durante la ejecucion de esta funcion auxiliar, $gap$ es una constante.


Dada la lista $S = (s_1, s_2, s_3, … , s_n)$ compuesta por números enteros, aplicamos la función $extractByGap((s_1,s_2,s_3, … ,s_n)) =extractByGap(s_1:: extractByGap((s_2,s_3, … ,s_n)))$.

## Caso base:

Cuando $n=0$, es decir $|S|=0$:

* $extractByGap({}) = 0$

## Paso inductivo:

* $extractByGap(s_1,s_2,s_3,...,s_k) = extractByGap(s_1 :: extractByGap(s_2,s_3,...,s_k))$

Para todo $k+1$:

* $extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) = extractByGap(s_1 :: extractByGap(s_2,s_3,...,s_k,s_{k+1}))$


Por la propiedad de un conjunto de elementos desordenados, podemos alterar el orden del conjunto:

* $extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) =extractByGap(s_{k+1},s_1,s_2,s_3,...,s_k)$

Por paso inductivo, sabemos que $extractByGap(L)$ tal que $|L| = k+1$ , es:

* $extractByGap(s_{k+1},s_1,s_2,s_3,...,s_k) =extractByGap(s_{k+1} :: insertTailRec(s_1 :: extractByGap(s_2,s_3,...,s_k)))$

* $extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) = extractByGap(s_{k+1}::extractByGap(s_1,s_2,s_3,...,s_k))$

* $extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) =extractByGap(s_{k+1},s_1,s_2,s_3,...,s_k)$

Por lo tanto: 

* $extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) =extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1})$

$extractByGap()=\left\lbrace\begin{array}{c} extractByGap(t, gap, h :: acc, iter + 1) \\ extractByGap(t, gap, acc, iter + 1) \end{array}\right.$

Como $gap$ es constante durante toda la ejecucion se puede retirar:

$extractByGap()=\left\lbrace\begin{array}{c} extractByGap(t, h :: acc, iter + 1) \\ extractByGap(t, acc, iter + 1) \end{array}\right.$

Y $iter$ representa un contador por lo que tampoco es necesaria para la demostracion:

$extractByGap()=\left\lbrace\begin{array}{c} extractByGap(t, h :: acc) \\ extractByGap(t, acc) \end{array}\right.$

$extractByGap()=\left\lbrace\begin{array}{c} Si~(iter~MOD~gap==0 )~extractByGap(t, h :: acc) \\ extractByGap(t, acc) \end{array}\right.$

Si $(iter~MOD~gap==0 )$ es una funcion auxiliar $condicionGap(s)$, la cual retorna $Nil$ o $s$:

$condicionGap(s)=\left\lbrace\begin{array}{c} Nill \\ s \end{array}\right.$

Por lo que:

* $extractByGap(s_1,s_2,s_3,...,s_k) = condicionGap(s_1)::extractByGap(s_2,s_3,...,s_k)$

Se espera que el algoritmo sirva para todo k+1:

* $extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) = condicionGap(s_1)::extractByGap(s_2,s_3,...,s_k,s_{k+1})$

Por hipótesis inductiva, podemos agrupar todo lo que hay previo al elemento $k_{k+1}$

* $extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) = extractByGap(s_1,s_2,s_3,...,s_k)::extractByGap(s_{k+1})$

Ahora, por las propiedades de la función $extractByGap$, sabemos que $extractByGap(s_{k+1})$ es:

* $extractByGap(s_{k+1}) = condicionGap(s_{k+1})::extractByGap(Nil)$

Por lo que finalmente queda:

$$extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1})$$ 

Queda:

$$extractByGap(s_1,s_2,s_3,...,s_k,s_{k+1}) = extractByGap(s_1,s_2,s_3,...,s_k)::condicionGap(s_{k+1})$$

Finalmente,al aplicar $extractByGap$ a la lista con $k$ elementos y concatenar el resultado al retorno de $condicionGap$ para el último elemento.  
