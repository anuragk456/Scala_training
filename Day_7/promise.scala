import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

def createThreadWithPromise(): Future[String] = {
  val promise = Promise[String]();
  
  val thread = new Thread(new Runnable {
    def run(): Unit = {
      println("Thread started...")
      Thread.sleep(2000)
      val result = "Hello from the thread!"
      promise.success(result)
      println("Thread finished!")
    }
  });

  thread.start();
  promise.future;
}

@main def abc(): Unit = {
  val future = createThreadWithPromise();

  future.onComplete {
    case Success(value) => println(s"Future completed with: $value")
    case Failure(exception) => println(s"Future failed with exception: $exception")
  }

  println("Main thread is waiting for the thread result...")
  Thread.sleep(3000);
}
