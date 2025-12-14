package javaProject;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//section officer
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TeacherRoutine extends JFrame{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//office teacher
	/**
	 * 
	 */
	 private JTable table;
	    private DefaultTableModel tableModel;
	    private JButton updateButton;
	    private JButton resetButton; // Added reset button
	    private Connection connection;

	    public TeacherRoutine() {
	        // Set up the frame
	        setTitle("Database Table Viewer");
	        setSize(800, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);

	        // Create the table model
	        tableModel = new DefaultTableModel();
	        tableModel.addColumn("day");
	        tableModel.addColumn("TIME1");
	        tableModel.addColumn("TIME2");
	        tableModel.addColumn("Break");
	        tableModel.addColumn("TIME3");
	        tableModel.addColumn("TIME4");

	        // Create the table
	        table = new JTable(tableModel);

	        JScrollPane scrollPane = new JScrollPane(table);

	        // Create the update button
	        updateButton = new JButton("Update");
	        updateButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                updateData();
	            }
	        });

	        // Create the reset button
	        resetButton = new JButton("Reset");
	        resetButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                resetTable();
	            }
	        });

	        // Add components to the frame
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(updateButton);
	        buttonPanel.add(resetButton);

	        getContentPane().setLayout(new BorderLayout());
	        getContentPane().add(scrollPane, BorderLayout.CENTER);
	        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	        // Initialize the database connection
	        try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");
	            fetchDataFromDatabase();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method to fetch data from the database and populate the table
	    private void fetchDataFromDatabase() {
	        try {
	            setVisible(true);
	            String sql = "SELECT * FROM routine_all";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            ResultSet resultSet = statement.executeQuery();

	            tableModel.setRowCount(0); // Clear existing data

	            while (resultSet.next()) {
	                String day = resultSet.getString("day");
	                String time1 = resultSet.getString("TIME1");
	                String time2 = resultSet.getString("TIME2");
	                String breakTime = resultSet.getString("Break");
	                String time3 = resultSet.getString("TIME3");
	                String time4 = resultSet.getString("TIME4");

	                tableModel.addRow(new Object[]{day, time1, time2, breakTime, time3, time4});
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method to update data in the database
	    private void updateData() {
	        try {
	            for (int row = 0; row < tableModel.getRowCount(); row++) {
	                String day = (String) tableModel.getValueAt(row, 0);
	                String time1 = (String) tableModel.getValueAt(row, 1);
	                String time2 = (String) tableModel.getValueAt(row, 2);
	                String breakTime = (String) tableModel.getValueAt(row, 3);
	                String time3 = (String) tableModel.getValueAt(row, 4);
	                String time4 = (String) tableModel.getValueAt(row, 5);

	                String updateSQL = "UPDATE routine_all SET TIME1 = ?, TIME2 = ?, Break = ?, TIME3 = ?, TIME4 = ? WHERE day = ?";
	                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

	                preparedStatement.setString(1, time1);
	                preparedStatement.setString(2, time2);
	                preparedStatement.setString(3, breakTime);
	                preparedStatement.setString(4, time3);
	                preparedStatement.setString(5, time4);
	                preparedStatement.setString(6, day);

	                // Execute the update statement
	                preparedStatement.executeUpdate();

	                // Close the PreparedStatement
	                preparedStatement.close();
	            }

	            // Display a success message
	            JOptionPane.showMessageDialog(this, "Data updated successfully");
	            fetchDataFromDatabase(); // Refresh the table after the update
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method to reset the table
	    private void resetTable() {
	        tableModel.setRowCount(0); // Clear the table
	        JOptionPane.showMessageDialog(this, "Table reset successfully");
	    }

    
	public static void main(String[] args) {
		new TeacherRoutine();
	}

}
