// class ABC {
//   val name: String = "Anurag Kurmi";
//   var age: Int = 30;

//   def printName(): Unit = {
//     println(s"Name: $name - Age: $age")
//   }
// }

// object Main {
//   def main(args: Array[String]): Unit = {
//     val a = new ABC();
//     a.printName();
//   }
// }


// class ABC(name: String = "Anurag Kurmi", age: Int = 30) {
//   def printName(): Unit = {
//     println(s"Name: $name - Age: $age")
//   }
// }

// object Main {
//   def main(args: Array[String]): Unit = {
//     val a = new ABC();
//     a.printName();
//   }
// }


class BankAccount(private var balance: Double) {
  
  def deposit(amount: Double): Unit = {
    if (amount > 0) {
      balance += amount
      println(s"Deposited: $$amount, New Balance: $$balance")
    } else {
      println("Deposit amount must be positive!")
    }
  }

  def withdraw(amount: Double): Unit = {
    if (amount > 0 && amount <= balance) {
      balance -= amount
      println(s"Withdrew: $$amount, New Balance: $$balance")
    } else {
      println("Insufficient funds or invalid amount!")
    }
  }

  def getBalance: Double = balance
}

object Main {
  def main(args: Array[String]): Unit = {
    val account = new BankAccount(1000.0)

    account.deposit(500.0)
    account.withdraw(200.0)

    println(s"Final Balance: $$${account.getBalance}")
  }
}
