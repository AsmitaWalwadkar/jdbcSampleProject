package jdbcStatementInterface;

import java.sql.*;

public class jdbcPrepareStatement {

	public static void main(String[] args) {
		try {
			// create a mysql database connection
			String myUrl = "jdbc:mysql://localhost/collageManagementSystemDB";
			Connection myconnection = DriverManager.getConnection(myUrl, "root", "123");
			System.out.println("Connected to database");
			// insert record
			// insertRecords( myconnection);
			//for updating record
			updateRecords(myconnection);
			//for deleting records
			deleteRecords(myconnection);
			// for displaying records
			displayRecords(myconnection);
			
			myconnection.close();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}

	}

	// For inserting the record
	public static void insertRecords(Connection myconnection) {
		try {
			PreparedStatement stmt = myconnection.prepareStatement("insert into tbl_attendence values(?,?,?,?)");
			stmt.setInt(1, 1);// 1 specifies the first parameter in the query
			stmt.setInt(2, 1);
			stmt.setInt(3, 4);
			stmt.setInt(4, 70);

			int i = stmt.executeUpdate();
			System.out.println(i + " records inserted");
		} catch (Exception exception) {
			System.out.println("An error occurred in inserting a record.");
			exception.printStackTrace();
		}

	}

	// For updating the record
	public static void updateRecords(Connection myconnection) {
		try {
			PreparedStatement stmt = myconnection
					.prepareStatement("update tbl_Student set Student_Name=? where Student_ID=?");
			stmt.setString(1, "Anamika");// 1 specifies the first parameter in the query i.e. name
			stmt.setInt(2, 10);

			int i = stmt.executeUpdate();
			System.out.println(i + " records updated");
		} catch (Exception exception) {
			System.out.println("An error occurred in updating.");
			exception.printStackTrace();
		}

	}

	// For Displaying  the record
			public static void displayRecords(Connection myconnection) {
				try {
					PreparedStatement stmt=myconnection.prepareStatement("select * from tbl_Student");  
					ResultSet rs=stmt.executeQuery();  
					System.out.println("StudID" +"\t "+"Rollno" +" \t"+ "Name" +" \t\t"+ "Address");  
					while(rs.next()){  
					System.out.println(rs.getInt(1)+"\t "+rs.getInt(2)+" \t\t"+rs.getString(3)+"\t \t"+rs.getString(4));  
					}  
				} catch (Exception exception) {
					System.out.println("An error occurred in displaying the student records.");
					exception.printStackTrace();
				}

			}
			
			
	// For deleting the record
		public static void deleteRecords(Connection myconnection) {
			try {
				PreparedStatement stmt=myconnection.prepareStatement("delete from tbl_Student where Student_ID=?");  
				stmt.setInt(1,12);  
				  
				int i=stmt.executeUpdate();  
				System.out.println(i+" records deleted");  
			} catch (Exception exception) {
				System.out.println("An error occurred in deleting.");
				exception.printStackTrace();
			}

		}
}
