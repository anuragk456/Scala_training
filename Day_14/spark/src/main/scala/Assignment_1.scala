import org.apache.spark.sql.SparkSession

object Assignment_1 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Assignment_1")
      .master("local[*]")
      .getOrCreate()

    val temp = List(
      "Spark is fast and reliable",
      "RDDs provide distributed data structures",
      "Word count example using RDD"
    )

    val perllel = spark.sparkContext.parallelize(temp);
    val words = perllel.flatMap(line => line.split(" "))
    val count = words.count()



    println(s"Total number of words: $count")

    spark.stop()
  }
}
