package javaProject;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class Page2 {
	Page2() {
        JFrame frame = new JFrame("Class Routine App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1200, 630);

        // Create a background image
        ImageIcon backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\page 2.jpg");

        // Create a JLabel to display the background image
        JLabel backgroundLabel = new JLabel(backgroundImage);

        // Set the bounds of the background label to cover half of the frame
        backgroundLabel.setBounds(0, 0, frame.getWidth() / 2, frame.getHeight());

        // Add the background label to the frame
        frame.add(backgroundLabel);

        // Create the other components of the frame
        JLabel titleLabel = new JLabel("Class Routine App");
        Font L = new Font("Arial", Font.BOLD, 20);
        titleLabel.setFont(L);
        titleLabel.setBounds(800, 50, 200, 30);
        frame.add(titleLabel);

        JLabel messageLabel = new JLabel("Tom or Jerry?");
        Font L1 = new Font("Arial", Font.BOLD, 20);
        messageLabel.setBounds(820, 100, 200, 30);
        messageLabel.setFont(L1);
        frame.add(messageLabel);

        ImageIcon studentIcon = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\student icon.jpg");
        JLabel label = new JLabel();
        label.setIcon(studentIcon);
        label.setBounds(650, 300, 250, 150);
        frame.getContentPane().add(label);

        ImageIcon teacherIcon = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\teacher icon.jpg");
        JLabel label1 =  new JLabel();
        label1.setIcon(teacherIcon);
        label1.setBounds(950, 300, 250, 150);
        frame.getContentPane().add(label1);

        JLabel chooseBatchLabel = new JLabel("Please Select Batch:");
        chooseBatchLabel.setBounds(750, 200, 150, 30);
        frame.add(chooseBatchLabel);

        String[] batchOptions = {"Invalid","BICE-2022"};
        JComboBox<String> batchComboBox = new JComboBox<>(batchOptions);
        batchComboBox.setBackground(Color.WHITE);
        batchComboBox.setBounds(900, 200, 100, 30);
        frame.add(batchComboBox);

        JLabel chooseLabel = new JLabel("Please Select!");
        chooseLabel.setBounds(750, 500, 150, 30);

        String[] userTypes = {"Student", "Teacher", "Section_Officer"};
        JComboBox<String> comboBox = new JComboBox<>(userTypes);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(900, 500, 150, 25);

        // Initially hide the "Please Select" ComboBox and label
        chooseLabel.setVisible(false);
        comboBox.setVisible(false);
        
        frame.add(chooseLabel);
        frame.add(comboBox);

        // Add an ItemListener to the batchComboBox
        batchComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ("BICE-2022".equals(batchComboBox.getSelectedItem())) {
                    chooseLabel.setVisible(true);
                    comboBox.setVisible(true);
                    frame.add(chooseLabel);
                    frame.add(comboBox);
                } else {
                    
                }
            }
        });

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedUserType = (String) comboBox.getSelectedItem();
                if (selectedUserType.equals("Student")) {
                    JOptionPane.showMessageDialog(frame, "You've selected Student");
                    new StudentLoginPage();
                    //frame.dispose();
                } else if (selectedUserType.equals("Teacher")) {
                    JOptionPane.showMessageDialog(frame, "You've selected Teacher");
                    new TeacherLoginGui();
                    //frame.dispose();
                } else if (selectedUserType.equals("Section_Officer")) {
                    JOptionPane.showMessageDialog(frame, "You've selected Section_Officer");
                    new SectionOfficerLoginGui();
                  //  frame.dispose();
                }
            }
        });
      
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Page2();
    }
}
