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
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class StudentLoginPage {
    StudentLoginPage(){

			 JFrame frame = new JFrame("Dear Student please LOGIN");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(1200, 630);
		        
		        // Create a background image
		        ImageIcon backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\page 2.jpg");
		        
		        // Create a JLabel to display the background image
		        JLabel backgroundLabel = new JLabel(backgroundImage);
		        backgroundLabel.setBounds(0, 0, frame.getWidth()/2, frame.getHeight());

		        // Add the background label to the frame
		        frame.add(backgroundLabel);

		        // Create a panel to contain the username, password, and enter button
		        JPanel panel = new JPanel();
		        panel.setLayout(null);

		        // Create a label for "Class Routine App"
		        JLabel titleLabel = new JLabel("Class Routine App");
		        titleLabel.setBounds(850, 50, 300, 100);
		        Font titleFont = new Font("Arial", Font.BOLD, 20);
		        titleLabel.setFont(titleFont);
		        titleLabel.setForeground(Color.BLACK);
		        panel.add(titleLabel);

		        // Create a username label
		        JLabel usernameLabel = new JLabel("Username:");
		        usernameLabel.setBounds(800, 200, 200, 50);
		        panel.add(usernameLabel);

		        // Create a username text field
		        JTextField usernameTextField = new JTextField();
		        usernameTextField.setBounds(900, 200, 200, 50);
		        panel.add(usernameTextField);

		        // Create a password label
		        JLabel passwordLabel = new JLabel("Password:");
		        passwordLabel.setBounds(800, 300, 200, 50);
		        panel.add(passwordLabel);

		        // Create a password text field
		        JPasswordField passwordTextField = new JPasswordField();
		        passwordTextField.setBounds(900, 300, 200, 50);
		        panel.add(passwordTextField);

		        // Create a login button
		        JButton loginButton = new JButton("Login");
		        loginButton.setBounds(900, 400, 100, 50);
		        loginButton.setBackground(new java.awt.Color(255, 204, 51));
		        loginButton.setForeground(java.awt.Color.black);
		        panel.add(loginButton);
		       
		     // Create a sign-up button
		    	JButton signUpButton = new JButton("Sign Up");
		    	signUpButton.setBounds(800, 500, 100, 50);
		    	signUpButton.setBackground(new java.awt.Color(255, 204, 51));
		    	panel.add(signUpButton);

		    	// Create a forgot password button
		    	JButton forgotPasswordButton = new JButton("Forgot Password");
		    	forgotPasswordButton.setBounds(950, 500, 150, 50);
		    	forgotPasswordButton.setBackground(new java.awt.Color(255, 204, 51));
		    	panel.add(forgotPasswordButton);

		    	// Add the panel to the frame
		    	frame.add(panel);

		    	// Add action listeners for the buttons
		    	loginButton.addActionListener(new ActionListener() {
		    		
		    		
		    		    public void actionPerformed(ActionEvent e) {
		    		        String user = usernameTextField.getText();
		    		        String password = new String(passwordTextField.getPassword());

		    		        try {
		    		            // Connect to the MySQL database
		    		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");

		    		            // Prepare a SQL statement to check the username and password in both tables
		    		            String query = "SELECT * FROM studentofA WHERE UserName = ? AND Password = ? UNION SELECT * FROM studentofB WHERE UserName = ? AND Password = ?";
		    		            PreparedStatement statement = connection.prepareStatement(query);
		    		            statement.setString(1, user);
		    		            statement.setString(2, password);
		    		            statement.setString(3, user);
		    		            statement.setString(4, password);

		    		            // Execute the SQL statement and get the results
		    		            ResultSet resultSet = statement.executeQuery();

		    		            // If the username and password are correct in either table, login the user
		    		            if (resultSet.next()) {
		    		                JOptionPane.showMessageDialog(panel, "Login Successful");
		    		                new CheckId();
		    		                frame.setVisible(false);
		    		            } else {
		    		                JOptionPane.showMessageDialog(panel, "Invalid username or password");
		    		            }

		    		            // Close the connection to the database
		    		            connection.close();
		    		        } catch (SQLException ex) {
		    		            ex.printStackTrace();
		    		        }
		    		    }
		    		});

		    	
		    	   
		    		
		    		

		    	signUpButton.addActionListener(new ActionListener() {
		    	    public void actionPerformed(ActionEvent e) {
		    	        new SignUpPage();
		    	        frame.setVisible(false);
		    	    }
		    	});

		    	forgotPasswordButton.addActionListener(new ActionListener() {
		    	    public void actionPerformed(ActionEvent e) {
		    	        new ForgetPassword();
		    	        frame.setVisible(false);
		    	    }
		    	});

		        
		    frame.setVisible(true);
	    }
	    	
	         public static void main(String[] args) {
		       new StudentLoginPage();

		}

}
