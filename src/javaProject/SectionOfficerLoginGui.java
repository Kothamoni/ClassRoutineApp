package javaProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SectionOfficerLoginGui extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   private JTextField usernameField;
	    private JPasswordField passwordField;

	    public SectionOfficerLoginGui() {
	        setTitle("Login");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(1800, 700);

	        // Create a JPanel with a background image
	        JPanel panel = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                ImageIcon backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\bup builidng offcw.jpg");
	                Image img = backgroundImage.getImage();
	                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	            }
	        };

	        panel.setLayout(null);

	        JLabel usernameLabel = new JLabel("Username:");
	        usernameLabel.setBounds(800, 200, 150, 50);
	        usernameLabel.setForeground(new Color(0, 0, 153));
	        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));

	        usernameField = new JTextField();
	        usernameField.setBounds(900, 200, 150, 50);
	        JLabel passwordLabel = new JLabel("Password:");
	        passwordLabel.setForeground(new Color(0, 0, 153));
	        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
	        passwordLabel.setBounds(800, 300, 100, 50);
	        passwordField = new JPasswordField();
	        passwordField.setBounds(900, 300, 150, 50);

	        JButton loginButton = new JButton("Login");
	        loginButton.setBounds(920, 400, 150, 50);
	        JButton signupButton = new JButton("Sign Up");
	        signupButton.setBounds(800, 500, 150, 50);
	        JButton forgotPasswordButton = new JButton("Forgot Password");
	        forgotPasswordButton.setBounds(1000, 500, 150, 50);

	        loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String user = usernameField.getText();
	                String password = new String(passwordField.getPassword());

	                try {
	                    // Connect to the MySQL database
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");

	                    // Prepare a SQL statement to check the username and password in the table
	                    String query = "SELECT Name, Password FROM officerinfo WHERE Name = ? AND Password = ?";
	                    PreparedStatement statement = connection.prepareStatement(query);
	                    statement.setString(1, user);
	                    statement.setString(2, password);

	                    // Execute the SQL statement and get the results
	                    ResultSet resultSet = statement.executeQuery();

	                    // If the username and password are correct, open the next GUI
	                    if (resultSet.next()) {
	                        JOptionPane.showMessageDialog(panel, "Login Successful");
	                        new SectionOfficerHome();
	                        dispose();
	                        
	                        
	                    } else {
	                        JOptionPane.showMessageDialog(panel, "Invalid username or password");
	                        new SectionOfficerLoginGui();
	                    }

	                    // Close the connection to the database
	                    connection.close();
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        signupButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	                new OfficeSignUpPage();
	            }
	        });

	        forgotPasswordButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	                new OfficeForgetPassword();
	            }
	        });

	        panel.add(usernameLabel);
	        panel.add(usernameField);
	        panel.add(passwordLabel);
	        panel.add(passwordField);
	        panel.add(loginButton);
	        panel.add(signupButton);
	        panel.add(forgotPasswordButton);

	        add(panel);
	        setVisible(true);
	    }


		    	
		         public static void main(String[] args) {
			       new SectionOfficerLoginGui();

}
}