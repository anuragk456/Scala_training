import org.apache.spark.sql.SparkSession
object Assignment_5 {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Assignment_5")
      .master("local[*]")
      .getOrCreate()

    val records = List((1, 80), (2, 90), (3, 85), (4, 70), (5, 95))

    val x = spark.sparkContext.parallelize(records)

    val score = x.map { case (_, score) => score }
    val totalScore = score.reduce(_ + _)
    val count = score.count()
    val averageScore = totalScore.toDouble / count

    println(s"Average Score: $averageScore")

    spark.stop()
  }
}
