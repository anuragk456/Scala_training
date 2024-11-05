import java.sql.{Connection, DriverManager, PreparedStatement, SQLException}

object JdbcCreateAndInsertExample {
  
  // Database credentials
  val url = "jdbc:mysql://scaladb.mysql.database.azure.com:3306/anurag_kurmi"
  val username = "mysqladmin"
  val password = "Password@12345"
  
  def main(args: Array[String]): Unit = {
    var connection: Connection = null
    var preparedStatement: PreparedStatement = null
    
    try {
      connection = DriverManager.getConnection(url, username, password);
      val createTableSql = """
        CREATE TABLE IF NOT EXISTS person (
          person_id INT PRIMARY KEY,
          first_name VARCHAR(50),
          last_name VARCHAR(50)
        );
      """

      preparedStatement = connection.prepareStatement(createTableSql);
      preparedStatement.executeUpdate();
      println("Table 'person' created successfully!")
      
      val insertSql = "INSERT INTO person (person_id, first_name, last_name) VALUES (?, ?, ?)"
      preparedStatement = connection.prepareStatement(insertSql)
      
      val data = Seq(
        (1, "John", "Doe"),
        (2, "Jane", "Smith"),
        (3, "Robert", "Brown"),
        (4, "Emily", "Davis"),
        (5, "Michael", "Johnson")
      )
      
      for ((id, firstName, lastName) <- data) {
        preparedStatement.setInt(1, id)
        preparedStatement.setString(2, firstName)
        preparedStatement.setString(3, lastName)
        preparedStatement.executeUpdate()
      }
      
      println("5 records inserted successfully!")
      
    } catch {
      case e: SQLException => e.printStackTrace()
      case e: Exception => e.printStackTrace()
    } finally {
      try {
        if (preparedStatement != null) preparedStatement.close()
        if (connection != null) connection.close()
      } catch {
        case se: SQLException => se.printStackTrace()
      }
    }
  }
}
