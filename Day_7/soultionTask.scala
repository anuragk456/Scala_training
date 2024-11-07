// Create A function that returns a Future[String].
// The function name is RandomNumberThreadExecutor 
// that function should have a promise created and return associated future. 

// In the function  create three  threads where each thread has a logic to generate a random number. 

// first thread name is firstThread. second thread name is secondThread. third thread name is thirdThread.
// all three threads runs infinetly. whenever any of the thread gets 1567 as the random number.
// it should resolve the promise by using success with. the message threadname+ " has generated " + 1567.
// it should stop and it should also notify (find a mechanism). other threads to stop.
// the main thread should wait for the future to be. complete and print the message.

import scala.concurrent.{Future, Promise};
import scala.concurrent.ExecutionContext.Implicits.global;
import scala.util.{Success, Failure};
import scala.util.Random;

def RandomNumberThreadExecutor(): Future[String] = {
  val promise = Promise[String]();
  var flag = false;
  
  val thread1 = new Thread(new Runnable {
    def run(): Unit = {
      while(!flag){
        val rndNum = Random.nextInt(10000);
        println(s"Thread 1 generated: $rndNum");
        if(rndNum==1567){
          flag = true;
          promise.success(s"Thread 1 has generated ${rndNum}");
          println("Thread 1 finished!")
        }    
      }
    }
  });

  val thread2 = new Thread(new Runnable {
    def run(): Unit = {
      while(!flag){ 
        val rndNum = Random.nextInt(10000);
        println(s"Thread 2 generated: $rndNum");
        if(rndNum==1567){
          flag = true;
          promise.success(s"Thread 2 has generated ${rndNum}");
          println("Thread 2 finished!")
        }
      }    
    }
  });

  val thread3 = new Thread(new Runnable {
    def run(): Unit = {
      while(!flag){
        val rndNum = Random.nextInt(10000);
        println(s"Thread 3 generated: $rndNum");
        if(rndNum==1567){
          flag = true;
          promise.success(s"Thread 3 has generated ${rndNum}");
          println("Thread 3 finished!")
        }    
      }
    }
  });

  thread1.start();
  thread2.start();
  thread3.start();
  promise.future;
}

@main def abc(): Unit = {
  val future = RandomNumberThreadExecutor();
  future.onComplete {
    case Success(value) => println(s"Future completed with: $value")
    case Failure(exception) => println(s"Future failed with exception: $exception")
  }
  println("Main thread is waiting for the thread result...");
}
