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
	public void addStudent(Student newStudents) {
		this.students.add(newStudents);
	}
	public boolean removeStudent(Student oldStudent) {
		
		return this.students.remove(oldStudent); 
	}
	
	public ArrayList<Staff> getStaffMembers() {
		return staffMembers;
	}

	public void setStaffMembers(Staff staffMembers) {
		this.staffMembers.add(staffMembers);
	}

	public void setStudents(Student students) {
		this.students.add(students);
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(Course course) {
		this.courses.add(course);
	}

	public int getId() {
		return id;
	}

	public ArrayList<Lab> getLabs() {
		return labs;
	}

	public void setLabs(Lab lab) {
		this.labs.add(lab);
	}

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(Location locations) {
		this.locations.add(locations);
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
