import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import scala.language.implicitConversions

// Employee case class with a custom toString method
case class Staff(id: Int, name: String, salary: Int) {
  override def toString: String = s"(${id}, ${name}, ${salary})"
}

// Department tree node, representing each department and its sub-departments and employees
case class DepartmentNode(name: String, employees: ListBuffer[Staff] = ListBuffer(), subDepartments: ListBuffer[DepartmentNode] = ListBuffer()) {
  override def toString: String = s"Department: $name"
}

// Implicit conversion from tuple to Staff instance
implicit def tupleToStaff(tuple: (Int, String, Int)): Staff = Staff(tuple._1, tuple._2, tuple._3)

class OrganizationManager {

  // Helper function to perform DFS and search for the department node
  private def findDepartment(root: DepartmentNode, departmentName: String): Option[DepartmentNode] = {
    if (root.name.equalsIgnoreCase(departmentName)) {
      return Some(root)
    }

    root.subDepartments.collectFirst {
      case child if findDepartment(child, departmentName).isDefined => findDepartment(child, departmentName).get
    }
  }

  // Function to add or update a department and its employees
  def updateDepartment(root: DepartmentNode, parentDept: String, deptName: String, newEmployees: ListBuffer[Staff]): String = {
    try {
      val parentNode = findDepartment(root, parentDept).getOrElse(throw new Exception(s"Department '$parentDept' not found"))

      val currentDeptNode = findDepartment(root, deptName).getOrElse {
        val newDeptNode = DepartmentNode(deptName)
        parentNode.subDepartments += newDeptNode
        newDeptNode
      }

      if (!parentNode.subDepartments.exists(_ eq currentDeptNode)) {
        throw new Exception(s"Department '$deptName' is not a direct child of '$parentDept'")
      }

      currentDeptNode.employees ++= newEmployees
      "Department and employee details updated successfully"
    } catch {
      case e: Exception => e.getMessage
    }
  }

  // Function to display the full organization structure
  def displayOrganization(root: DepartmentNode): Unit = {
    println()
    println(s"${root}")
    root.subDepartments.foreach(subDept => printDepartmentDetails(subDept, 1))
  }

  // Helper function for recursively printing department details
  private def printDepartmentDetails(node: DepartmentNode, level: Int): Unit = {
    println("   " * level + s"${node}")
    node.employees.foreach { emp =>
      println("   " * (level + 1) + s"Employee: $emp")
    }
    node.subDepartments.foreach { subDept =>
      printDepartmentDetails(subDept, level + 1)
    }
  }
}

// Main application that starts the interaction
def launchApp(): Unit = {
  val rootNode: DepartmentNode = DepartmentNode("Headquarters")
  val orgManager: OrganizationManager = new OrganizationManager()

  println("==== Organization Management System ====")
  var continueApp = true

  while (continueApp) {
    println("Select an option:")
    println("1. View Organization Structure")
    println("2. Modify Organization Structure")
    println("3. Exit")
    val option = readLine().trim

    option match {
      case "1" =>
        orgManager.displayOrganization(rootNode)
      case "2" =>
        val (parentDept, newDept, staffList) = gatherUserInput()
        println(orgManager.updateDepartment(rootNode, parentDept, newDept, staffList))
      case "3" =>
        continueApp = false
      case _ =>
        println("Invalid option. Please try again.")
    }
  }
}

// Function to gather user input for modifying the organization
def gatherUserInput(): (String, String, ListBuffer[Staff]) = {
  val parentDept: String = readLine("Enter the name of the parent department: ").trim
  val deptName: String = readLine("Enter the name of the department to modify: ").trim
  val staffList: ListBuffer[Staff] = ListBuffer()

  var continueInput = true
  println("=== Add Employee Details ===")
  while (continueInput) {
    println("Press:")
    println("1. To add employee details")
    println("2. To finish and save")
    val action = readLine().trim

    action match {
      case "1" =>
        val empId: Int = readLine("Enter employee ID: ").trim.toInt
        val empName: String = readLine("Enter employee name: ").trim
        val empSalary: Int = readLine("Enter employee salary: ").trim.toInt
        val employee: Staff = (empId, empName, empSalary)
        staffList += employee
      case "2" =>
        continueInput = false
      case _ =>
        println("Invalid choice. Please try again.")
    }
  }

  (parentDept, deptName, staffList)
}

// Main entry point
@main def OrganizationApp(): Unit = {
  launchApp()
}
