package Divisions;

public class Designation {
	private int id;
	private String title;
	private String salary;
	public Designation(int id, String title, String salary) {
		super();
		this.id = id;
		this.title = title;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Designation [id=" + id + ", " + (title != null ? "title=" + title + ", " : "")
				+ (salary != null ? "salary=" + salary : "") + "]";
	}
	
}
