// Scala program of method overriding 
class Shapes { 
	def Area(l:Double, b:Double, r:Double)={ 
		0.0
	} 
} 

class Rectangle extends Shapes{ 
	override def Area(l:Double, b:Double, r:Double)={ 
		(l * b) 
	} 
} 

class Circle extends Shapes{ 
	override def Area(l:Double, b:Double, r:Double)={ 
		println(" Circle ");
		return ((3.14)* r * r)
	} 
} 

object overrideClass {
	def main(args:Array[String]) = { 
		
		var rectangle = new Rectangle() 
		var circle = new Circle() 
		var shapes = new Shapes();
		println(rectangle.Area(3, 11, 4)) 
		println(circle.Area(1, 7, 10)) 
		println(shapes.Area(1, 7, 10)) 
		
	} 
} 
