import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NewUtilityFrame extends JFrame {
	
	private JTextField textFieldBadgeNum;
	private JTextField textFieldUtilityFName;
	private JTextField textFieldUtilityLName;
	private JTextField textFieldHours;
	private JPanel panelMain;
	private JPanel panelEmpIdNum;
	private JPanel panelEmpFName;
	private JPanel panelEmpLName;
	private JPanel panelHours;
	private JPanel panelButtons;
	private JButton btnAdd;
	private JButton btnCancel;
	private DefaultTableModel employeeUtilityModel;
	private String reportID;
	
	public NewUtilityFrame(DefaultTableModel employeeUtilityModel, String reportID) {
		this.employeeUtilityModel = employeeUtilityModel;
		this.reportID = reportID;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(450, 650);
		setTitle("New Utility");
		
		
		panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(null, "New Utility", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(3, 0, 0, 0));
		
		panelEmpIdNum = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelEmpIdNum.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelEmpIdNum.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Badge Number", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.add(panelEmpIdNum);
		
		textFieldBadgeNum = new JTextField();
		panelEmpIdNum.add(textFieldBadgeNum);
		textFieldBadgeNum.setColumns(10);
		
		panelEmpFName = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelEmpFName.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelEmpFName.setBorder(new TitledBorder(null, "Utility First Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panelEmpFName);
		
		textFieldUtilityFName = new JTextField();
		panelEmpFName.add(textFieldUtilityFName);
		textFieldUtilityFName.setColumns(10);
		
		panelEmpLName = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelEmpLName.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelEmpLName.setBorder(new TitledBorder(null, "Utility Last Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panelEmpLName);
		
		textFieldUtilityLName = new JTextField();
		panelEmpLName.add(textFieldUtilityLName);
		textFieldUtilityLName.setColumns(10);
		
//		panelHours = new JPanel();
//		FlowLayout flowLayout_3 = (FlowLayout) panelHours.getLayout();
//		flowLayout_3.setAlignment(FlowLayout.LEFT);
//		panelHours.setBorder(new TitledBorder(null, "Hours at the Machine", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panelMain.add(panelHours);
		
//		textFieldHours = new JTextField();
//		panelHours.add(textFieldHours);
//		textFieldHours.setColumns(10);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add");
		panelButtons.add(btnAdd);
		btnAdd.addActionListener(new AddEmployeeListener());
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		btnCancel.addActionListener(new CancelEmployeeListener());
		
		setLocationRelativeTo(null);
	}
	
	private class AddEmployeeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] newUtility = {textFieldBadgeNum.getText(), 
								textFieldUtilityFName.getText(), 
								textFieldUtilityLName.getText(),
								"Hours"};
			employeeUtilityModel.addRow(newUtility);
			newUtility = null;
			
			try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
				
				String sql = "INSERT INTO Utility_T(EmployeeID, "
							 + "EmployeeFName, "
							 + "EmployeeLName, "
							 + "FormingReportID) "
							 + "VALUES(?,?,?,?)";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(textFieldBadgeNum.getText()));
				pstmt.setString(2, textFieldUtilityFName.getText());
				pstmt.setString(3, textFieldUtilityLName.getText());
				pstmt.setString(4, reportID);
				
				pstmt.execute();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
			
			dispose();
		}
		
	}
	
	private class CancelEmployeeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();	
		}
		
	}
	
}