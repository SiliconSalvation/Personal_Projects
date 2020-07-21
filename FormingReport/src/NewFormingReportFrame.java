import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

public class NewFormingReportFrame extends JFrame {
	
	private JPanel panelMain;
	private JPanel panel_Date;
	private JFormattedTextField formattedTextFieldDate;
	private JPanel panel_Shift;
	private JComboBox<String> comboBoxShift;
	private JPanel panel_CrusherTankLetter;
	private JComboBox<String> comboBoxCrusherTank;
	private JPanel panel_MachineNumber;
	private JFormattedTextField formattedTextFieldMachineNumber;
	private JPanel panel_WorkOrderNum;
	private JFormattedTextField formattedTextFieldWorkNum;
	private JPanel panel_ProductCode;
	private JPanel panel_ProductDescription;
	private JPanel panel_FeedLength;
	private JPanel panelButtons;
	private JButton btnAdd;
	private JButton btnCancel;
	private JTextField textFieldProductCode;
	private JTextField textFieldProductDescription;
	private JTextField textFieldFeedLength;
	private JTextField textFieldPiecesPerShot;
	private JTextField textFieldPartsPerCase;
	private JPanel panelPiecesPerShot;
	private JPanel panelPartsPerCase;
	
	public NewFormingReportFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(450, 750);
		setTitle("New Report");
		
		panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(null, "Forming Report", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(10, 1, 0, 0));
		
		
		panel_Date = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_Date.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_Date.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Report Date", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.add(panel_Date);
		
		formattedTextFieldDate = new JFormattedTextField(createFormatter("##'/##'/####"));
		formattedTextFieldDate.setColumns(15);
		formattedTextFieldDate.setHorizontalAlignment(SwingConstants.LEFT);
		panel_Date.add(formattedTextFieldDate);
		
		panel_Shift = new JPanel();
		panel_Shift.setBorder(new TitledBorder(null, "Shift", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout_2 = (FlowLayout) panel_Shift.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelMain.add(panel_Shift);
		
		comboBoxShift = new JComboBox<String>();
		comboBoxShift.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
		panel_Shift.add(comboBoxShift);
		
		panel_CrusherTankLetter = new JPanel();
		panel_CrusherTankLetter.setBorder(new TitledBorder(null, "Crusher Tank Letter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout fl_panel_CrusherTankLetter = (FlowLayout) panel_CrusherTankLetter.getLayout();
		fl_panel_CrusherTankLetter.setAlignment(FlowLayout.LEFT);
		panelMain.add(panel_CrusherTankLetter);
		
		comboBoxCrusherTank = new JComboBox<String>();
		comboBoxCrusherTank.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));
		panel_CrusherTankLetter.add(comboBoxCrusherTank);
		
		panel_MachineNumber = new JPanel();
		panel_MachineNumber.setBorder(new TitledBorder(null, "Machine Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout fl_panel_MachineNumber = (FlowLayout) panel_MachineNumber.getLayout();
		fl_panel_MachineNumber.setAlignment(FlowLayout.LEFT);
		panelMain.add(panel_MachineNumber);
		
		formattedTextFieldMachineNumber = new JFormattedTextField(createFormatter("UU##"));
		formattedTextFieldMachineNumber.setColumns(5);
		panel_MachineNumber.add(formattedTextFieldMachineNumber);
		
		panel_WorkOrderNum = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_WorkOrderNum.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_WorkOrderNum.setBorder(new TitledBorder(null, "Work Order Number", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_WorkOrderNum);
		
		formattedTextFieldWorkNum = new JFormattedTextField(createFormatter("UU######'-##"));
		formattedTextFieldWorkNum.setColumns(25);
		panel_WorkOrderNum.add(formattedTextFieldWorkNum);
		
		panel_ProductCode = new JPanel();
		panel_ProductCode.setBorder(new TitledBorder(null, "Product Code", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout fl_panel_ProductCode = (FlowLayout) panel_ProductCode.getLayout();
		fl_panel_ProductCode.setAlignment(FlowLayout.LEFT);
		panelMain.add(panel_ProductCode);
		
		textFieldProductCode = new JTextField();
		panel_ProductCode.add(textFieldProductCode);
		textFieldProductCode.setColumns(25);
		
		panel_ProductDescription = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_ProductDescription.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_ProductDescription.setBorder(new TitledBorder(null, "Product Description", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelMain.add(panel_ProductDescription);
		
		textFieldProductDescription = new JTextField();
		panel_ProductDescription.add(textFieldProductDescription);
		textFieldProductDescription.setColumns(25);
		
		panel_FeedLength = new JPanel();
		panel_FeedLength.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Actual Feed Length", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		FlowLayout fl_panel_FeedLength = (FlowLayout) panel_FeedLength.getLayout();
		fl_panel_FeedLength.setAlignment(FlowLayout.LEFT);
		panelMain.add(panel_FeedLength);
		
		textFieldFeedLength = new JTextField();
		panel_FeedLength.add(textFieldFeedLength);
		textFieldFeedLength.setColumns(10);
		
		panelPiecesPerShot = new JPanel();
		panelPiecesPerShot.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pieces Per Shot", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,0,0)));
		FlowLayout fl_panelPiecesPerShot = (FlowLayout) panelPiecesPerShot.getLayout();
		fl_panelPiecesPerShot.setAlignment(FlowLayout.LEFT);
		panelMain.add(panelPiecesPerShot);
		
		textFieldPiecesPerShot = new JTextField();
		panelPiecesPerShot.add(textFieldPiecesPerShot);
		textFieldPiecesPerShot.setColumns(5);
		
		panelPartsPerCase = new JPanel();
		panelPartsPerCase.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pieces Per Case", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,0,0)));
		FlowLayout fl_panelPartsPerCase = (FlowLayout) panelPartsPerCase.getLayout();
		fl_panelPartsPerCase.setAlignment(FlowLayout.LEFT);
		panelMain.add(panelPartsPerCase);
		
		textFieldPartsPerCase = new JTextField();
		panelPartsPerCase.add(textFieldPartsPerCase);
		textFieldPartsPerCase.setColumns(5);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add");
		panelButtons.add(btnAdd);
		btnAdd.addActionListener(new AddButtonListener());
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		btnCancel.addActionListener(new CancelButtonListener());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class AddButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new ReportGUI(formattedTextFieldDate.getText(),
						  comboBoxShift.getSelectedIndex(), 
						  comboBoxCrusherTank.getSelectedIndex(), 
						  formattedTextFieldMachineNumber.getText(), 
						  formattedTextFieldWorkNum.getText(), 
						  textFieldProductCode.getText(), 
						  textFieldProductDescription.getText(), 
						  textFieldFeedLength.getText(),
						  textFieldPiecesPerShot.getText(),
						  textFieldPartsPerCase.getText(),
						  getFormingReportID(formattedTextFieldWorkNum.getText(),
								   formattedTextFieldDate.getText(),
								   comboBoxShift.getSelectedItem().toString(),
								   formattedTextFieldMachineNumber.getText()));
			
			try(Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
				
				
				String sql = "INSERT INTO FormingReport_T(WorkOrderNumber, "
							+ "FRDate, "
							+ "Shift, "
							+ "CrusherTankLetter, "
							+ "MachineNum, "
							+ "ProductCode, "
							+ "ProductDescription, "
							+ "FeedLength, "
							+ "PiecesInShot, "
							+ "PiecesPerCase, "
							+ "FormingReportID) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, formattedTextFieldWorkNum.getText());
				pstmt.setString(2, getDate(formattedTextFieldDate.getText()));
				pstmt.setInt(3, Integer.parseInt(comboBoxShift.getSelectedItem().toString()));
				pstmt.setString(4, comboBoxCrusherTank.getSelectedItem().toString());
				pstmt.setString(5, formattedTextFieldMachineNumber.getText());
				pstmt.setString(6, textFieldProductCode.getText());
				pstmt.setString(7, textFieldProductDescription.getText());
				pstmt.setDouble(8, Double.parseDouble(textFieldFeedLength.getText()));
				pstmt.setInt(9, Integer.parseInt(textFieldPiecesPerShot.getText()));
				pstmt.setInt(10, Integer.parseInt(textFieldPartsPerCase.getText()));
				pstmt.setString(11, getFormingReportID(formattedTextFieldWorkNum.getText(),
													   formattedTextFieldDate.getText(),
													   comboBoxShift.getSelectedItem().toString(),
													   formattedTextFieldMachineNumber.getText()));
				
				pstmt.execute();
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
			
			dispose();
		}
		
	}
	
	private class CancelButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
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
	
	private String getDate(String date) {
		String month;
		String day;
		String year;
		
		month = date.substring(0, 2);
		day = date.substring(3, 5);
		year = date.substring(6);
		
		return year + "-" + month + "-" + day;
	}
	//
	//work order number, current date, shift, machine number
	//mmddyyyymmddyyyysnn
	private String getFormingReportID(String workOrder, String date, String shift, String machineNum) {
		String wo;
		String d;
		String mNum;
		
		wo = workOrder.substring(2, 8) + workOrder.substring(9);
		d = date.substring(0, 2) + date.substring(3, 5) + date.substring(6);
		mNum = machineNum.substring(2);
		
		return wo + d + shift + mNum;
	}

}