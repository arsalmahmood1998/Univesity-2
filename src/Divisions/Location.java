package Divisions;
public class Location {
	private int id;
	private String name;
	private String area;
	private int noOfRooms;
	private Department department;
	public Location(int id, String name, String area, int noOfRooms, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.noOfRooms = noOfRooms;
		this.department = department;	}
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getNoOfRooms() {
		return noOfRooms;
	}
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
				+ (area != null ? "area=" + area + ", " : "") + "noOfRooms=" + noOfRooms + ", "
				+ (department != null ? "department=" + department + ", " : "")
				+ (getClass() != null ? "getClass()=" + getClass() + ", " : "") + "hashCode()=" + hashCode() + ", "
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}
	
}
