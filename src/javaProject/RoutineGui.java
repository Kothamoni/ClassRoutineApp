package javaProject;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
//afrina mam routine
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

//teacher routine 
public class RoutineGui extends JFrame {
	 private static final long serialVersionUID = 1L;
	    private JLabel welcomeLabel;
	    private JButton showRoutineButton;
	    private JButton takeAttendanceButton; // New button for taking attendance

	    public RoutineGui() {
	        setTitle("Routine GUI");
	        setSize(600, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(null);

	        JPanel panel = new JPanel() {
	            private static final long serialVersionUID = 1L;

	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                // Load the background image
	                Image backgroundImage = new ImageIcon("D:\\study\\eclipse-workspace\\Java_Project_2_1\\Image\\routine.jpg").getImage();
	                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        panel.setLayout(null);
	        setContentPane(panel);

	        welcomeLabel = new JLabel("Welcome respected teachers");
	        welcomeLabel.setBounds(110, 30, 400, 30);
	        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 25));
	        panel.add(welcomeLabel);

	        showRoutineButton = new JButton("Show Routine");
	        showRoutineButton.setBounds(150, 200, 300, 30);
	        showRoutineButton.setFont(new Font("Arial", Font.BOLD, 30));
	        panel.add(showRoutineButton);

	        takeAttendanceButton = new JButton("Take Attendance"); // New button for taking attendance
	        takeAttendanceButton.setBounds(150, 250, 300, 30);
	        takeAttendanceButton.setFont(new Font("Arial", Font.BOLD, 30));
	        panel.add(takeAttendanceButton);

	        // ActionListener for the takeAttendanceButton
	        takeAttendanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new TeacherAttGui();
	               
	            }
	        });

	        showRoutineButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               
	                // The existing code for showing routine
	                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	                    @Override
	                    protected Void doInBackground() throws Exception {
	                        try {
	                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/routine", "root", "14498");
	                            Statement statement = connection.createStatement();
	                            ResultSet resultSet = statement.executeQuery("SELECT day, TIME1, TIME2, BREAK, TIME3, TIME4 FROM routine_all");

	                            JTable table = new JTable(buildTableModel(resultSet));
	                            JPanel panel = new JPanel(new BorderLayout());
	                            JLabel label = new JLabel("Here is your routine");
	                            label.setHorizontalAlignment(JLabel.CENTER);
	                            panel.add(label, BorderLayout.NORTH);
	                            panel.add(new JScrollPane(table), BorderLayout.CENTER);

	                            JFrame newFrame = new JFrame("Routine Details");
	                            newFrame.add(panel);
	                            newFrame.setSize(1000, 600);
	                            newFrame.setVisible(true);

	                            resultSet.close();
	                            statement.close();
	                            connection.close();

	                        } catch (Exception ex) {
	                            ex.printStackTrace();
	                        }
	                        return null;
	                    }
	                };
	                worker.execute();
	            }
	        });

	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        new RoutineGui();
	    }

	    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
	        ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
	        // Column names
	        int columnCount = metaData.getColumnCount();
	        Vector<String> columnNames = new Vector<>();
	        for (int column = 1; column <= columnCount; column++) {
	            columnNames.add(metaData.getColumnName(column));
	        }
	        // Data of the table
	        Vector<Vector<Object>> data = new Vector<>();
	        while (rs.next()) {
	            Vector<Object> vector = new Vector<>();
	            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	                vector.add(rs.getObject(columnIndex));
	            }
	            data.add(vector);
	        }
	        return new DefaultTableModel(data, columnNames);
	    }
}
