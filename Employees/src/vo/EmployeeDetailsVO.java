package vo;

public class EmployeeDetailsVO extends EmployeesVO {
	private String jobTitle;
	private String managerFirstName;
	private String managerLastName;
	private String departmentName;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getManagerFirstName() {
		return managerFirstName;
	}

	public void setManagerFirstName(String managerFirstName) {
		this.managerFirstName = managerFirstName;
	}

	public String getManagerLastName() {
		return managerLastName;
	}

	public void setManagerLastName(String managerLastName) {
		this.managerLastName = managerLastName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "EmployeeDetailsVO [jobTitle=" + jobTitle + ", managerFirstName=" + managerFirstName
				+ ", managerLastName=" + managerLastName + ", departmentName=" + departmentName + "]";
	}

}
