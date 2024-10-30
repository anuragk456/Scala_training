trait TailWagger {
    def startTail(): Unit
    def stopTail(): Unit
}

trait TailWagger1 {
    def run(): Unit
    def stop(): Unit
}

class Dog extends TailWagger, TailWagger1{
    def startTail(): Unit = println("tail is wagging")
    def stopTail(): Unit = println("tail is stopped")
    def run(): Unit = println("tail is running ")
    def stop(): Unit = println("tail is stop running")
}

object TraitsExmp{
    def main(args: Array[String])={
        var d = new Dog();
        d.stopTail();
        d.stopTail();
        d.run();
        d.stop();
    }
}