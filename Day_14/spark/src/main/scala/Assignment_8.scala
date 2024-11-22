import org.apache.spark.sql.SparkSession

object Assignment_8 {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Assignment_8")
      .master("local[*]")
      .getOrCreate()

    val csvData = List(
      "1,John,25",
      "2,Mary,17",
      "3,Paul,19",
      "4,Linda,16",
      "5,Steve,30"
    )

    val l1 = spark.sparkContext.parallelize(csvData)

    val temp = l1.map(row=>{
      val columns = row.split(",")
      (columns(0).toInt, columns(1), columns(2).toInt)
    })

    val res  = temp.filter{ case (_, _, age) => age >= 18 }
    val result = res.collect()

    println("Filtered Records (age >= 18):")
    result.foreach { case (id, name, age) =>
      println(s"($id, $name, $age)")
    }

    spark.stop()
  }
}

