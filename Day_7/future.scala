import scala.concurrent.{Future, ExecutionContext}
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
def testing(): Future[String] = {
  Future {
    println("Inside the function.....")
    // Thread.sleep(3000) 
    "Hey, I am ok.. -----------------------------------"   
  }
}

@main def abc(): Unit = {
  val resultFuture = testing();
  
  resultFuture.onComplete {
    case Success(value) => println(s"Future completed with: $value")
    case Failure(exception) => println(s"Future failed with: $exception")
  }

  println("Inside the main function");
  for(x<-1 to 50){
    println("Inside the main function");
  }
  Thread.sleep(500);
}
