package javaProject;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class SectionOfficerHome extends JFrame {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	    private JMenu studentSecAMenu;
	    private JMenu studentSecBMenu;
	    private JMenu teacherMenu;
	    private JMenuItem routineItemSecA;
	    private JMenuItem routineItemSecB;
	    private JMenuItem routineItemTeacher;

	    public SectionOfficerHome() {
	        super("Section Officer Home Page");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(600, 600);
	       
	        setContentPane(new JLabel(new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\bup.png")));

	        // Create the menubar
	        menuBar = new JMenuBar();
	        studentSecAMenu = new JMenu("StudentSecA");
	        studentSecBMenu = new JMenu("StudentSecB");
	        teacherMenu = new JMenu("Teacher");

	        // Create the "Routine" menu items for each menu
	        routineItemSecA = new JMenuItem("Routine");
	        routineItemSecB = new JMenuItem("Routine");
	        routineItemTeacher = new JMenuItem("Routine");

	        routineItemSecA.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(SectionOfficerHome.this, "Routine selected for StudentSecA!");
	                new SORoutineA();
	                
	            }
	        });

	        routineItemSecB.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(SectionOfficerHome.this, "Routine selected for StudentSecB!");
	                new SORoutineB();
	            }
	        });

	        routineItemTeacher.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(SectionOfficerHome.this, "Routine selected for Teacher!");
	                new TeacherRoutine();
	            }
	        });

	        // Add the "Routine" menu items to their respective menus
	        studentSecAMenu.add(routineItemSecA);
	        studentSecBMenu.add(routineItemSecB);
	        teacherMenu.add(routineItemTeacher);

	        // Add the menus to the menubar
	        menuBar.add(studentSecAMenu);
	        menuBar.add(studentSecBMenu);
	        menuBar.add(teacherMenu);

	        // Set the orientation of the menu bar to horizontal (default)
	        // No need to set it as it's horizontal by default
	        // menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));

	        // Set the menubar to the frame
	        setJMenuBar(menuBar);
	        setVisible(true);
	    }


	public static void main(String[] args) {
		new SectionOfficerHome();

	}

}
