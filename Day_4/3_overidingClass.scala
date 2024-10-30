class Animal { 
    def number() = { 
        println("We have two animals") 
    } 
} 

class Dog extends Animal { 

    override def number() = { 
        super.number()
        println("We have two dogs") 
    } 
} 

object OverridingClass { 
    def main(args: Array[String]) = { 
        val x = new Dog()
        x.number()
    } 
}
