import org.apache.spark.sql.SparkSession

object Assignment_5 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Partitioning Impact on Performance")
      .master("local[*]")
      .getOrCreate()

    val largeDataset = spark.sparkContext.parallelize(1 to 1000000).map(i => s"Row_$i")

    val partitionsList = List(2, 4, 8)

    partitionsList.foreach { numPartitions =>
      println(s"\n--- Processing with $numPartitions Partitions ---")

      val partitionedRDD = largeDataset.repartition(numPartitions)
      println(s"Number of partitions: ${partitionedRDD.getNumPartitions}")

      val rowCount = partitionedRDD.count()
      println(s"Row count: $rowCount")

      val sortedRDD = partitionedRDD.sortBy(row => row)
      println(s"Data sorted using $numPartitions partitions.")

      val outputPath = s"output/partition_$numPartitions"
      sortedRDD.saveAsTextFile(outputPath)
      println(s"Output saved to: $outputPath")
    }

    println("\nAll operations complete. Check the Spark UI for analysis.")
    scala.io.StdIn.readLine("Press ENTER to exit the application...")
    spark.stop()
  }
}
