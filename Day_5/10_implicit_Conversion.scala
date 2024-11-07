case class Amount(value: Double)

// implicit def intToString(x: Int): String = x.toString
implicit def doubleToAmount(x: Double): Amount = Amount(x)
implicit def stringToInt(z: String): Int = z.toInt
// implicit def stringToInt(z: String): Int = {
//   try {
//     z.toInt
//   } catch {
//     case _: NumberFormatException => throw new IllegalArgumentException(s"$z is not a valid integer")
//   }
// }

@main def exc(): Unit = {
//   val str: String = 10;
//   println(str)

  val a: Int = 15
  println(a)

  val amount: Amount = 20.5
  println(amount)

  val num: Int = "A"
  println(num)

//   try {
//     val s: Int = stringToInt("Anurag") 
//     println(s)
//   } catch {
//     case e: IllegalArgumentException => println(e.getMessage)
//   }
}
