import org.apache.spark.sql.SparkSession

object Assignment_6 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Assignment_6")
      .master("local[*]")
      .getOrCreate()

    val names = List((1, "Alice"), (2, "Bob"), (3, "Charlie"))
    val scores = List((1, 85), (2, 90), (3, 78))

    val name = spark.sparkContext.parallelize(names);
    val score = spark.sparkContext.parallelize(scores);

    val res = name.join(score)


    val resultRDD = res.map { case (id, (name, score)) => (id, name, score) }

    val result = resultRDD.collect()
    println("Joined RDD (id, name, score):")

    result.foreach { case (id, name, score) =>
      println(s"($id, $name, $score)")
    }

    spark.stop()
  }
}
