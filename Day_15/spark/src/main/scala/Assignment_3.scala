import org.apache.spark.sql.SparkSession

object Assignment_3 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Analyzing Tasks and Executors")
//      .config("spark.executor.instances", 2)
      .master("local[*]")
      .getOrCreate()

    val initialRDD = spark.sparkContext.parallelize(Seq.fill(1000000)("Lorem ipsum dolor sit amet"), 4)
    println(s"Initial number of partitions: ${initialRDD.getNumPartitions}")

    val wordsRDD = initialRDD.flatMap(line => line.split(" "))

    val keyValueRDD = wordsRDD.map(word => (word, 1))

    val wordCountsRDD = keyValueRDD.reduceByKey(_ + _)

    println("Preview of word counts (first 10):")
    wordCountsRDD.take(10).foreach(println)

    val outputPath = "output/word_counts"
    wordCountsRDD.saveAsTextFile(outputPath)

    println(s"Results saved to: $outputPath")
    println("\nResults saved. Check the Spark UI for the save operation.")

    println("Press ENTER to exit the application...")
    scala.io.StdIn.readLine()

    spark.stop()
  }
}
