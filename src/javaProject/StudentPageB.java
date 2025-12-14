package javaProject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class StudentPageB extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
    JTable tblData;
     DefaultTableModel tableModel;
     JLabel l;

	

    public StudentPageB() {
        setTitle("Section B");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 589, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        l=new JLabel("Routine of Section B");
        l.setBounds(220,20,150,10);
        contentPane.add(l);
        

        JTable tblData = new JTable();
        JScrollPane sp = new JScrollPane(tblData);
        sp.setBounds(268, 52, 283, 209);
        contentPane.add(sp);

        tableModel = new DefaultTableModel();
        tblData.setModel(tableModel);
        
    
        
       

        JButton btnShow = new JButton("Check Routine");
        btnShow.setBounds(56, 60, 128, 35);
        contentPane.add(btnShow);
        setVisible(true);

        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");
                    String query = "SELECT * FROM routineb";
                    PreparedStatement statement = connection.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();

                    // Create a DefaultTableModel and get its column names from the ResultSetMetaData
                    DefaultTableModel model = buildTableModel(rs);

                    // Set the model for your table
                    tblData.setModel(model);

                    statement.close();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to load routine from the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnReset.setBounds(56, 126, 128, 35);
        contentPane.add(btnReset);

        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 DefaultTableModel tableModel = (DefaultTableModel) tblData.getModel();
                 int rowCount = tableModel.getRowCount();
                 for (int i = rowCount - 1; i >= 0; i--) {
                     tableModel.removeRow(i);
                 }
            }
        });
        JButton attendance = new JButton("See Attendance");
        attendance.setBounds(56, 200, 130, 35);
        contentPane.add(attendance);
        setVisible(true);
        attendance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				new AttendanceofSectionB();
				
			}
        	
        });
        
    }
   
  

    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        // Get the number of columns
        int columnCount = metaData.getColumnCount();
        // Create a DefaultTableModel with column names
        DefaultTableModel model = new DefaultTableModel();
        for (int column = 1; column <= columnCount; column++) {
            model.addColumn(metaData.getColumnName(column));
        }
        // Add data to the model
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int column = 1; column <= columnCount; column++) {
                row[column - 1] = rs.getObject(column);
            }
            model.addRow(row);
        }
        return model;
        
    }
    
  public static void main(String[] args) {
	  new StudentPageB();
  }
    
    

}
