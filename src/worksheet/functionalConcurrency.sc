/*
The sum of a list of numbers using imperative style of coding.
We can see that there is presence of mutable state that makes
it difficult to distribute the work in a parallel fashion
 */
def sum(numbers: List[Int]) = {
  var totalSum = 0
  for (i <- numbers) {
    Thread.sleep(10)
    totalSum += i;
  }
  totalSum
}

//Helper function to print the elapsed time for the execution of the code block
def time[R](block: => R): R = {
  val t0 = System.nanoTime()
  val result = block // call-by-name
  val t1 = System.nanoTime()

  println("Elapsed time: " + (t1 - t0) / 1000000000 + "s")

  result
}


/*
time taken to sum the list using the global state
 */
time(sum((1 to 1000).toList))



/*
Time taken to sum the list using a reduce function(functional paradigm).
It is fairly straightforward to execute this block in parallel
 */
time((1 to 1000).reduce((x, y) => {
  //Represent an expensive time consuming operation like an API call
  Thread.sleep(10)
  x + y
}))



/*
Time taken to sum the list using a parallel reduce
 */
time((1 to 1000).par.reduce((x, y) => {
  //Represent an expensive time consuming operation like an API call
  Thread.sleep(10)
  x + y
}))
