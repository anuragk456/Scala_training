abstract class Pet (name: String) {
    def speak(): Unit = println("Yo")   
    def comeToMaster(): Unit     
}

trait abc {
    def a(): Unit ={
        println("inside ABC");
    }
}

class Dog(name: String) extends Pet(name) {
    // override def speak() = println("Woof")
    def comeToMaster() = println("Here I come!")
}

object AbstractClass {
    def main(args:Array[String])={
        var dog = new Dog("bullDog");

        dog.speak();
        dog.comeToMaster();
    }
}


