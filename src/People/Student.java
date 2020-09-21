package People;
import Divisions.Department;

public class Student extends Person {
	private int id;
	private Department department;
	
	public Student(String name, String phone, String email, int id, Department department) {
		super(name, phone, email);
		this.id = id;
		this.department = department;
	}
	
	public int getId() {
		return id;
	}

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", " + (department != null ? "department=" + department + ", " : "") + "getId()="
				+ getId() + ", " + (getDepartment() != null ? "getDepartment()=" + getDepartment() + ", " : "")
				+ (getName() != null ? "getName()=" + getName() + ", " : "")
				+ (getPhone() != null ? "getPhone()=" + getPhone() + ", " : "")
				+ (getEmail() != null ? "getEmail()=" + getEmail() + ", " : "")
				+ (getClass() != null ? "getClass()=" + getClass() + ", " : "") + "hashCode()=" + hashCode() + ", "
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}
	
}
