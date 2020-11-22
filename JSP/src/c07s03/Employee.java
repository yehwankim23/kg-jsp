package c07s03;

public class Employee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private int salary;
	private int managerId;
	private int departmentId;

	public int getEmployeeId() {
		return employeeId;

	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;

	}

	public String getFirstName() {
		return firstName;

	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

	}

	public String getLastName() {
		return lastName;

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	public int getSalary() {
		return salary;

	}

	public void setSalary(int salary) {
		this.salary = salary;

	}

	public int getManagerId() {
		return managerId;

	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;

	}

	public int getDepartmentId() {
		return departmentId;

	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;

	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", managerId=" + managerId + ", departmentId=" + departmentId + "]";

	}

}
