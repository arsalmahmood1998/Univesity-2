package People;

import Divisions.Course;

//import java.util.ArrayList;

import Divisions.Department;

public class Staff extends Person {
	private int id;
	private Staff supervisor;
	private Department department;
	private Course course;
	
	public Staff(String name, String phone, String email, int id, Staff supervisor) {
		super(name, phone, email);
		this.id = id;
		this.supervisor = supervisor;
		//this.departments=new ArrayList<Department>();
	}
	
	public Staff(String name, String phone, String email, int id) {
		super(name, phone, email);
		this.id = id;
		this.supervisor=null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Staff getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Staff supervisor) {
		this.supervisor = supervisor;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", " + (supervisor != null ? "supervisor=" + supervisor + ", " : "")
				+ (department != null ? "department=" + department + ", " : "")
				+ (course != null ? "course=" + course + ", " : "")
				+ (getName() != null ? "getName()=" + getName() + ", " : "")
				+ (getPhone() != null ? "getPhone()=" + getPhone() + ", " : "")
				+ (getEmail() != null ? "getEmail()=" + getEmail() + ", " : "")
				+ (super.toString() != null ? "toString()=" + super.toString() + ", " : "")
				+ (getClass() != null ? "getClass()=" + getClass() + ", " : "") + "hashCode()=" + hashCode() + "]";
	}
	
}
