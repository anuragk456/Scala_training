import org.apache.spark.sql.SparkSession

object Assignment_10 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Assignment_10")
      .master("local[*]")
      .getOrCreate()

    val data = List(("A", 10), ("B", 20), ("A", 30), ("B", 40), ("C", 50))

    val temp = spark.sparkContext.parallelize(data)

    val res = temp.reduceByKey(_ + _)
    val result = res.collect()

    println("Grouped and Summed by Key:")
    result.foreach { case (key, sum) =>
      println(s"Key: $key, Sum: $sum")
    }

    spark.stop()
  }
}
