package javaProject;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;



public class AttendanceofSectionB {
	   private JFrame frame;
	    private DefaultTableModel model;
	    private JTable table;

	    public AttendanceofSectionB() {
	        frame = new JFrame("Show Attendance");
	        frame.setSize(600, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        model = new DefaultTableModel();
	        table = new JTable(model);

	        JScrollPane scrollPane = new JScrollPane(table);
	        frame.add(scrollPane, BorderLayout.CENTER);

	       


	        // Prompt the user to enter the username
	        String userName = JOptionPane.showInputDialog(frame, "Enter the user name:");
	        if (userName != null && !userName.isEmpty()) {
	            // Database connection parameters
	        	
	            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	            String DATABASE_URL = "jdbc:mysql://localhost:3306/routine";
	            String USERNAME = "root";
	            String PASSWORD = "14498";

	            Connection connection = null;
	            try {
	            	
	                Class.forName(JDBC_DRIVER);
	                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

	                // Check if the user exists in the attendanceofB table
	                String checkUserQuery = "SELECT * FROM SecB WHERE Name = ?";
	                PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery);
	                checkUserStatement.setString(1, userName);
	                ResultSet userResultSet = checkUserStatement.executeQuery();
	                

	                if (userResultSet.next()) {
	                    // User exists, fetch their records
	                    String fetchAttendanceQuery = "SELECT * FROM SecB WHERE Name = ?";
	                    PreparedStatement statement = connection.prepareStatement(fetchAttendanceQuery);
	                    statement.setString(1, userName);
	                    ResultSet resultSet = statement.executeQuery();

	                    // Get metadata for column names
	                    ResultSetMetaData rsmd = resultSet.getMetaData();
	                    int cols = rsmd.getColumnCount();

	                    // Add column names to the table model
	                    for (int i = 1; i <= cols; i++) {
	                        model.addColumn(rsmd.getColumnName(i));
	                    }

	                    // Populate the table with data from the database
	                    while (resultSet.next()) {
	                        Object[] rowData = new Object[cols];
	                        for (int i = 1; i <= cols; i++) {
	                            rowData[i - 1] = resultSet.getObject(i);
	                        }
	                        model.addRow(rowData);
	                        
	                    }
	                    
	                } else {
	                	
	                    JOptionPane.showMessageDialog(frame, "User not found in attendance records.", "Error", JOptionPane.ERROR_MESSAGE);
	                    new AttendanceofSectionB();
	                    
	                }

	                // Close the connection to the database
	                connection.close();
	            } catch (ClassNotFoundException | SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(frame, "Failed to load attendance data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
	                new AttendanceofSectionB();
	            }
	        } else {
	           JOptionPane.showMessageDialog(frame, "User name is required.", "Error", JOptionPane.ERROR_MESSAGE);
	           
	           new AttendanceofSectionB();
	        }

	        frame.setVisible(true);
	        table.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if (e.getButton() == MouseEvent.BUTTON1) {
	                    model.setRowCount(0); // Clear the table data on left mouse click
	                }
	            }
	        });
	    }


   public static void main(String[]args) {
	   new AttendanceofSectionB();
   }

}
