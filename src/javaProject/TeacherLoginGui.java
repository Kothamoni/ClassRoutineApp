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
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//teacher

public class TeacherLoginGui extends JFrame {
	 private static final long serialVersionUID = 1L;
	    private JButton loginButton;
	    private JButton signUpButton;
	    private JButton forgetPassButton;
	    private JLabel userLabel, passwordLabel;
	    private JTextField userTextField;
	    private JPasswordField passwordField;

	    public TeacherLoginGui() {
	        setTitle("Teacher Login");
	        setSize(900, 500);
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
	                Image backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\tutor_login_icon.png").getImage();
	                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	            }
	        };

	        panel.setLayout(null);
	        setContentPane(panel);

	        JLabel label = new JLabel("Welcome");
	        label.setBounds(100, 30, 300, 50);
	        label.setFont(new Font("Times New Roman", Font.PLAIN, 46));
	        label.setForeground(Color.BLUE);
	        panel.add(label);

	        userLabel = new JLabel("User:");
	        userLabel.setBounds(50, 80, 50, 30);
	        userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        panel.add(userLabel);

	        userTextField = new JTextField();
	        userTextField.setBounds(120, 80, 200, 30);
	        panel.add(userTextField);

	        passwordLabel = new JLabel("Password:");
	        passwordLabel.setBounds(30, 120, 100, 30);
	        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        panel.add(passwordLabel);

	        passwordField = new JPasswordField();
	        passwordField.setBounds(120, 120, 200, 30);
	        panel.add(passwordField);

	        loginButton = new JButton("Login");
	        loginButton.setBounds(150, 170, 80, 30);
	        loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        loginButton.setBackground(Color.GREEN);
	        panel.add(loginButton);

	        signUpButton = new JButton("Sign Up");
	        signUpButton.setBounds(50, 220, 180, 30);
	        signUpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        signUpButton.setBackground(new Color(135, 206, 250));
	        panel.add(signUpButton);

	        forgetPassButton = new JButton("Forget Pass");
	        forgetPassButton.setBounds(250, 220, 150, 30);
	        forgetPassButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        forgetPassButton.setBackground(Color.RED);
	        panel.add(forgetPassButton);

	        loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root",
	                            "14498");
	                    String query = "SELECT * FROM teacherinfo WHERE name = ? AND password = ?";
	                    PreparedStatement preparedStatement = connection.prepareStatement(query);
	                    preparedStatement.setString(1, userTextField.getText());
	                    preparedStatement.setString(2, new String(passwordField.getPassword()));
	                    ResultSet resultSet = preparedStatement.executeQuery();

	                    if (resultSet.next()) {
	                        JOptionPane.showMessageDialog(TeacherLoginGui.this, "Login Successful");
	                        new RoutineGui();
	                        dispose();
	                    } else {
	                        JOptionPane.showMessageDialog(TeacherLoginGui.this, "Invalid Name or Password");
	                    }

	                    resultSet.close();
	                    preparedStatement.close();
	                    connection.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        signUpButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new TeacherSignUpGUI();
	                dispose();

	            }

	        });
	        forgetPassButton.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new ForgotPassword();
	                dispose();

	            }

	        });

	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new TeacherLoginGui();
	    }
}
