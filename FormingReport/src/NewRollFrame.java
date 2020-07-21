import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class NewRollFrame extends JFrame {
	
	
	private JTextField textFieldSheetCode;
	private JTextField textFieldExNum;
	private JTextField textFieldRollNum;
	private JTextField textFieldLength;
	private JTextField textFieldWeight;
	private JFormattedTextField formattedTextFieldDate;
	private JPanel panelMain;
	private JPanel panel_SheetCode;
	private JPanel panel_EXNum;
	private JPanel panel_Date;
	private JPanel panel_Time;
	private JFormattedTextField formattedTextFieldTime;
	private JPanel panel_RollNum;
	private JPanel panel_Lenth;
	private JPanel panel_Weight;
	private JPanel panelButtons;
	private JButton btnAdd;
	private JButton btnCancel;
	private DefaultTableModel rollModel;
	private String reportID;
	
	
	public NewRollFrame(DefaultTableModel rm, String reportID) {
		this.rollModel = rm;
		this.reportID = reportID;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(450, 650);
		setTitle("New Roll");
		
		panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(null, "New Roll", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(7, 1, 0, 0));
		
		panel_SheetCode = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_SheetCode.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_SheetCode.setBorder(new TitledBorder(null, "Sheet Code OR OPS Part # (NOT SIZE)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_SheetCode);
		
		textFieldSheetCode = new JTextField();
		panel_SheetCode.add(textFieldSheetCode);
		textFieldSheetCode.setColumns(30);
		
		panel_EXNum = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_EXNum.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_EXNum.setBorder(new TitledBorder(null, "Extrusion MO#", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_EXNum);
		
		textFieldExNum = new JTextField();
		panel_EXNum.add(textFieldExNum);
		textFieldExNum.setColumns(30);
		
		panel_Date = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_Date.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_Date.setBorder(new TitledBorder(null, "Date Made", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_Date);
		
		formattedTextFieldDate = new JFormattedTextField(createFormatter("##'/##'/####"));
		formattedTextFieldDate.setColumns(15);
		panel_Date.add(formattedTextFieldDate);
		
		panel_Time = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_Time.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_Time.setBorder(new TitledBorder(null, "Time Made", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_Time);
		
		formattedTextFieldTime = new JFormattedTextField(createFormatter("##:##"));
		formattedTextFieldTime.setColumns(5);
		panel_Time.add(formattedTextFieldTime);
		
		panel_RollNum = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_RollNum.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_RollNum.setBorder(new TitledBorder(null, "Roll # or OPS Lot #", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_RollNum);
		
		textFieldRollNum = new JTextField();
		panel_RollNum.add(textFieldRollNum);
		textFieldRollNum.setColumns(25);
		
		panel_Lenth = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_Lenth.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_Lenth.setBorder(new TitledBorder(null, "Meters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_Lenth);
		
		textFieldLength = new JTextField();
		panel_Lenth.add(textFieldLength);
		textFieldLength.setColumns(10);
		
		panel_Weight = new JPanel();
		FlowLayout flowLayout_6 = (FlowLayout) panel_Weight.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panel_Weight.setBorder(new TitledBorder(null, "LBS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_Weight);
		
		textFieldWeight = new JTextField();
		panel_Weight.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add");
		panelButtons.add(btnAdd);
		
		btnAdd.addActionListener(new AddRollListener());
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		
		btnCancel.addActionListener(new CancelRollListener());
		setLocationRelativeTo(null);
	}
	
	private class AddRollListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] newRoll = {textFieldSheetCode.getText(), 
								textFieldExNum.getText(), 
								formattedTextFieldDate.getText(), 
								formattedTextFieldTime.getText(), 
								textFieldRollNum.getText(), 
								textFieldLength.getText(), 
								textFieldWeight.getText(), 
								"Shots", 
								"Part Weight"};
			rollModel.addRow(newRoll);
			
			newRoll = null;
			
			
			try(Connection conn = DriverManager.getConnection(Constants.DB_URL)) {

				String sql = "INSERT INTO Rolls_T(SheetCode, ExtrusionMONum, DateTimeMade, RollNum, RollLength, RollWeight, FormingReportID) "
						+ "VALUES(?,?,?,?,?,?,?)";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, textFieldSheetCode.getText());
				pstmt.setString(2, textFieldExNum.getText());
				pstmt.setString(3, getDate(formattedTextFieldDate.getText()) + " " + formattedTextFieldTime.getText());
				pstmt.setString(4, textFieldRollNum.getText());
				pstmt.setInt(5, Integer.parseInt(textFieldLength.getText()));
				pstmt.setInt(6, Integer.parseInt(textFieldWeight.getText()));
				pstmt.setString(7, reportID);
				
				pstmt.execute();
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
			
			dispose();
		}
		
	}
	
	private class CancelRollListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	
	private String getDate(String date) {
		String month;
		String day;
		String year;
		
		month = date.substring(0, 2);
		day = date.substring(3, 5);
		year = date.substring(6);
		
		return year + "-" + month + "-" + day;
	}
	
	private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }//End createFormatter method
	
}