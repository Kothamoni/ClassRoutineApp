package javaProject;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TeacherAttGui extends JFrame {
	 private static final long serialVersionUID = 1L;
	    private JLabel dateLabel, sectionLabel;
	    private JComboBox<String> dateDropdown, sectionDropdown;
	    private JLabel backgroundLabel;

	    public TeacherAttGui() {
	        setTitle("Main Dropdown GUI");
	        setSize(800, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Set up the background image
	        ImageIcon backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\bup.png"); // Replace with the path to your image
	        backgroundLabel = new JLabel(new ImageIcon(backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
	        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
	        setContentPane(backgroundLabel);

	        setLayout(new BorderLayout());

	        JPanel panel = new JPanel();
	        panel.setOpaque(false);
	        panel.setLayout(null);

	        dateLabel = new JLabel("Date:");
	        dateLabel.setBounds(50, 50, 150, 30);
	        panel.add(dateLabel);

	        String[] dates = {"30 Oct 2023"};
	        dateDropdown = new JComboBox<>(dates);
	        dateDropdown.setBounds(200, 50, 150, 30);
	        panel.add(dateDropdown);

	        sectionLabel = new JLabel("Choose  section:");
	        sectionLabel.setBounds(50, 100, 150, 30);
	        panel.add(sectionLabel);

	        String[] sections = {"Section A", "Section B"};
	        sectionDropdown = new JComboBox<>(sections);
	        sectionDropdown.setBounds(200, 100, 150, 30);
	        panel.add(sectionDropdown);

	        sectionDropdown.addActionListener(e -> {
	            String selectedSection = (String) sectionDropdown.getSelectedItem();
	            if (selectedSection.equals("Section A")) {
	                new SectionAattGUI();
	            } else if (selectedSection.equals("Section B")) {
	                new SectionBattGUI();
	            }
	        });

	        add(panel, BorderLayout.CENTER);

	        setVisible(true);
	    }

	  public static void main(String[] args) {
		  new TeacherAttGui();
	  }
}
	
