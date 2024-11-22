import org.apache.spark.sql.SparkSession
object Assignment_4 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Assignment_4").master("local[*]").getOrCreate();

    val temp = List("Spark", "is", "powerful", "and", "fast");
    val data = spark.sparkContext.parallelize(temp)

    val res = data.flatMap(line => line.toCharArray)
    val result = res.map(char => (char, 1))

    val x = result.reduceByKey(_ + _);
    val y = x.collect()

    println("Character Frequencies:")
    result.foreach { case (char, count) =>
      println(s"Character '$char' -> $count")
    }
    spark.stop()
  }
}
