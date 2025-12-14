package javaProject;
//student
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//student
public class ForgetPassword {
	

        ForgetPassword(){

		        JFrame newframe;
		        JTextField UserNameText; // Change this to JTextField
		        JPasswordField newpasswordText;
		        

		        newframe = new JFrame("Forgotten Password");
		        newframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        newframe.setSize(510, 578);
		        newframe.setLayout(null);

		        // Create a background image
		        ImageIcon backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\forgetpass stu.jpg");

		        // Create a JLabel to display the background image
		        JLabel backgroundLabel = new JLabel(backgroundImage);
		        backgroundLabel.setBounds(0, 0, 519, newframe.getHeight());
		        newframe.add(backgroundLabel);

		        JLabel titleLabel = new JLabel("Class Routine App");
		        titleLabel.setBounds(700, 50, 300, 100);
		        Font titleFont = new Font("Arial", Font.BOLD, 30);
		        titleLabel.setFont(titleFont);
		        titleLabel.setForeground(Color.BLUE);
		        newframe.add(titleLabel);

		        JLabel tl = new JLabel("Reset Your Password to Login Again");
		        tl.setBounds(550, 150, 800, 100);
		        Font tF = new Font("Arial", Font.BOLD, 30);
		        tl.setFont(tF);
		        tl.setForeground(Color.PINK);
		        newframe.add(tl);

		        JLabel username = new JLabel("Username:");
		        username.setBounds(700, 300, 200, 50);
		        username.setForeground(Color.BLACK);// Adjust the position
		        newframe.add(username);

		        UserNameText = new JTextField(); // Change to JTextField
		        UserNameText.setBounds(800, 300, 200, 50); // Adjust the position
		        newframe.add(UserNameText);

		        JLabel Newpassword = new JLabel("Password:");
		        Newpassword.setBounds(700, 350, 200, 50);
		        newframe.add(Newpassword);

		        newpasswordText = new JPasswordField();
		        newpasswordText.setBounds(800,350,200,50);
		        newframe.add(newpasswordText);

		        JButton updatePasswordButton = new JButton("Update your BackDated Password");
		        updatePasswordButton.setBounds(720, 500, 300, 50); // Adjust the position and size
		        updatePasswordButton.setBackground(Color.BLACK);
		        updatePasswordButton.setForeground(Color.WHITE);
		        newframe.add(updatePasswordButton);

		        updatePasswordButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                String user = UserNameText.getText();
		                String newPassword = new String(newpasswordText.getPassword());
		                String tableName = ""; // To store the table name

		                try {
		                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");

		                    // Check if the username exists in studentofA table
		                    String checkQueryA = "SELECT UserName FROM studentofA WHERE UserName = ?";
		                    PreparedStatement checkStatementA = connection.prepareStatement(checkQueryA);
		                    checkStatementA.setString(1, user);
		                    ResultSet checkResultA = checkStatementA.executeQuery();

		                    // Check if the username exists in studentofB table
		                    String checkQueryB = "SELECT UserName FROM studentofB WHERE UserName = ?";
		                    PreparedStatement checkStatementB = connection.prepareStatement(checkQueryB);
		                    checkStatementB.setString(1, user);
		                    ResultSet checkResultB = checkStatementB.executeQuery();

		                    if (checkResultA.next()) {
		                        tableName = "studentofA"; // Username exists in studentofA
		                    } else if (checkResultB.next()) {
		                        tableName = "studentofB"; // Username exists in studentofB
		                    } else {
		                        JOptionPane.showMessageDialog(newframe, "Username not found");
		                        return; // Exit the method
		                    }

		                    // Update the password in the appropriate table
		                    String updateQuery = "UPDATE " + tableName + " SET Password = ? WHERE UserName = ?";
		                    PreparedStatement statement = connection.prepareStatement(updateQuery);
		                    statement.setString(1, newPassword);
		                    statement.setString(2, user);

		                    int rowsUpdated = statement.executeUpdate();
		                    if (rowsUpdated > 0) {
		                        JOptionPane.showMessageDialog(newframe, "Password Updated Successfully");
		                        newframe.dispose();
		                        new StudentLoginPage();
		                       // newframe.setVisible(false);
		                    } else {
		                        JOptionPane.showMessageDialog(newframe, "Failed to update password");
		                    }

		                    connection.close();
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                }
		            }
		        });


		        newframe.setVisible(true);
		    }

		    public static void main(String[] args) {
		        new ForgetPassword();
		    }

}
