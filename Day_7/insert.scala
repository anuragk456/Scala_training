import java.sql.{Connection, DriverManager, Statement, ResultSet, SQLException}

object JdbcSelectExample {
  
  // Database credentials
  val url = "jdbc:mysql://scaladb.mysql.database.azure.com:3306/anurag_kurmi"
  val username = "mysqladmin"
  val password = "Password@12345"
  
  def main(args: Array[String]): Unit = {
    var connection: Connection = null
    var statement: Statement = null
    var resultSet: ResultSet = null
    
    try {
      connection = DriverManager.getConnection(url, username, password);
      statement = connection.createStatement();
      
      val res = "SELECT * FROM person";
      
      resultSet = statement.executeQuery(res)
      
      println("Records in 'person' table:")
      while (resultSet.next()) {
        val personId = resultSet.getInt("person_id")
        val firstName = resultSet.getString("first_name")
        val lastName = resultSet.getString("last_name")
        
        println(s"ID: $personId, First Name: $firstName, Last Name: $lastName")
      }
      
    } catch {
      case e: SQLException => e.printStackTrace()
      case e: Exception => e.printStackTrace()
    } finally {
      try {
        if (resultSet != null) resultSet.close()
        if (statement != null) statement.close()
        if (connection != null) connection.close()
      } catch {
        case se: SQLException => se.printStackTrace()
      }
    }
  }
}
