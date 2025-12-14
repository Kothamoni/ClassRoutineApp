package javaProject;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//student
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//student
public class CheckId {
	private JFrame frame;
	  private JTextField idField;

	  public CheckId() {
	    frame = new JFrame("Student Section Checker");
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400, 400);
	    frame.setLayout(new FlowLayout());
	     
	    JLabel label=new JLabel();
	    Container contentPane = frame.getContentPane();
	    contentPane.setBackground(Color.MAGENTA);
	    contentPane.add(label);

	    JLabel idLabel = new JLabel("Enter ID:");
	    frame.add(idLabel);

	    idField = new JTextField(10);
	    frame.add(idField);

	    JButton checkButton = new JButton("Check Section");
	    frame.add(checkButton);

	    frame.setVisible(true);

	    checkButton.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	      	frame.dispose();
	        String studentID = idField.getText();

	        // Check if the ID is in the studentofA or studentofB table
	        if (checkIDInDatabase(studentID)) {
	          // Show a popup message that the student is in Section A or Section B accordingly
	          if (isInSectionA(studentID)) {
	            JOptionPane.showMessageDialog(frame, "You are in Section A.");
	            frame.dispose();
	            new StudentPageA();
	          } else {
	            JOptionPane.showMessageDialog(frame, "You are in Section B.");
	            frame.dispose();
	            new StudentPageB();
	          }
	        } else {
	          // Show a popup message that the ID is not found in the database
	          JOptionPane.showMessageDialog(frame, "ID not found.");
	          frame.dispose();
	          new CheckId();
	        }
	      }
	    });
	  }

	  private boolean checkIDInDatabase(String studentID) {
	    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498")) {
	      String query = "SELECT Id FROM studentofA WHERE Id = ? UNION SELECT Id FROM studentofB WHERE Id = ?";
	      PreparedStatement statement = connection.prepareStatement(query);
	      statement.setString(1, studentID);
	      statement.setString(2, studentID);

	      ResultSet resultSet = statement.executeQuery();

	      return resultSet.next(); // Returns true if the ID is found in either table
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return false;
	  }

	  private boolean isInSectionA(String studentID) {
	    // Check if the ID is even
	    return Integer.parseInt(studentID) % 2 == 0;
	  }

	  public static void main(String[] args) {
		  new CheckId();
	  }


	
//student
}

