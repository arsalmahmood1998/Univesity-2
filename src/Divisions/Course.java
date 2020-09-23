package Divisions;

import java.util.ArrayList;

import People.Staff;
import People.Student;

public class Course {
	private int id;
	private String name;
	private int creditHours;
	private ArrayList<Student>enrolledBy;
	private ArrayList<Department>offeredBy;
	private Staff instructor;
	private Department department;
	public Course(int id, String name, int creditHours,Staff instructor,Department department) {
		super();
		this.id = id;
		this.name = name;
		this.creditHours = creditHours;
		this.instructor = instructor;
		this.department=department;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreditHours() {
		return creditHours;
	}
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	public ArrayList<Student> getEnrolledBy() {
		return enrolledBy;
	}
	public void setEnrolledBy(ArrayList<Student> enrolledBy) {
		this.enrolledBy = enrolledBy;
	}
	public ArrayList<Department> getOfferedBy() {
		return offeredBy;
	}
	public void setOfferedBy(ArrayList<Department> offeredBy) {
		this.offeredBy = offeredBy;
	}
	public Staff getInstructor() {
		return instructor;
	}
	public void setInstructor(Staff instructor) {
		this.instructor = instructor;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", " + (name != null ? "name=" + name + ", " : "") + "creditHours=" + creditHours
				+ ", " + (enrolledBy != null ? "enrolledBy=" + enrolledBy + ", " : "")
				+ (offeredBy != null ? "offeredBy=" + offeredBy + ", " : "")
				+ (instructor != null ? "instructor=" + instructor + ", " : "")
				+ (department != null ? "department=" + department : "") + "]";
	}
	
}
