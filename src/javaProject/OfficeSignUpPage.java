package javaProject;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class OfficeSignUpPage {
	JFrame frame;
    JTextField userNamet;
    JPasswordField passwordText;
         OfficeSignUpPage(){
            	 


            	         frame = new JFrame("User Sign Up");
            	         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	         frame.setSize(500, 300);
            	         frame.setLayout(null);

            	         JLabel titleLabel = new JLabel("Sign Up for SecOff");
            	         titleLabel.setBounds(180, 10, 150, 30);
            	         Font titleFont = new Font("Arial", Font.BOLD, 16);
            	         titleLabel.setFont(titleFont);
            	         frame.add(titleLabel);

            	         JLabel userLabel = new JLabel("Username:");
            	         userLabel.setBounds(50, 60, 100, 30);
            	         frame.add(userLabel);

            	         userNamet = new JTextField();
            	         userNamet.setBounds(150, 60, 200, 30);
            	         frame.add(userNamet);

            	         JLabel passLabel = new JLabel("Password:");
            	         passLabel.setBounds(50, 100, 100, 30);
            	         frame.add(passLabel);

            	         passwordText = new JPasswordField();
            	         passwordText.setBounds(150, 100, 200, 30);
            	         frame.add(passwordText);

            	         JButton signUpButton = new JButton("Sign Up");
            	         signUpButton.setBounds(200, 150, 100, 30);
            	         frame.add(signUpButton);

            	         signUpButton.addActionListener(new ActionListener() {
            	             @Override
            	             public void actionPerformed(ActionEvent e) {
            	            	 
            	                 String user = userNamet.getText();
            	                 String password = new String(passwordText.getPassword());

            	                 try {
            	                     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");

            	                     String insertQuery = "INSERT INTO officerinfo(Name, Password) VALUES (?, ?)";
            	                     PreparedStatement statement = connection.prepareStatement(insertQuery);
            	                     statement.setString(1, user);
            	                     statement.setString(2, password);

            	                     int rowsInserted = statement.executeUpdate();

            	                     if (rowsInserted > 0) {
            	                         JOptionPane.showMessageDialog(frame, "Sign Up Successful");
            	                         frame.dispose();
            	                         new SectionOfficerLoginGui();
            	                     } else {
            	                         JOptionPane.showMessageDialog(frame, "Error while signing up");
            	                         frame.dispose();
            	                         new OfficeSignUpPage();
            	                     }

            	                     connection.close();
            	                 } catch (Exception ex) {
            	                     ex.printStackTrace();
            	                 }
            	             }
            	         });

            	         frame.setVisible(true);
            	     }

              
               
	    public static void main(String[] args) {
	        new OfficeSignUpPage();
	    }
}
