class A {
  println("A this is the parent class: ")
}

class B(str: String) extends A {
  println("B this is the child class: " + str)
}

object HelloWorld {
  def main(args: Array[String]): Unit = {
    val b = new B("Anurag")
  }
}
