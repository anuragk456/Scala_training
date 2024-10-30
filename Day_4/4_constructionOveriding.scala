class Animal(val name: String) {
  def this() = this("Animal")

  def describe(): Unit = {
    println(s"This is an animal named $name.")
  }
}

class Dog(override val name: String, val breed: String) extends Animal(name) {
  def this(breed: String) = {
    this("Unnamed Dog", breed)
  }

  def this() = {
    super("Animal")
    this("Unnamed Dog", "Unknown Breed")
  }

  override def describe(): Unit = {
    println(s"This is a dog named $name, and it is a $breed.")
  }
}

object ConstructorOverriding {
  def main(args: Array[String]): Unit = {
    val dog = new Dog("Buddy", "Golden Retriever")
    dog.describe()

    val unnamedDog = new Dog("Bulldog")
    unnamedDog.describe()

    val defaultDog = new Dog()
    defaultDog.describe()
  }
}
