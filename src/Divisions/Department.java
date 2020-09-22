package Divisions;

import People.Staff;
import People.Student;

import java.util.ArrayList;

public class Department{
	private int id;
	private String name;   
	private Staff head;
	private ArrayList<Student> students;
	private ArrayList<Staff>staffMembers;
	private ArrayList<Course>courses;
	private ArrayList<Lab>labs;
	private ArrayList<Location>locations;
	public Department(int id, String name, Staff head) {
		super();
		this.id = id;
		this.name = name;
		this.head = head;
		this.students= new ArrayList<Student>();
		this.staffMembers= new ArrayList<Staff>();
		this.courses= new ArrayList<Course>();
		this.labs= new ArrayList<Lab>();
		this.locations= new ArrayList<Location>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Staff getHead() {
		return head;
	}
	public void setHead(Staff head) {
		this.head = head;
	}
	public ArrayList<Student> getStudents() {
		return students;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void addStudent(Student newStudent) {
		this.students.add(newStudent);
	}
	public boolean removeStudent(Student oldStudent) {
		
		return this.students.remove(oldStudent); 
	}
	

	public ArrayList<Staff> getStaffMembers() {
		return staffMembers;
	}

	public void setStaffMembers(ArrayList<Staff> staffMembers) {
		this.staffMembers = staffMembers;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Lab> getLabs() {
		return labs;
	}

	public void setLabs(ArrayList<Lab> labs) {
		this.labs = labs;
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
				+ (head != null ? "head=" + head + ", " : "") + (students != null ? "students=" + students + ", " : "")
				+ (staffMembers != null ? "staffMembers=" + staffMembers + ", " : "")
				+ (courses != null ? "courses=" + courses + ", " : "") + (labs != null ? "labs=" + labs + ", " : "")
				+ (locations != null ? "locations=" + locations : "") + "]";
	}	
}
