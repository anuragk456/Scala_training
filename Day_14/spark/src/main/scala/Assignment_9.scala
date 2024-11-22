import org.apache.spark.sql.SparkSession

object Assignment_9 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Assignment_9")
      .master("local[*]")
      .getOrCreate()


    val nums = spark.sparkContext.parallelize(1 to 100)

    val sum = nums.reduce(_ + _)

    println(s"Total sum : $sum")

    spark.stop()
  }
}
