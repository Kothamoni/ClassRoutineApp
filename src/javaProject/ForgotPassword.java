package javaProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


//teacher

public class ForgotPassword extends JFrame{
	 private static final long serialVersionUID = 1L;
	    private JLabel nameLabel, passwordLabel;
	    private JTextField nameTextField;
	    JPasswordField passwordTextField;
	    private JButton updatePasswordButton;

	    public ForgotPassword() {
	        setTitle("Forgot Password");
	        setSize(800, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        JPanel panel = new JPanel() {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                // Load the background image
	                Image backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\forgotpass.png").getImage();
	                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        panel.setLayout(null);
	        setContentPane(panel);

	        JLabel label = new JLabel("Forgot Password");
	        label.setBounds(150, 30, 150, 30);
	        panel.add(label);

	        nameLabel = new JLabel("Name:");
	        nameLabel.setBounds(50, 80, 50, 30);
	        panel.add(nameLabel);

	        nameTextField = new JTextField();
	        nameTextField.setBounds(120, 80, 200, 30);
	        panel.add(nameTextField);

	        passwordLabel = new JLabel("Password:");
	        passwordLabel.setBounds(50, 120, 70, 30);
	        panel.add(passwordLabel);

	        passwordTextField = new JPasswordField();
	        passwordTextField.setBounds(120, 120, 200, 30);
	        panel.add(passwordTextField);

	        updatePasswordButton = new JButton("Update Password");
	        updatePasswordButton.setBounds(100, 220, 250, 30);
	        updatePasswordButton.setFont(new Font("Times New Roman", Font.PLAIN, 20)); // Change the font size as desired
	        updatePasswordButton.setBackground(Color.GREEN); // Set the background color to red
	        panel.add(updatePasswordButton);

	        updatePasswordButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");
	                    String query = "UPDATE teacherinfo SET password = ? WHERE name = ?";
	                    PreparedStatement preparedStatement = connection.prepareStatement(query);
	                    preparedStatement.setString(1, passwordTextField.getText());
	                    preparedStatement.setString(2, nameTextField.getText());
	                    int rowsUpdated = preparedStatement.executeUpdate();

	                    if (rowsUpdated > 0) {
	                        JOptionPane.showMessageDialog(ForgotPassword.this, "Password Changed");
	                        new TeacherLoginGui();
	                        dispose();
	                    } else {
	                        JOptionPane.showMessageDialog(ForgotPassword.this, "Name Not Found");
	                    }

	                    preparedStatement.close();
	                    connection.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new ForgotPassword();
	    }

}