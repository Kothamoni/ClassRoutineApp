package javaProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class SORoutineB extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel tableModel;
    private JButton updateButton;
    private Connection connection;

    public SORoutineB() {
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

        // Add components to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(updateButton,BorderLayout.EAST);

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
            String sql = "SELECT * FROM routineb";
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

                String updateSQL = "UPDATE routineb SET TIME1 = ?, TIME2 = ?, Break = ?, TIME3 = ?, TIME4 = ? WHERE day = ?";
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
    public static void main(String[] args) {
    	new SORoutineB();
    }

}
