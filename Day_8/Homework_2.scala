trait Task {
    def doTask(): Unit={
      println("This is the Task Traits inside i am in doTask method ");
    };
}

trait Cook extends Task {
  override def doTask(): Unit = {
    println("This is the Cook Traits inside i am in doTask  OverRidden method");
  }
}

trait Garnish extends Cook {
  override def doTask(): Unit = {
    println("This is the Garnish Traits inside i am in doTask  OverRidden method");
  }
}

trait Pack extends Garnish {
  override def doTask(): Unit = {
    println("This is the Pack Traits inside i am in doTask  OverRidden method");
  }
}

class Activity extends Task{
    def doActivity(): Unit = {
        doTask();
    }
}

object TraitsExmp{
  def main(args: Array[String]): Unit = {
    // val temp = new Activity with Cook with Garnish with Pack;
    // temp.doActivity();    // output : This is the Pack Traits inside i am in doTask  OverRidden method

    // val temp = new Activity with Cook with Garnish;
    // temp.doActivity(); // output: This is the Garnish Traits inside i am in doTask  OverRidden method

    // val temp = new Activity with Cook;
    // temp.doActivity(); // output: This is the Cook Traits inside i am in doTask  OverRidden method

    // val temp = new Activity;
    // temp.doActivity(); // output: This is the Cook Traits inside i am in doTask  OverRidden method


    val temp = new Activity with Pack with Cook with Garnish;
    temp.doActivity();    // output : This is the Pack Traits inside i am in doTask  OverRidden method
  }
}