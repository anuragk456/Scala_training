// case class Person(name: String, age: Int)

// object MainApp extends App {
//   val person = Person("Alice", 25)
//   println(person)  // Output: Person(Alice,25)
  
//   person match {
//     case Person(name, age) => 
//       println(s"Name: $name, Age: $age")
//     case _ => 
//       println("No match")
//   }
// }

// class Circle(val radius: Double)

// object Circle {
//   def apply(diameter: Double): Circle = {
//     new Circle(diameter / 2)
//   }

//   def unapply(circle: Circle): Option[Double] = {
//     if (circle != null) Some(circle.radius) else None
//   }
// }

object MainApp extends App {
  val circle = Circle(10) 

  println(s"Circle radius: ${circle.radius}")

  circle match {
    case Circle(radius) => 
      println(s"Matched circle with radius: $radius")
    case _ => 
      println("Not a circle")
  }
}
