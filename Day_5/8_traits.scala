trait TailWagger {
    def startTail(): Unit
    def stopTail(): Unit

    def wagTail(action: () => Unit): Unit = {
        startTail()
        action()
        stopTail()
    }
}

trait TailWagger1 {
    def speak(): Unit = println(" hey hiiii ")
    def run(): Unit
    def stop(): Unit
}


class Dog extends TailWagger with TailWagger1 {
    def startTail(): Unit = println("Tail started wagging!")
    def stopTail(): Unit = println("Tail stopped wagging!")

    def run(): Unit = println("Dog is running!")
    def stop(): Unit = println("Dog has stopped running!")
}

object Main {
    def main(args: Array[String]): Unit = {
        val dog = new Dog()
        dog.speak();
        dog.wagTail(() => {
            dog.run()
        })
    }
}
