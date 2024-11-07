object OptionExample extends App {
  def findById(id: Int): Option[String] = {
    val data = Map(1 -> "Alice", 2 -> "Bob", 3 -> "Charlie")
    data.get(id)
  }

  
  println(findById(2))  // Some(Bob)
  println(findById(4))  // None

  // Pattern matching with Option
  findById(1) match {
    case Some(name) => println(s"Found: $name")
    case None => println("Not found")
  }
}
