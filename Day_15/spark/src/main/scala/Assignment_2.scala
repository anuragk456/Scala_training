import org.apache.spark.sql.SparkSession

object Assignment_2 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Narrow vs Wide Transformations")
      .master("local[*]")
      .getOrCreate()

    val initialRDD = spark.sparkContext.parallelize(1 to 10000000, 4)
    println(s"Initial number of partitions: ${initialRDD.getNumPartitions}")

    val mappedRDD = initialRDD.map(num => num * 2)
    println("Narrow Transformation (map) applied.")

    val filteredRDD = mappedRDD.filter(num => num % 4 == 0)
    println("Narrow Transformation (filter) applied.")

    println("Preview of filtered data (first 10 elements):")
    filteredRDD.take(10).foreach(println)

    val kvRDD = filteredRDD.map(num => (num % 10, num))
    val groupedRDD = kvRDD.groupByKey()
    println("Wide Transformation (groupByKey) applied.")

    println("Preview of grouped data (first 10 keys with values):")
    groupedRDD.take(10).foreach { case (key, values) =>
      println(s"Key: $key, Values: ${values.take(5).mkString(", ")}...")
    }


    val outputPath = "output/narrow_vs_wide_results"
    groupedRDD.saveAsTextFile(outputPath)
    println(s"Results saved to: $outputPath")
    
    println("\nStep 3 complete: Check the Spark UI for the coalesce operation.")
    println("Press ENTER to finish...")
    
    readLine()

    spark.stop()
  }
}
