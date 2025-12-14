package javaProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
public class StudentPageA {
	  
	   StudentPageA() {
	    	 JFrame frame = new JFrame("BUP");
	    	 frame.setLayout(null);
	    	 frame.setSize(800, 800);
	    	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	
	    	 ImageIcon backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\Stu page.jpg");
	    	 JLabel backgroundLabel = new JLabel(backgroundImage);
	    	 backgroundLabel.setBounds(0, 0, 800, 800);
	    	 frame.add(backgroundLabel);

	    	
	    	JLabel label = new JLabel("Welcome to SectionA, BICE-2022! ");
	   label.setBounds(850, 300, 600, 100);
	      Font b3 = new Font("Arial", Font.BOLD, 25);
	   label.setFont(b3);
	     frame.add(label);

	    	 // Set the background label's visibility to true
	    	 

	    	 // Add the background label to the frame
	    	 frame.add(backgroundLabel);

	    	 

	// Create a menu bar
	JMenuBar menuBar = new JMenuBar();
	frame.setJMenuBar(menuBar);


	JMenu routineMenu = new JMenu("Routine");


	    JMenu sectionAMenu = new JMenu("Section A");
	  

	    // Add the submenus to the "Routine" menu
	    routineMenu.add(sectionAMenu);
	    

	    // Create menu items for "Check Routine" and "Download Routine PDF" under each section
	    JMenuItem sectionACheckRoutineMenuItem = new JMenuItem("Check Routine");
	    JMenuItem sectionADownloadRoutinePDFMenuItem = new JMenuItem("Download Routine PDF");

	    

	    // Add the menu items to the respective section submenus
	    sectionAMenu.add(sectionACheckRoutineMenuItem);
	    sectionAMenu.add(sectionADownloadRoutinePDFMenuItem);

	  

	    // Create a menu for "See Attendance"
	    JMenu seeAttendanceMenu = new JMenu("See Attendance of Section A");

	    // Create menu items for "Check Attendance" and add them to the "See Attendance" menu
	    JMenuItem checkAttendanceMenuItem = new JMenuItem("Check Attendance");
	    seeAttendanceMenu.add(checkAttendanceMenuItem);

	    // Add the "Routine" and "See Attendance" menus to the menu bar
	    menuBar.add(routineMenu);
	    menuBar.add(seeAttendanceMenu);

	    // Add action listeners for "Check Routine" and "Download Routine PDF" under each section
	    sectionACheckRoutineMenuItem.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  try {
	 			 Class.forName("com.mysql.cj.jdbc.Driver");
	 			 Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");
	 			 String query = "SELECT * FROM routinea";
		            PreparedStatement statement = connection.prepareStatement(query);
		             ResultSet rs = statement.executeQuery();
		           
		         

	               DefaultTableModel model = new DefaultTableModel();

	                     // Create a table and set the model
	                     JTable tblData = new JTable(model);
	                    
	                     // Get metadata for column names
	                     ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
	                     int cols = rsmd.getColumnCount();

	                     // Add column names to the table model
	                     for (int i = 1; i <= cols; i++) {
	                         model.addColumn(rsmd.getColumnName(i));
	                     }

	                     // Populate the table with data from the database
	                     while (rs.next()) {
	                         Object[] rowData = new Object[cols];
	                         for (int i = 1; i <= cols; i++) {
	                             rowData[i - 1] = rs.getObject(i);
	                         }
	                         model.addRow(rowData);
	                     }

	                     statement.close();
	                     connection.close();

	                     // Create a new frame for displaying the table
	                     JFrame tableFrame = new JFrame("Routine of Section A");
	                     tableFrame.setSize(500, 500);

	                     // Create a scroll pane for the table
	                     JScrollPane scrollPane = new JScrollPane(tblData);
	                     tableFrame.add(scrollPane);

	                     // Display the new frame
	                     tableFrame.setVisible(true);
	                 } catch (SQLException | ClassNotFoundException ex) {
	                     ex.printStackTrace();
	                     JOptionPane.showMessageDialog(frame, "Failed to load routine from the database.", "Error", JOptionPane.ERROR_MESSAGE);
	                 }
	             }
	         });
	        
	      

	    sectionADownloadRoutinePDFMenuItem.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        
	        new PDFGenerator();
	      }
	    });

	   
	   
	        checkAttendanceMenuItem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                
	               new AttndenaceofSectionA();
	            }
	        });

	        frame.setVisible(true);
	    }

	   public static void main(String[] args) {
		   new StudentPageA();
	   }


}
