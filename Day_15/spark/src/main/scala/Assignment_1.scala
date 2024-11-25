import org.apache.spark.sql.SparkSession

object Assignment_1 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("RDD Partitioning")
      .master("local[*]")
      .getOrCreate()

    val initialRDD = spark.sparkContext.parallelize(1 to 10000000, 4)
    println(s"Initial number of partitions: ${initialRDD.getNumPartitions}")

    println("Initial partition data:")
    initialRDD.glom().collect().zipWithIndex.foreach { 
      case (partitionData, idx) => println(s"Partition $idx: ${partitionData.take(10).mkString(", ")}")
    }

    val repartitionedRDD = initialRDD.repartition(8)
    println(s"Number of partitions after repartition: ${repartitionedRDD.getNumPartitions}")


    println("Partition data after repartition:")
    repartitionedRDD.glom().collect().zipWithIndex.foreach { 
      case (partitionData, idx) => println(s"Partition $idx: ${partitionData.take(10).mkString(", ")}")
    }

    
    val coalescedRDD = repartitionedRDD.coalesce(2)
    println(s"Number of partitions after coalesce: ${coalescedRDD.getNumPartitions}")

    println("Partition data after coalesce:")
    coalescedRDD.glom().collect().zipWithIndex.foreach { 
      case (partitionData, idx) => println(s"Partition $idx: ${partitionData.take(10).mkString(", ")}")
    }

    val finalResult = coalescedRDD.collect()
    println("\nStep 3 complete: Check the Spark UI for the coalesce operation.")
    println("Press ENTER to finish...")
    readLine()
    println(s"Final result count: ${finalResult.length}")

    spark.stop()
  }
}
