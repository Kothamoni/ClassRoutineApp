package javaProject;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class Page1 extends JFrame{
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;



		Page1() {
	    	setTitle("CR App");
	 setSize(900, 600);
	 setLayout(null);
	 setLocationRelativeTo(null);
	 setDefaultCloseOperation(EXIT_ON_CLOSE);
	 setVisible(true);    setLayout(new FlowLayout());
	 setContentPane(new JLabel(new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\page 1.jpg"))); 

	 setLayout(new FlowLayout());

	 JLabel L1 = new JLabel(" Welcome to CR App");
	 L1.setBounds(300, 250, 300, 100);
	 //calls the JLabel to be used
	 Font L = new Font("Arial", Font.BOLD, 30); // Change the font name, style, and size as needed
	 L1.setFont(L);
	 L1.setForeground(Color.BLACK);
	 add(L1); //add the L1 into the application background

	 setSize(600, 600); //sets the size of the background image

	 JButton b=new JButton("Want To Know About CR App? ");
	 b.setBackground(Color.pink);
	 b.setBounds(100,100,400,100);
	 Font b1 = new Font("Arial", Font.BOLD, 18); // Change the font name, style, and size as needed
	 b.setFont(b1);
	 b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	 dispose();
	    	  new Page2();
	    	
	    	  }
	  });
	   add(b);
	  }
	   public static void main(String[] args)  {
	          new Page1();
	  }

}
