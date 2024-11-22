import org.apache.spark.sql.SparkSession

object Assignment_3 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Assignment_3")
      .master("local[*]")
      .getOrCreate()

    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val numbersRDD = spark.sparkContext.parallelize(numbers)

    val oddNumbersRDD = numbersRDD.filter(num => num % 2 == 0)

    val result = oddNumbersRDD.collect()
    println("Even Numbers:")
    result.foreach(println)

    spark.stop()
  }
}
