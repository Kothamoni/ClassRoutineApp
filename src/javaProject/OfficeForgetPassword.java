package javaProject;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class OfficeForgetPassword {
	 private JFrame frame;

	    public OfficeForgetPassword() {
	        frame = new JFrame("Forget Password App");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(800, 600);
	        frame.setLayout(new FlowLayout());
	        
	        
	         ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Acer\\Pictures\\JAVA Project\\mim4.jpg");
			        
			        // Create a JLabel to display the background image
			        JLabel backgroundLabel = new JLabel(backgroundImage);
			        backgroundLabel.setBounds(0, 0, frame.getWidth()/2, frame.getHeight());

			        // Add the background label to the frame
			        frame.add(backgroundLabel);

	        // Office Login Page
	        JButton forgetPasswordButton = new JButton("Forget Password");
	       forgetPasswordButton.addActionListener(e -> showForgetPasswordPage());

	        frame.add(new JLabel("Are you sure!"));
	        frame.add(forgetPasswordButton);     
	        frame.setVisible(true);
	        
	        
	    }

	    private void showForgetPasswordPage() {
	        frame.getContentPane().removeAll();
	        frame.revalidate();
	        frame.repaint();

	        // Forget Password Page
	        JButton yesButton = new JButton("Yes");
	        JButton noButton = new JButton("No");

	        yesButton.addActionListener(e -> showResetPasswordPage());
	        noButton.addActionListener(e -> showOfficeLoginPage());
	       
	        frame.add(new JLabel("Do you want to reset your password?"));
	        frame.add(yesButton);
	        frame.add(noButton);

	        frame.setVisible(true);
	    }

	    private void showResetPasswordPage() {
	        frame.getContentPane().removeAll();
	        frame.revalidate();
	        frame.repaint();

	        // Reset Password Page
	        JTextField usernameField = new JTextField(15);
	        JPasswordField passwordField = new JPasswordField(15);
	        JButton updateButton = new JButton("Update");

	        updateButton.addActionListener(e -> {
	            // Update the password in the database
	            String username = usernameField.getText();
	            char[] passwordChars = passwordField.getPassword();
	            String password = new String(passwordChars);

	            if (updatePassword(username, password)) {
	                JOptionPane.showMessageDialog(frame, "Password updated successfully");
	                showOfficeLoginPage();
	            } else {
	                JOptionPane.showMessageDialog(frame, "Password update failed");
	            }
	        });

	    
	        frame.add(new JLabel("Reset your password to login again!"));
	        frame.add(new JLabel("                   "));
	        frame.add(new JLabel("Username:"));
	        frame.add(usernameField);
	        frame.add(new JLabel("Password:"));
	        frame.add(passwordField);
	        frame.add(updateButton);

	        frame.setVisible(true);
	    }

	   
	    private void showOfficeLoginPage() {
	        frame.getContentPane().removeAll();
	        frame.revalidate();
	        frame.repaint();

	        // Office Login Page
	        frame.dispose();
	        new SectionOfficerLoginGui();
	       
	    }

	    // Method to update the password in the database
	    private boolean updatePassword(String username, String password) {
	        String url = "jdbc:mysql://localhost:3306/routine";
	        String user = "root";
	        String pass = "14498";

	        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
	            String query = "UPDATE officerinfo SET Password = ? WHERE Name = ?";
	            PreparedStatement preparedStatement = conn.prepareStatement(query);
	            preparedStatement.setString(1, password); // setting password in the first parameter
	            preparedStatement.setString(2, username); // setting username in the second parameter

	            int rowsUpdated = preparedStatement.executeUpdate();
	            return rowsUpdated > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static void main(String[] args) {
	        new OfficeForgetPassword();
	    }
}
