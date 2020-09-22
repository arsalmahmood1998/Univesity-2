 import java.util.ArrayList;
import java.util.TreeMap;

import java.sql.*;

import Divisions.Course;
import Divisions.Department;
import Divisions.Lab;
import Divisions.Location;
import People.Staff;
import People.Student;
import Divisions.Designation;
public class App {
	TreeMap<Integer,Staff>staffMembers=new TreeMap<Integer,Staff>();
	TreeMap<Integer,Department>department=new TreeMap<Integer,Department>();
	TreeMap<Integer,Student>students=new TreeMap<Integer,Student>();
	TreeMap<Integer,Course>courses=new TreeMap<Integer,Course>();
	TreeMap<Integer,Location>locations=new TreeMap<Integer,Location>();
	TreeMap<Integer,Lab>labs=new TreeMap<Integer,Lab>();
	TreeMap<Integer,Designation>designations=new TreeMap<Integer,Designation>();

	Connection dbConnection;
	App(){
		
	}
	void run(Connection connection) throws SQLException{
		
		dbConnection=connection;
		getAllStaffMembersFromDataBase();
		System.out.println(staffMembers.get(13));
		//getAllDepartmentsFromDataBase();
		//System.out.println(department.values());
		//getAllStudentFromDataBase();
		//System.out.println(students.values());
		//addStudentInDepartment(1);
		//System.out.println("Students in Computer Science"+department.get(1).getStudents());
		//addStudentInDepartment(2);
		//System.out.println("Students in Business Studies"+department.get(2).getStudents());
		//addStudentInDepartment(3);
		//System.out.println("Students in Business Studies"+department.get(2).getStudents());
		//addStaffInDepartment(1);
		//addCoursesInDepartment(1);
		//addLabsInDepartment(1);
		//addLocationInDepartment(1);
		//addStaffInDepartment(2);
		//addLabsInDepartment(2);
		//addLocationInDepartment(2);
		//addCoursesInDepartment(2);
		//addCoursesInDepartment(3);
		//addLabsInDepartment(3);
		//addStaffInDepartment(3);
		//addLocationInDepartment(3);
		//getAllCoursesFromDataBase();
		//getAllLocationsFromDatBase();
		//getAllLabsFromDatBase();
		//getAllDesignationsFromDataBase();
		//addDepartmentInStaff(1);
		//addDepartmentInStaff(2);
		//addDepartmentInStaff(3);
		//addDepartmentInStaff(4);
		//addDepartmentInStaff(5);
		//addDepartmentInStaff(6);
		//addDepartmentInStaff(7);
		//addDepartmentInStaff(8);
		//addDepartmentInStaff(9);
		//addDepartmentInStaff(10);
		//addDepartmentInStaff(11);
		//addDepartmentInStaff(12);
		//addDepartmentInStaff(13);
		//addDepartmentInStaff(14);
		//addDepartmentInStaff(15);
		//addDepartmentInStaff(16);
		//addCoursesToStaff(10);
		//assignCoursesToStaff(11);
	}
	/**
	 *
	 * @param id
	 * @return Staff Member
	 * @throws SQLException
	 */
	Staff getStaffId(int id) throws SQLException{
		if(staffMembers.containsKey(id)) {
			return staffMembers.get(id);
			}
		else {
			Statement st = dbConnection.createStatement();
			ResultSet rows =st.executeQuery("select * from staff where id="+id);
			while(rows.next()) {
				int _id = rows.getInt("id");
				String name = rows.getString("name");
				String eMail = rows.getString("eMail");
				String phone = rows.getString("phone");
				int supervisorId=rows.getInt("supervisor");
				Staff supervisor=null;
				Staff staffMember=new Staff(name,phone,eMail,_id,supervisor);
				if(staffMembers.containsKey(supervisorId)) {
					staffMember.setSupervisor(staffMembers.get(supervisorId));
					}
				else {
					getStaffId(supervisorId);
					staffMember.setSupervisor(staffMembers.get(supervisorId));
					}
				return staffMember;
			}
		}
		return null;		
	}
	/**
	 * Adds Staff Member into Map
	 * @throws SQLException
	 */
	void getAllStaffMembersFromDataBase() throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from Staff");
		while(rows.next()) {
	    Staff newStaff=getStaffId(rows.getInt("id"));
	    staffMembers.put(rows.getInt("id"), newStaff);
		}
	}
	
	/**
	 * Adds Departments in map
	 * @throws SQLException
	 */
	void getAllDepartmentsFromDataBase() throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from department");
		while(rows.next()) {
			Department newDepartment=getDepartment(rows.getInt("id"));
			department.put(rows.getInt("id"), newDepartment);
		}
	}
	/**
	 * returns Department from Data Base
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	Department getDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from department where id="+id);
		Department department=null;
		while(rows.next()) {
			id =rows.getInt("id");
			String name=rows.getString("name");
			Staff head=getStaffId(rows.getInt("HOD"));
			department=new Department(id,name,head);
		}
		return department;
	}
	/**
	 * returns Student
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	Student getStudent(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from student where id="+id);
		Student newStudent=null;
		while(rows.next()) {
			id = rows.getInt("id");
			String name = rows.getString("name");
			String eMail = rows.getString("eMail");
			String phone = rows.getString("phone");
			Department department=getDepartment(rows.getInt("departmentId"));
			newStudent=  new Student(name,phone,eMail,id,department);
		}
		return newStudent;
	}
	/**
	 * Adds student in department
	 * @throws SQLException
	 */
	void addStudentInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from student where DepartmentID="+id);
		while(rows.next()) {
			Student newStudent=students.get(rows.getInt("id"));
			ArrayList<Student> students=new ArrayList<Student>();
			students.add(newStudent);
			department.get(id).setStudents(students);
		}
	}
	/**
	 * Add Staff Members in Department
	 * @param id
	 * @throws SQLException
	 */
	void addStaffInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from staff where id in (select StaffID from staffworksfor where DeptID ="+id+")");
		while(rows.next()) {
			Staff newStaff=getStaffId(rows.getInt("id"));
			ArrayList<Staff> staffMembers=new ArrayList<Staff>();
			staffMembers.add(newStaff);
			department.get(id).setStaffMembers(staffMembers);
			System.out.println(newStaff);
		}
	}
	/**
	 * Adds Courses in Departments
	 * @param id
	 * @throws SQLException
	 */
	void addCoursesInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from course where DepartmentId="+id);
		while(rows.next()) {
			Course newCourse=getCourse(rows.getInt("id"));
			ArrayList<Course> courses=new ArrayList<Course>();
			courses.add(newCourse);
			department.get(id).setCourses(courses);
			System.out.println(newCourse);
		}
	}
	/**
	 * Adds Labs in Departments
	 * @param id
	 * @throws SQLException
	 */
	void addLabsInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from lab where DepartmentId="+id);
		while(rows.next()) {
			Lab newLab=getLab(rows.getInt("id"));
			ArrayList<Lab> labs=new ArrayList<Lab>();
			labs.add(newLab);
			department.get(id).setLabs(labs);
			System.out.println(newLab);
		}
	}
	/**
	 * Adds Location in Departments
	 * @param id
	 * @throws SQLException
	 */
	void addLocationInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from location where DeptId="+id);
		while(rows.next()) {
			Location newLocation=getLocation(rows.getInt("id"));
			ArrayList<Location> labs=new ArrayList<Location>();
			labs.add(newLocation);
			department.get(id).setLocations(labs);
			System.out.println(newLocation);
		}
	}
	void addDepartmentInStaff(int id)throws SQLException{
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from department where ID in (select DeptID from staffWorksfor where StaffID="+id+")");
		while(rows.next()){
			Department newDepartment=getDepartment(rows.getInt("ID"));
			staffMembers.get(id).setDepartment(newDepartment);
			}
	}
	/**
	 * Assigns Courses to Staff Members
	 * @param id
	 * @throws SQLException
	 */
	void assignCoursesToStaff(int id)throws SQLException{
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from course where InstructorID="+id);
		while(rows.next()) {
			Course newCourse=getCourse(rows.getInt("id"));
			System.out.println(id);
			System.out.println(newCourse);
			staffMembers.get(id).setCourse(newCourse);
			System.out.println(newCourse);
		}
	}
	/**
	 * Adds Students from Database
	 * @throws SQLException
	 */
	void getAllStudentFromDataBase() throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from student");
		while(rows.next()) {
			Student newStudent=getStudent(rows.getInt("id"));
			students.put(rows.getInt("id"), newStudent);
			System.out.println(students.get(rows.getInt("id")));
		}
	}
	/**
	 * returns Course
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	Course getCourse(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from Course where id="+id);
		Course newCourse=null;
		while(rows.next()) {
			id=rows.getInt("id");
			String name=rows.getString("name");
			Staff instructor=getStaffId(rows.getInt("instructorId"));
			Department department=getDepartment(rows.getInt("departmentId"));
			int creditHours=rows.getInt("creditHours");
			newCourse=new Course(id,name,creditHours,instructor,department);
		}
		return newCourse;
	}
	
	/**
	 * Adds Courses into Map
	 * @throws SQLException
	 */
	void getAllCoursesFromDataBase()throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from course");
		while(rows.next()) {
			Course newCourse=getCourse(rows.getInt("id"));
			courses.put(rows.getInt("id"),newCourse);
			System.out.println(newCourse);
		}
	}
	/**
	 * return Location
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	Location getLocation(int id) throws SQLException {
		Location location=null;
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from location where id="+id);
		while(rows.next()) {
			id=rows.getInt("ID");
			String name=rows.getString("Name");
			String area=rows.getString("DesignatedArea");
			Department department=getDepartment(rows.getInt("DeptId"));
			int rooms=rows.getInt("NoOfRooms");
			location=new Location(id,name,area,rooms,department);
			}
		return location;
		}
	/**
	 * Adds Location From Data Base
	 * @throws SQLException
	 */
	void getAllLocationsFromDatBase() throws SQLException{
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from location");
		while(rows.next()) {
			Location newLocation=getLocation(rows.getInt("id"));
			locations.put(rows.getInt("id"), newLocation);
			System.out.println(locations.get(rows.getInt("id")));
			}
		}
	/**
	 * returns Lab from Data Base
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	Lab getLab(int id) throws SQLException{
		Lab lab=null;
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from lab where id="+id);
		while(rows.next()) {
			id=rows.getInt("ID");
			String name=rows.getString("Name");
			Location location=getLocation(rows.getInt("Location"));
			Department department=getDepartment(rows.getInt("DepartmentId"));
			lab=new Lab(id,name,department,location);
			}
		return lab;
		}
	/**
	 * Adds Lab into Map
	 * @throws SQLException
	 */
	void getAllLabsFromDatBase() throws SQLException{
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from lab");
		while(rows.next()) {
			Lab newLab=getLab(rows.getInt("id"));
			labs.put(rows.getInt("id"), newLab);
			System.out.println(labs.get(rows.getInt("id")));
			}
		}
	/**
	 * returns Designation from Data Base
	 */
	Designation getDesignation(int id) throws SQLException{
		Designation newDesignation=null;
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from designation where DesignationID="+id);
		while(rows.next()) {
			id=rows.getInt("DesignationID");
			String title=rows.getString("DesignationTitle");
			String salary=rows.getString("SalaryInRupess");
			newDesignation=new Designation(id,title,salary);
			}
		return newDesignation;
		}
	/**
	 * Adds Designations into Tree Map
	 * @throws SQLException
	 */
	void getAllDesignationsFromDataBase()throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from designation");
		while(rows.next()) {
			Designation newDesignation=getDesignation(rows.getInt("DesignationID"));
			designations.put(rows.getInt("DesignationID"), newDesignation);
			System.out.println(designations.get(rows.getInt("DesignationID")));
			}
		}
	}
