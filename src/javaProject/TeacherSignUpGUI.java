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
import javax.swing.JTextField;
//teacher
public class TeacherSignUpGUI extends JFrame{
	private static final long serialVersionUID = 1L;
    private JLabel idLabel, nameLabel, passwordLabel;
    private JTextField idTextField, nameTextField, passwordTextField;
    private JButton signUpButton;

    public TeacherSignUpGUI() {
        setTitle("Teacher SignUp");
        setSize(400, 300);
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
                Image backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\signup.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setLayout(null);
        setContentPane(panel);

        JLabel label = new JLabel("Teacher SignUp");
        label.setBounds(150, 30, 100, 30);
        panel.add(label);

        idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 80, 50, 30);
        panel.add(idLabel);

        idTextField = new JTextField();
        idTextField.setBounds(120, 80, 200, 30);
        panel.add(idTextField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 120, 50, 30);
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(120, 120, 200, 30);
        panel.add(nameTextField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 160, 70, 30);
        panel.add(passwordLabel);

        passwordTextField = new JTextField();
        passwordTextField.setBounds(120, 160, 200, 30);
        panel.add(passwordTextField);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(50, 220, 180, 30);
        signUpButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        signUpButton.setBackground(new Color(135, 206, 250));
        panel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root",
                            "14498");
                    String query = "INSERT INTO teacherinfo (id, name, password) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, idTextField.getText());
                    preparedStatement.setString(2, nameTextField.getText());
                    preparedStatement.setString(3, passwordTextField.getText());
                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(TeacherSignUpGUI.this, "SignUp Successful");
                        new Page2();
                    } else {
                        JOptionPane.showMessageDialog(TeacherSignUpGUI.this, "SignUp Unsuccessful");
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
        new TeacherSignUpGUI();
    }
}
