package javaProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // Add this import for ResultSet
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//student
public class SignUpPage {
//student
    JFrame frame;
    JTextField userNamet;
    JPasswordField passwordText;
    JPanel panel;

    SignUpPage() {
        frame = new JFrame("New User Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(767, 424);
        frame.setLayout(null);

        // Create a background image
        ImageIcon backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\signup stu.jpg");

        // Create a JLabel to display the background image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, frame.getWidth() / 2, frame.getHeight());
        frame.add(backgroundLabel);

        JLabel titleLabel = new JLabel("Class Routine App");
        titleLabel.setBounds(490, 50, 300, 100);
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(Color.BLACK);
        frame.add(titleLabel);
        JLabel tl = new JLabel("SignUp for GIFTS");
       tl.setBounds(450, 100, 300, 100);
        Font tF = new Font("Arial", Font.BOLD, 30);
        tl.setFont(tF);
        tl.setForeground(Color.RED);
        frame.add(tl);
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(400, 200, 100, 30);
        frame.add(userLabel);

        userNamet = new JTextField();
        userNamet.setBounds(500, 200, 225, 30);
        frame.add(userNamet);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(400, 250, 100, 30);
        frame.add(passLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(500, 250, 225, 30);
        frame.add(passwordText);
        
        JLabel id = new JLabel("Id:");
        id.setBounds(400, 300, 100, 30);
        frame.add(id);

        
        JTextField idt= new JTextField();
        idt.setBounds(500,300,100,30);
        frame.add(idt);
        
        
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(600, 300, 100, 30);
        signUpButton.setBackground(Color.white);
        frame.add(signUpButton);
        
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userNamet.getText();
                String password = new String(passwordText.getPassword());
                String id = idt.getText();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");

                    // Check if the ID exists in both studentofsecA and studentofsecB tables
                    String idCheckQuery = "SELECT Id FROM studentofA WHERE Id = ? UNION SELECT Id FROM studentofB WHERE Id = ?";
                    PreparedStatement idCheckStatement = connection.prepareStatement(idCheckQuery);
                    idCheckStatement.setString(1, id);
                    idCheckStatement.setString(2, id);
                    ResultSet idCheckResult = idCheckStatement.executeQuery();

                    if (idCheckResult.next()) {
                        // ID already exists in one of the tables, show a popup message
                        JOptionPane.showMessageDialog(frame, "ID already exists. Please provide a new ID.");
                    } else {
                        // Determine whether the ID is even or odd
                        int idInt = Integer.parseInt(id);
                        String insertStudentQuery;

                        if (idInt % 2 == 0) {
                            // Insert into studentofsecA for even IDs
                            insertStudentQuery = "INSERT INTO studentofA(Id, UserName, Password) VALUES (?, ?, ?)";
                        } else {
                            // Insert into studentofsecB for odd IDs
                            insertStudentQuery = "INSERT INTO studentofB(Id, UserName, Password) VALUES (?, ?, ?)";
                        }

                        PreparedStatement studentStatement = connection.prepareStatement(insertStudentQuery);
                        studentStatement.setString(1, id);
                        studentStatement.setString(2, user);
                        studentStatement.setString(3, password);

                        int rowsInserted = studentStatement.executeUpdate();

                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(frame, "Sign Up Successful");
                            new StudentLoginPage();
                            frame.setVisible(false);
                        }
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
new SignUpPage();
}
}
