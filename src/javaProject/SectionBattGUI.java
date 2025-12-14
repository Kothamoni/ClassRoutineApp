package javaProject;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SectionBattGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;
	    private JPanel panel;
	    private JButton saveButton;
	    public SectionBattGUI() {
	        try {
	            // Connect to the existing MySQL database
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }

	        setTitle("Attendance System");
	        setSize(800, 500);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        panel = new JPanel();
	        add(panel);
	        placeComponents(panel);

	        setVisible(true);
	     
	    }

	    private void placeComponents(JPanel panel) {
	        panel.setLayout(null);

	        // Create a label
	        JLabel label = new JLabel("UCAM : Please mark if present");
	        label.setBounds(10, 20, 150, 25);
	        panel.add(label);

	        // Fetch students from the database
	        try {
	            Statement stmt = connection.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT id, name FROM secB");
	            int y = 50;

	            // Create checkboxes for each student
	            while (rs.next()) {
	                int studentId = rs.getInt("Id");
	                String studentName = rs.getString("name");

	                JCheckBox checkBox = new JCheckBox("ID: " + studentId + "----------- " + studentName); // Modified label to include the ID
	                checkBox.setBounds(10, y, 200, 25); // Adjust the width to accommodate the ID and name
	                panel.add(checkBox);

	                // Add action listener to mark attendance
	                checkBox.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        try {
	                            String attendanceStatus = checkBox.isSelected() ? "Present" : "Absent";
	                            updateAttendanceInDatabase(studentId, studentName, attendanceStatus);
	                        } catch (SQLException ex) {
	                            ex.printStackTrace();
	                        }
	                    }
	                });

	                y += 30;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // Create a "Save" button
	        saveButton = new JButton("Save");
	        int buttonY = getY() + 50; // Adjust the value according to your layout
	        saveButton.setBounds(250, buttonY, 80, 25);
	        panel.add(saveButton);

	        // Add action listener to the "Save" button
	        saveButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    saveAttendance();
	                    JOptionPane.showMessageDialog(panel, "Attendance Saved Successfully!");
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        // Create a "Reset" button
	        JButton resetButton = new JButton("Reset");
	        int resetButtonY = buttonY + 50; // Adjust the value according to your layout
	        resetButton.setBounds(250, resetButtonY, 80, 25);
	        panel.add(resetButton);

	        // Add action listener to the "Reset" button
	        resetButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    resetAttendance();
	                    JOptionPane.showMessageDialog(panel, "Attendance Reset Successfully!");

	                    // Clear the checkbox selections
	                    clearCheckboxSelections(panel);
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	    }

	   

		private void updateAttendanceInDatabase(int studentId, String studentName, String attendanceStatus) throws SQLException {
	        String sql = "UPDATE secB SET status = ? WHERE id = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setString(1, attendanceStatus);
	        statement.setInt(2, studentId);
	        int rowsUpdated = statement.executeUpdate();
	        System.out.println(rowsUpdated + " rows updated.");
	    }
	    

	    private void saveAttendance() throws SQLException {
	        // Update the attendance for each student
	        Component[] components = panel.getComponents();
	        for (Component component : components) {
	            if (component instanceof JCheckBox) {
	                JCheckBox checkBox = (JCheckBox) component;
	                String[] studentInfo = checkBox.getText().split(", ");
	                if (studentInfo.length == 2) {
	                    int studentId = Integer.parseInt(studentInfo[0].substring(4)); // Extract student ID from the label
	                    String studentName = studentInfo[1];
	                    String attendanceStatus = checkBox.isSelected() ? "Present" : "Absent";
	                    updateAttendanceInDatabase(studentId, studentName, attendanceStatus);
	                }
	            }
	        }
	    }

	    private void resetAttendance() throws SQLException {
	        String sql = "UPDATE secB SET status = ''";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        int rowsUpdated = statement.executeUpdate();
	        System.out.println(rowsUpdated + " rows updated.");
	    }

	    private void clearCheckboxSelections(JPanel panel) {
	        Component[] components = panel.getComponents();
	        for (Component component : components) {
	            if (component instanceof JCheckBox) {
	                JCheckBox checkBox = (JCheckBox) component;
	                checkBox.setSelected(false); // Clear the checkbox selection
	            }
	        }
	    }

	    public static void main(String[] args) {
	      
	    	new SectionBattGUI();
	    }
}
