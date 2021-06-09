// jdbc program to Use JDBC 'Statement' interface
//to perform create, update, insert, delete operations on database tables

package jdbcStatementInterface;

import java.sql.*;

public class jdbcDBOperations {
	
	public static void main(String[] args) {
		int studID=0;
		try {
			// connect way #1
			String url1 = "jdbc:mysql://localhost:3306/collageManagementSystemDB";
			String user = "root";
			String password = "123";

			Connection myconnection = DriverManager.getConnection(url1, user, password);
			if (myconnection != null) {
				System.out.println("Connected to the database.");
			}
			
			createTable(myconnection);
			studID=insertRecordsStudent(myconnection);
			updateRecord(myconnection);
			fetchRecords(myconnection);
			deleteRecord(myconnection, studID);		// delete the last inserted record Can send any Student id 
			
			myconnection.close(); 
		} catch (SQLException ex) {
			System.out.println("An error occurred.  user/password is invalid");
			ex.printStackTrace();
		}
	}
	
	public static void createTable(Connection myconnection) {
		try {
		Statement stmt =myconnection.createStatement();  
		stmt.executeUpdate
				("CREATE TABLE Persons(PersonID int, LastName varchar(255), FirstName varchar(255),Address varchar(255),City varchar(255))");  
		System.out.println("Table created");	 
		}catch (SQLException ex) {
			System.out.println("Table Already exists!");
			//ex.printStackTrace();
		}
	}
	
	public static void updateRecord(Connection myconnection) {
		try {
		Statement stmt =myconnection.createStatement();  
		stmt.executeUpdate
				("update tbl_student set student_name='Richa' where student_ID=5 ");  
		System.out.println("Table Record Updated");	 
		}catch (SQLException ex) {
			System.out.println("Table Already exists!");
			//ex.printStackTrace();
		}
	}
	
	public static void deleteRecord(Connection myconnection, int studID) {
		try {
		Statement stmt =myconnection.createStatement();  
		stmt.executeUpdate
				("delete from tbl_student  where student_ID= '" + studID + "' ");  
		System.out.println("Record deleted for Student ID "+ studID );	 
		}catch (SQLException ex) {
			System.out.println("Problem in deleting the record");
			//ex.printStackTrace();
		}
	}
	
	public static void fetchRecords(Connection myconnection) {
		try {
		Statement stmt =myconnection.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from tbl_Student");  
		System.out.println("Currently data present in tbl_Student");
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		//myconnection.close();  
		}catch (SQLException ex) {
			System.out.println("An error occurred. Table/column name is invalid");
			ex.printStackTrace();
		}
	}
	
	public static int insertRecordsStudent(Connection myconnection) {
		
		int studID=0;
		try {
		Statement stmt =myconnection.createStatement();  
		ResultSet rs=stmt.executeQuery("select max(student_ID) from tbl_Student");
		while(rs.next()) {
			studID= rs.getInt(1);
		}
		studID= studID+1;
		
		stmt.executeUpdate("insert into tbl_Student values('" + studID + "',1,' Sameera ','Shivaji park','32321321',0,0) ");
		System.out.println("record inserted in table for student ID : "+ studID);
		}
		catch (SQLException ex) {
			System.out.println("An error occurred in insertRecords. Table/column name is invalid");
			ex.printStackTrace();
		}
		return studID;
	}
	
	
	
}
