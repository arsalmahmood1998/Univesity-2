package Divisions;

public class Lab {
	private int id;
	private String Name;
	private Department department;
	private Location location;
	public Lab(int id, String name, Department department, Location location) {
		super();
		this.id = id;
		this.Name = name;
		this.department = department;
		this.location = location;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Lab [id=" + id + ", " + (Name != null ? "Name=" + Name + ", " : "")
				+ (department != null ? "department=" + department + ", " : "")
				+ (location != null ? "location=" + location : "") + "]";
	}
	
}
