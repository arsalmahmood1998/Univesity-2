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
		getAllDepartmentsFromDataBase();
		//System.out.println(department.values());
		//getAllStudentFromDataBase();
		//System.out.println("Students in Computer Science"+department.get(1).getStudents());
		//System.out.println("Students in Business Studies"+department.get(2).getStudents());
		//System.out.println("Students in Media  Studies"+department.get(3).getStudents());
		//System.out.println("Staff in Computer Science"+department.get(1).getStaffMembers());
		//System.out.println("Students in Business Studies"+department.get(2).getStaffMembers());
		//System.out.println("Students in Media  Studies"+department.get(3).getStaffMembers());
		//System.out.println("Courses in Computer Science"+department.get(1).getCourses());
		//System.out.println("Courses in Business Studies"+department.get(2).getCourses());
		//System.out.println("Courses in Media Studies"+department.get(3).getCourses());
		//System.out.println("Labs in Computer Science"+department.get(1).getLabs());
		//System.out.println("Labs in Business Studies"+department.get(2).getLabs());
		//System.out.println("Labs in Media Studies"+department.get(3).getLabs());
		//System.out.println("Locations Of Computer Science"+department.get(1).getLocations());
		//System.out.println("Location Of Business Studies"+department.get(2).getLocations());
		//System.out.println("Location Of Media Studies"+department.get(3).getLocations());
		getAllStaffMembers();
		System.out.println(staffMembers.values());
	}
	/**
	 *
	 * @param id
	 * @return Staff Member
	 * @throws SQLException
	 */
	Staff getStaffId(int id) throws SQLException{
		// Check if Staff Member already exists in Tree Map or not 
		if(staffMembers.containsKey(id)) {
			//If Exists return that Staff Member
			return staffMembers.get(id);
		}
		// Else Create A new Staff Member
		else {
			Statement st = dbConnection.createStatement();
			// Use Query to find the Staff Member from Data Base 
			ResultSet columns =st.executeQuery("select * from staff where id="+id);
			// Uses this while loop to find values from columns in data base 
			while(columns.next()) {
				// Store's Id in this variable
				int _id = columns.getInt("id");
				// Store's name in this variable
				String name = columns.getString("name");
				// Store's eMail in this variable
				String eMail = columns.getString("eMail");
				// Store's phone no in this variable
				String phone =columns.getString("phone");
				// Store's supervisor Id in this variable
				int supervisorId=columns.getInt("supervisor");
				// creates a new object of class Staff and initializes null value in it
				Staff supervisor=null;
				// creates new object of class Staff and initializes values from data base in it 
				Staff staffMember=new Staff(name,phone,eMail,_id,supervisor);
				// checks if the supervisor of this staff member already exists in Tree Map or not
				if(staffMembers.containsKey(supervisorId)) {
					// If supervisor exists in tree map then set supervisor of this of this staff member from Tree Map
					staffMember.setSupervisor(staffMembers.get(supervisorId));
				}
				else {
					// If supervisor does not exists in this tree map than create it
					supervisor=getStaffId(supervisorId);
					//Set supervisor of staff member 
					staffMember.setSupervisor(supervisor);
				}
				// returns the staff member created 
				return staffMember;
			}
		}
		// returns null
		return null;
	}
	/**
	 * Adds Staff Member into Map
	 * @throws SQLException
	 */
	void getAllStaffMembers() throws SQLException {
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
			ArrayList<Student> studentsInDepartment=addStudentsInDepartment(id);
			for(Student newStudent:studentsInDepartment) { 
				department.addStudent(newStudent);
			}
			ArrayList<Staff>allStaffMembers= addStaffMembersInDepartment(id);
			for(Staff staffMember:allStaffMembers) {
				department.setStaffMembers(staffMember);
			}
			ArrayList<Course>allCourses= addCoursesInDepartment(id);
			for(Course newCourse:allCourses) {
				department.setCourses(newCourse);
			}
			ArrayList<Lab>allLabs= addLabsInDepartment(id);
			for(Lab newLab:allLabs) {
				department.setLabs(newLab);
			}
			ArrayList<Location>allLocations= addLocationInDepartment(id);
			for(Location newLocation:allLocations) {
				department.setLocations(newLocation);
			}
			
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
			Department newdepartment=department.get(rows.getInt("departmentId"));
			newStudent=  new Student(name,phone,eMail,id,newdepartment);
		}
		return newStudent;
	}
	/**
	 * Adds student in department
	 * @throws SQLException
	 */
	ArrayList <Student> addStudentsInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ArrayList<Student>allStudents=new ArrayList<Student>();
		ResultSet rows =st.executeQuery("select * from student where DepartmentID="+id);
		while(rows.next()) {
			Student newStudent=getStudent(rows.getInt("id"));
			allStudents.add(newStudent);
		}
		return allStudents;	
	}
	/**
	 * Add Staff Members in Department
	 * @param id
	 * @throws SQLException
	 */
	ArrayList<Staff> addStaffMembersInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from staff where id in (select StaffID from staffworksfor where DeptID ="+id+")");
		ArrayList<Staff>allStaffMembers=new ArrayList<Staff>();
		while(rows.next()) {
			Staff newStaff=getStaffId(rows.getInt("id"));
			allStaffMembers.add(newStaff);
		}
		return allStaffMembers;
	}
	/**
	 * Adds Courses in Departments
	 * @param id
	 * @throws SQLException
	 */
	ArrayList<Course> addCoursesInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ArrayList<Course>allCourses=new ArrayList<Course>();
		ResultSet rows =st.executeQuery("select * from course where DepartmentId="+id);
		while(rows.next()) {
			Course course=getCourse(rows.getInt("id"));
			allCourses.add(course);
		}
		return allCourses;
	}
	/**
	 * Adds Labs in Departments
	 * @param id
	 * @throws SQLException
	 */
	ArrayList<Lab> addLabsInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ArrayList<Lab>allLabs=new ArrayList<Lab>();
		ResultSet rows =st.executeQuery("select * from lab where DepartmentId="+id);
		while(rows.next()) {
			Lab newLab=getLab(rows.getInt("id"));
			allLabs.add(newLab);
		}
		return allLabs;
	}
	/**
	 * Adds Location in Departments
	 * @param id
	 * @throws SQLException
	 */
	ArrayList<Location> addLocationInDepartment(int id) throws SQLException {
		Statement st = dbConnection.createStatement();
		ArrayList<Location> locations=new ArrayList<Location>();
		ResultSet rows =st.executeQuery("select * from location where DeptId="+id);
		while(rows.next()) {
			Location newLocation=getLocation(rows.getInt("id"));
			locations.add(newLocation);
			
		}
		return locations;
	}
	void addDepartmentInStaff(int id)throws SQLException{
		Statement st = dbConnection.createStatement();
		ResultSet rows =st.executeQuery("select * from department where ID in (select DeptID from staffWorksfor where StaffID="+id+")");
		while(rows.next()){
			Department newDepartment=department.get((rows.getInt("ID")));
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
			Department newDepartment=department.get((rows.getInt("departmentId")));
			int creditHours=rows.getInt("creditHours");
			newCourse=new Course(id,name,creditHours,instructor,newDepartment);
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
			Department newDepartment=department.get((rows.getInt("DeptId")));
			int rooms=rows.getInt("NoOfRooms");
			location=new Location(id,name,area,rooms,newDepartment);
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
			Department newDepartment=department.get((rows.getInt("DepartmentId")));
			lab=new Lab(id,name,newDepartment,location);
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
