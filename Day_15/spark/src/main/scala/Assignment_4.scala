import org.apache.spark.sql.SparkSession

object Assignment_4 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Exploring DAG and Spark UI")
      .master("local[*]")
      .getOrCreate()

    val initialRDD = spark.sparkContext.parallelize(1 to 1000000, 4)
    println(s"Initial number of partitions: ${initialRDD.getNumPartitions}")

    val evenRDD = initialRDD.filter(num => num%2 == 0);
    println("Applied Filter: Kept only even numbers.")

    val multiRDD = evenRDD.map(num => num * 10)
    println("Applied Map (1): Multiplied each number by 10.")

    val tupleRDD = multiRDD.map(num => (num % 100, num))
    println("Applied Map (2): Generated (remainder, number) tuples.")

    val reducedRDD = tupleRDD.reduceByKey(_ + _)
    println("Applied ReduceByKey: Grouped by key and summed values.")

    val result = reducedRDD.collect()
    println("Collected Results:")
    result.take(10).foreach(println)

    println("\nExecution complete. Check the Spark UI to analyze the DAG and stages.")
    println("Press ENTER to exit...")
    scala.io.StdIn.readLine()

    spark.stop()
  }
}
