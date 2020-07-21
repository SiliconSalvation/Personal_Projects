import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class ReportGUI extends JFrame {
	/**
	 * @author Nick Stauffer
	 * @author staunw01@pfw.edu
	 * @version 1.00
	 */
	//
	//Global Variables
	//
	//Startup Variables
	private JFormattedTextField txtDate;
	private JFormattedTextField textFieldMachineNumber;
	private JFormattedTextField textFieldWorkOrder;
	private JTextField textFieldProductCode;
	private JTextField textFieldProductDescription;
	private JTextField textFieldFeedLength;
	private JTextField textFieldCasesMoved;
	private JTextField textFieldPiecesPerShot;
	private JTextField textPiecesPerCase;
	private JTextField textTotalShots;
	private JTextField textAvgPartWeight;
	private JTextField textTotalUtilityHours;
	private JTextField textTotalScrapPounds;
	private JTextField textTotalScrapParts;
	private JComboBox<String> shiftComboBox;
	private JComboBox<String> crusherLetterComboBox;
	private JTable tableTechName;
	private JTable tableUtilityNames;
	private JTable tableScrapTickets;
	private JTable tableRolls;	
	private DefaultTableModel employeeUtilityModel = new DefaultTableModel(Constants.EMPLOYEE_UTILITY_NAMES, 0);
	private DefaultTableModel employeeTechModel = new DefaultTableModel(Constants.EMPLOYEE_TECH_COLUMNS, 0);
	private DefaultTableModel rollModel = new DefaultTableModel(Constants.ROLL_COLUMNS,0);
	private DefaultTableModel scrapModel = new DefaultTableModel(Constants.SCRAP_COLUMNS,0);
	private JButton btnCalculateTotals;
	private JButton btnDeleteRoll;
	private JButton btnAddRoll;
	private JButton btnAddTechName;
	private JButton btnAddUtilityName;
//	private JButton btnUpdateInfo;
	private JLabel lblFormingOperationReport;
	private JButton btnRemoveTechnician;
	private JButton btnRemoveUtility;
	private JButton btnAddScrapTicket;
	private JMenuItem mntmLoad;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenuItem mntmCreateNewReport;
	private JPanel panelScrapTickets;
	private JPanel panelUtilityNames;
	private JPanel panelRolls;
	private JPanel panelSmiley;
	private JLabel lblSmile;
	private JSeparator separator_1;
	private JButton btnRemoveScrapTicket;
	private String reportID;
	
	//
	//Constructor
	//
	public ReportGUI(String date, 
					 int shift, 
					 int crusherLetter, 
					 String machineNum, 
					 String workOrder, 
					 String prodCode, 
					 String prodDesc, 
					 String feedLen, 
					 String partsPerShot,
					 String partsPerCase,
					 String reportID) {
		
		setResizable(false);
		setTitle("Operation Forming Report");
		
		this.reportID = reportID;
		setSize(new Dimension(1366, 768));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		lblFormingOperationReport = new JLabel("Forming Operation Report");
		lblFormingOperationReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormingOperationReport.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblFormingOperationReport.setBounds(38, 11, 254, 22);
		panel.add(lblFormingOperationReport);
		
		txtDate = new JFormattedTextField(createFormatter("##'/##'/####"));
		txtDate.setBounds(506, 12, 103, 20);
		panel.add(txtDate);
		txtDate.setColumns(10);
		txtDate.setText(date);
		txtDate.setEditable(false);
		
		shiftComboBox = new JComboBox<String>();
		shiftComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5"}));
		shiftComboBox.setBounds(697, 11, 52, 22);
		panel.add(shiftComboBox);
		shiftComboBox.setSelectedIndex(shift);
		shiftComboBox.setEnabled(false);
		
		JLabel lblShift = new JLabel("Shift");
		lblShift.setBorder(new EmptyBorder(1, 1, 1, 0));
		lblShift.setHorizontalAlignment(SwingConstants.TRAILING);
		lblShift.setBounds(619, 15, 68, 14);
		panel.add(lblShift);
		
		JLabel lblCrusherTankLetter = new JLabel("Crusher Tank Letter");
		lblCrusherTankLetter.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCrusherTankLetter.setBounds(687, 12, 208, 20);
		panel.add(lblCrusherTankLetter);
		
		crusherLetterComboBox = new JComboBox<String>();
		crusherLetterComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"A", "B", "C", "D", "E", "F", "G", 
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));
		crusherLetterComboBox.setBounds(912, 12, 103, 20);
		panel.add(crusherLetterComboBox);
		crusherLetterComboBox.setSelectedIndex(crusherLetter);
		crusherLetterComboBox.setEnabled(false);
		
		JLabel lblMachineNumber = new JLabel("Machine Number:");
		lblMachineNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMachineNumber.setBounds(1025, 15, 130, 14);
		panel.add(lblMachineNumber);
		
		textFieldMachineNumber = new JFormattedTextField(createFormatter("UU##"));
		textFieldMachineNumber.setBounds(1165, 12, 89, 20);
		panel.add(textFieldMachineNumber);
		textFieldMachineNumber.setColumns(10);
		textFieldMachineNumber.setText(machineNum);
		textFieldMachineNumber.setEditable(false);
		
		textFieldWorkOrder = new JFormattedTextField(createFormatter("UU######'-##"));
		textFieldWorkOrder.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldWorkOrder.setText("Enter Work Order");
		textFieldWorkOrder.setBounds(38, 59, 297, 20);
		panel.add(textFieldWorkOrder);
		textFieldWorkOrder.setColumns(10);
		textFieldWorkOrder.setText(workOrder);
		textFieldWorkOrder.setEditable(false);
		
		textFieldProductCode = new JTextField();
		textFieldProductCode.setBounds(345, 59, 264, 20);
		panel.add(textFieldProductCode);
		textFieldProductCode.setColumns(10);
		textFieldProductCode.setText(prodCode);
		textFieldProductCode.setEditable(false);
		
		textFieldProductDescription = new JTextField();
		textFieldProductDescription.setBounds(622, 59, 371, 20);
		panel.add(textFieldProductDescription);
		textFieldProductDescription.setColumns(10);
		textFieldProductDescription.setText(prodDesc);
		textFieldProductDescription.setEditable(false);
		
		textFieldFeedLength = new JTextField();
		textFieldFeedLength.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldFeedLength.setBounds(1033, 59, 221, 20);
		panel.add(textFieldFeedLength);
		textFieldFeedLength.setColumns(10);
		textFieldFeedLength.setText(feedLen);
		textFieldFeedLength.setEditable(false);
		
		JLabel lblTotalCasesMoved = new JLabel("Total Cases Moved (From Workorder Sheet)");
		lblTotalCasesMoved.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalCasesMoved.setBounds(410, 333, 449, 14);
		panel.add(lblTotalCasesMoved);
		
		btnCalculateTotals = new JButton("Calculate Totals");
		btnCalculateTotals.setBounds(1059, 465, 195, 125);
		panel.add(btnCalculateTotals);
		btnCalculateTotals.addActionListener(new CalculateTotalsListener());
		
		btnDeleteRoll = new JButton("Remove Roll");
		btnDeleteRoll.setBounds(1059, 269, 195, 42);
		panel.add(btnDeleteRoll);
		btnDeleteRoll.addActionListener(new RemoveTableItemListener());
		
		btnAddRoll = new JButton("Add Roll");
		btnAddRoll.setBounds(854, 269, 195, 42);
		panel.add(btnAddRoll);
		
		textFieldCasesMoved = new JTextField();
		textFieldCasesMoved.setBounds(881, 333, 86, 20);
		panel.add(textFieldCasesMoved);
		textFieldCasesMoved.setColumns(10);
		
		btnAddTechName = new JButton("Add Technician");
		btnAddTechName.setBounds(38, 195, 168, 49);
		panel.add(btnAddTechName);
		btnAddTechName.addActionListener(new AddEmployeeListener());
		
		btnAddUtilityName = new JButton("Add Utility");
		btnAddUtilityName.setBounds(38, 378, 168, 49);
		panel.add(btnAddUtilityName);
		btnAddUtilityName.addActionListener(new AddEmployeeListener());
		
		JLabel lblNumberOfPieces = new JLabel("Number of Pieces in a Shot");
		lblNumberOfPieces.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumberOfPieces.setBounds(446, 367, 413, 14);
		panel.add(lblNumberOfPieces);
		
		textFieldPiecesPerShot = new JTextField();
		textFieldPiecesPerShot.setBounds(881, 364, 86, 20);
		panel.add(textFieldPiecesPerShot);
		textFieldPiecesPerShot.setColumns(10);
		textFieldPiecesPerShot.setText(partsPerShot);
		textFieldPiecesPerShot.setEditable(false);
		
		JLabel lblPiecesPerCase = new JLabel("Pieces Per Case (From Workorder Sheet)");
		lblPiecesPerCase.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPiecesPerCase.setBounds(446, 398, 413, 14);
		panel.add(lblPiecesPerCase);
		
		textPiecesPerCase = new JTextField();
		textPiecesPerCase.setBounds(881, 394, 86, 20);
		panel.add(textPiecesPerCase);
		textPiecesPerCase.setColumns(10);
		textPiecesPerCase.setText(partsPerCase);
		textPiecesPerCase.setEditable(false);
		
//		btnUpdateInfo = new JButton("Set Information");
//		btnUpdateInfo.setBounds(1059, 333, 195, 125);
//		panel.add(btnUpdateInfo);
//		btnUpdateInfo.addActionListener(new SetInfoListener());
		
		btnRemoveTechnician = new JButton("Remove Selected Technician");
		btnRemoveTechnician.setBounds(207, 195, 175, 49);
		panel.add(btnRemoveTechnician);
		btnRemoveTechnician.addActionListener(new RemoveTableItemListener());
		
		btnRemoveUtility = new JButton("Remove Selected Utility");
		btnRemoveUtility.setBounds(207, 378, 175, 49);
		panel.add(btnRemoveUtility);
		btnRemoveUtility.addActionListener(new RemoveTableItemListener());
		
		btnAddScrapTicket = new JButton("Add Scrap Ticket");
		btnAddScrapTicket.setBounds(38, 580, 168, 49);
		panel.add(btnAddScrapTicket);
		btnAddScrapTicket.addActionListener(new AddScrapTicket());
		
		textTotalShots = new JTextField();
		textTotalShots.setEditable(false);
		textTotalShots.setBounds(881, 425, 86, 20);
		panel.add(textTotalShots);
		textTotalShots.setColumns(10);
		
		textAvgPartWeight = new JTextField();
		textAvgPartWeight.setEditable(false);
		textAvgPartWeight.setBounds(881, 455, 86, 20);
		panel.add(textAvgPartWeight);
		textAvgPartWeight.setColumns(10);
		
		JLabel lblTotalShots = new JLabel("Total Shots");
		lblTotalShots.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalShots.setBounds(528, 429, 331, 14);
		panel.add(lblTotalShots);
		
		JLabel lblAVGPartWeight = new JLabel("Average Part Weight");
		lblAVGPartWeight.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAVGPartWeight.setBounds(538, 458, 321, 14);
		panel.add(lblAVGPartWeight);
		
		textTotalUtilityHours = new JTextField();
		textTotalUtilityHours.setEditable(false);
		textTotalUtilityHours.setBounds(881, 486, 86, 20);
		panel.add(textTotalUtilityHours);
		textTotalUtilityHours.setColumns(10);
		
		JLabel lblTotalUtilityHours = new JLabel("Total Utility Hours");
		lblTotalUtilityHours.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalUtilityHours.setBounds(446, 489, 413, 14);
		panel.add(lblTotalUtilityHours);
		
		textTotalScrapPounds = new JTextField();
		textTotalScrapPounds.setEditable(false);
		textTotalScrapPounds.setBounds(881, 517, 86, 20);
		panel.add(textTotalScrapPounds);
		textTotalScrapPounds.setColumns(10);
		
		JLabel lblTotalScrapPounds = new JLabel("Total Scrap Pounds");
		lblTotalScrapPounds.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalScrapPounds.setBounds(562, 520, 297, 14);
		panel.add(lblTotalScrapPounds);
		
		textTotalScrapParts = new JTextField();
		textTotalScrapParts.setEditable(false);
		textTotalScrapParts.setBounds(881, 548, 86, 20);
		panel.add(textTotalScrapParts);
		textTotalScrapParts.setColumns(10);
		
		JLabel lblTotalScrapParts = new JLabel("Total Scrap Parts");
		lblTotalScrapParts.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalScrapParts.setBounds(538, 551, 321, 14);
		panel.add(lblTotalScrapParts);
		
		JPanel panelTechName = new JPanel();
		panelTechName.setBounds(38, 90, 344, 100);
		panel.add(panelTechName);
		tableTechName = new JTable(employeeTechModel);
		tableTechName.getTableHeader().setReorderingAllowed(false);
		tableTechName.setRowHeight(Constants.TABLE_ROW_HEIGHT);
		JScrollPane techScrollPane = new JScrollPane(tableTechName);
		techScrollPane.setPreferredSize(new Dimension(343, 95));
		panelTechName.add(techScrollPane);
		
		
		panelScrapTickets = new JPanel();
		panelScrapTickets.setBounds(38, 436, 345, 139);
		tableScrapTickets = new JTable(scrapModel);
		tableScrapTickets.getTableHeader().setReorderingAllowed(false);
		tableScrapTickets.setRowHeight(Constants.TABLE_ROW_HEIGHT);
		JScrollPane scrapScrollPane = new JScrollPane(tableScrapTickets);
		scrapScrollPane.setPreferredSize(new Dimension(343, 132));
		panelScrapTickets.add(scrapScrollPane);
		panel.add(panelScrapTickets);
		
		panelUtilityNames = new JPanel();
		panelUtilityNames.setBounds(38, 249, 344, 125);
		tableUtilityNames = new JTable(employeeUtilityModel);
		tableUtilityNames.getTableHeader().setReorderingAllowed(false);
		tableUtilityNames.setRowHeight(Constants.TABLE_ROW_HEIGHT);
		JScrollPane utilityScrollPane = new JScrollPane(tableUtilityNames);
		utilityScrollPane.setPreferredSize(new Dimension(343, 117));
		panelUtilityNames.add(utilityScrollPane);
		panel.add(panelUtilityNames);
		
		rollModel.addTableModelListener(new RollTableChangedListener());
		panelRolls = new JPanel();
		panelRolls.setBounds(410, 90, 844, 177);
		tableRolls = new JTable(rollModel);
		tableRolls.getTableHeader().setReorderingAllowed(false);
		tableRolls.setRowHeight(Constants.TABLE_ROW_HEIGHT);
		JScrollPane rollScrollPane = new JScrollPane(tableRolls);
		rollScrollPane.setPreferredSize(new Dimension(843, 170));
		panelRolls.add(rollScrollPane);
		tableRolls.setFocusable(true);
		panel.add(panelRolls);
		
		/*TableCellListener tclRolls = */new TableCellListener(tableRolls, actionRolls);
		/*TableCellListener tclScrap = */new TableCellListener(tableScrapTickets, actionScrap);
		/*TableCellListener tclTech = */new TableCellListener(tableTechName, actionTech);
		/*TableCellListener tclUtility = */new TableCellListener(tableUtilityNames, actionUtility);
		
		panelSmiley = new JPanel();
		panelSmiley.setBounds(410, 428, 247, 186);
		panel.add(panelSmiley);
		
		lblSmile = new JLabel("");
		lblSmile.setVisible(false);
//		lblSmile.setIcon(new ImageIcon("smiley24.png"));
		panelSmiley.add(lblSmile);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(38, 85, 1216, 2);
		panel.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(392, 85, 9, 530);
		panel.add(separator_1);
		
		btnRemoveScrapTicket = new JButton("Remove Scrap Ticket");
		btnRemoveScrapTicket.setMaximumSize(new Dimension(134, 23));
		btnRemoveScrapTicket.setBounds(207, 580, 175, 49);
		panel.add(btnRemoveScrapTicket);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDate.setBounds(313, 15, 183, 14);
		panel.add(lblDate);
		
		JLabel lblWorkOrder = new JLabel("Work Order");
		lblWorkOrder.setBounds(38, 44, 297, 14);
		panel.add(lblWorkOrder);
		
		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setBounds(345, 43, 264, 14);
		panel.add(lblProductCode);
		
		JLabel lblProductDescription = new JLabel("Product Description");
		lblProductDescription.setBounds(622, 40, 364, 14);
		panel.add(lblProductDescription);
		
		JLabel lblActualFeedLength = new JLabel("Actual Feed Length");
		lblActualFeedLength.setBounds(1035, 40, 219, 14);
		panel.add(lblActualFeedLength);
		btnRemoveScrapTicket.addActionListener(new RemoveTableItemListener());

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmCreateNewReport = new JMenuItem("Create New Report");
		mnFile.add(mntmCreateNewReport);
		
		mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmEndOfShift = new JMenuItem("End of Shift Report");
		mnReports.add(mntmEndOfShift);
		mntmExit.addActionListener(new MenuExitButtonListener());
		
		btnAddRoll.addActionListener(new AddRollListener());
		
		TableColumn colDate = tableRolls.getColumnModel().getColumn(2);
		TableColumn colTime = tableRolls.getColumnModel().getColumn(3);
		colDate.setCellEditor(new TableDateCellEditor());
		colTime.setCellEditor(new TableTimeCellEditor());
		
		employeeUtilityModel.addTableModelListener(new UtilityTableChangedListener());
		employeeTechModel.addTableModelListener(new TechTableChangedListener());
		scrapModel.addTableModelListener(new ScrapTableChangedListener());
		
		txtDate.setToolTipText("Enter date as 'MM/DD/YYYY'");
		
		setLocationRelativeTo(null);
		setVisible(true);
	}//End Constructor
	
	//
	//Class AddRollListener
	//
	private class AddRollListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			NewRollFrame rf = new NewRollFrame(rollModel, reportID);
			rf.setVisible(true);

		}
	}//End Class AddRollListener
	//
	//Class CalculateTotalsListener
	//
	private class CalculateTotalsListener implements ActionListener {
		DecimalFormat df = new DecimalFormat("#.000");
		DecimalFormat df2 = new DecimalFormat("#.00");
		DecimalFormat df3 = new DecimalFormat("###");
		@Override
		public void actionPerformed(ActionEvent e) {
			int totalShots = 0;
			double partWeightTotal = 0;
			int count = 0;
			double average = 0;
			double utilityHours = 0;
			double totalScrapWeight = 0;
			double scrapParts = 0;
			String shots = null;
			String AVGPartWeight = null;
			String hoursWorked = null;
			String totalScrapPounds = null;
	
			try {
				for (int i = 0; i < rollModel.getRowCount(); i++) {
					shots = (String) rollModel.getValueAt(i, 7);
					AVGPartWeight = (String) rollModel.getValueAt(i, 8);
					totalShots += Integer.parseInt(shots);
					partWeightTotal += Double.parseDouble(AVGPartWeight);
					count++;
				}
			
				average = partWeightTotal / count;
				textTotalShots.setText(df3.format(totalShots));
				textAvgPartWeight.setText(df.format(average));
				
				for (int i = 0; i < employeeUtilityModel.getRowCount(); i++) {
					hoursWorked = (String) employeeUtilityModel.getValueAt(i, 3);
					utilityHours += Double.parseDouble(hoursWorked);
				}
				textTotalUtilityHours.setText(Double.toString(utilityHours));
				
				for (int i = 0; i < scrapModel.getRowCount(); i++) {
					totalScrapPounds = (String) scrapModel.getValueAt(i, 1);
					totalScrapWeight += Double.parseDouble(totalScrapPounds);
				}
				textTotalScrapPounds.setText(df2.format(totalScrapWeight));
				scrapParts = totalScrapWeight / average; 
				textTotalScrapParts.setText(df3.format(scrapParts));
				lblSmile.setVisible(true);
				
			} catch (Exception ex) {
//				if (employeeUtilityModel.getRowCount() < 1 || employeeTechModel.getRowCount() < 1 
//						|| rollModel.getRowCount() < 1 || scrapModel.getRowCount() < 1) {
//					JOptionPane.showMessageDialog(null, "There is an empty table");
//				} 
//				else {
					JOptionPane.showMessageDialog(null, "A table contains invalid data.\nPlease enter a correct numeric value.");
//				}
			}
		}
				
	}//End Class CalculateTotalsListener
	//
	//Class AddEmployeeListener
	//
	private class AddEmployeeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent $) {
			if ($.getSource() == btnAddTechName) {
				NewTechnicianFrame tf = new NewTechnicianFrame(employeeTechModel, reportID);
				tf.setVisible(true);
			}
			if ($.getSource() == btnAddUtilityName) {
				NewUtilityFrame uf = new NewUtilityFrame(employeeUtilityModel, reportID);
				uf.setVisible(true);
			}
		}
	}//End Class AddEmployeeListener
	//
	//Class TableChangedListener
	//
	private class RollTableChangedListener implements TableModelListener {
		
		
		
		@Override
		public void tableChanged(TableModelEvent $) {
			
			
			if ($.getType() == TableModelEvent.UPDATE) {
				try {
					
					try(Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
						
						int row = tableRolls.getSelectedRow();
						
						String sql;
						
						PreparedStatement pstmt;
						
						if($.getColumn() == 0) {
							
							sql = "UPDATE Rolls_T SET SheetCode = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setString(1, tableRolls.getValueAt(row, $.getColumn()).toString());
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						if($.getColumn() == 1) {
							
							sql = "UPDATE Rolls_T SET ExtrusionMONum = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setString(1, tableRolls.getValueAt(row, $.getColumn()).toString());
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						if($.getColumn() == 2) {
							
							sql = "UPDATE Rolls_T SET DateTimeMade = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							String date = getDate(tableRolls.getValueAt(row, 2).toString()) + " " + tableRolls.getValueAt(row, 3).toString();
							
							pstmt.setString(1, date);
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						if($.getColumn() == 3) {
								
							sql = "UPDATE Rolls_T SET DateTimeMade = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							String date = getDate(tableRolls.getValueAt(row, 2).toString()) + " " + tableRolls.getValueAt(row, 3).toString();
							
							pstmt.setString(1, date);
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						if($.getColumn() == 4) {
							
							String message = "Cannot Change the Roll Number\nDelete and make a new roll";
							
							JOptionPane.showMessageDialog(rootPane, message);
							
						}
						if($.getColumn() == 5) {
							
							sql = "UPDATE Rolls_T SET RollLength = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setInt(1, Integer.parseInt(tableRolls.getValueAt(row, $.getColumn()).toString()));
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						if($.getColumn() == 6) {
							
							sql = "UPDATE Rolls_T SET RollWeight = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setInt(1, Integer.parseInt(tableRolls.getValueAt(row, $.getColumn()).toString()));
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						if($.getColumn() == 7) {
							
							sql = "UPDATE Rolls_T SET Shots = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setInt(1, Integer.parseInt(tableRolls.getValueAt(row, $.getColumn()).toString()));
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						if($.getColumn() == 8) {
							
							sql = "UPDATE Rolls_T SET AvgPartWeight = ? WHERE RollNum = ? AND FormingReportID = ?";
							pstmt = conn.prepareStatement(sql);
							
							pstmt.setDouble(1, Double.parseDouble(tableRolls.getValueAt(row, $.getColumn()).toString()));
							pstmt.setString(2, tableRolls.getValueAt(row, 4).toString());
							pstmt.setString(3, reportID);
							
							pstmt.executeUpdate();
							
						}
						
						
						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//			if ($.getType() == TableModelEvent.DELETE) {
//				try {
//					System.out.println("Deleting from table");
//					System.out.println($.getColumn());
//					FileOps.makeBackup(ReportDriver.getReport());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}if ($.getType() == TableModelEvent.INSERT) {
//				try {
//					System.out.println("Inserting into table");
//					System.out.println($.getColumn());
//					FileOps.makeBackup(ReportDriver.getReport());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			
		}
		
	}//End Class TableChangedListener
	private class TechTableChangedListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent $) {
			if($.getType() == TableModelEvent.UPDATE) {
				try(Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
					
					int row = tableTechName.getSelectedRow();
					
					String sql;
					
					PreparedStatement pstmt;
					
					if($.getColumn() == 0) {
						String message = "Cannot Change Employee Number\nDelete and make a new record";
						
						JOptionPane.showMessageDialog(rootPane, message);	
					}
					if($.getColumn() == 1) {
						
						sql = "Update Technicians_T SET EmployeeFName = ? WHERE EmployeeID = ? AND FormingReportID = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tableTechName.getValueAt(row, $.getColumn()).toString());
						pstmt.setInt(2, Integer.parseInt(tableTechName.getValueAt(row, 0).toString()));
						pstmt.setString(3, reportID);
						
						pstmt.execute();
						
					}
					if($.getColumn() == 2) {
						
						sql = "Update Technicians_T SET EmployeeLName = ? WHERE EmployeeID = ? AND FormingReportID = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tableTechName.getValueAt(row, $.getColumn()).toString());
						pstmt.setInt(2, Integer.parseInt(tableTechName.getValueAt(row, 0).toString()));
						pstmt.setString(3, reportID);
						
						pstmt.execute();
						
					}
					if($.getColumn() == 3) {
						
						sql = "Update Technicians_T SET EmployeeHoursWorked = ? WHERE EmployeeID = ? AND FormingReportID = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, Integer.parseInt(tableTechName.getValueAt(row, $.getColumn()).toString()));
						pstmt.setInt(2, Integer.parseInt(tableTechName.getValueAt(row, 0).toString()));
						pstmt.setString(3, reportID);
						
						pstmt.execute();
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
					
			}
			
		}
		
	}
	private class UtilityTableChangedListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent $) {
			if($.getType() == TableModelEvent.UPDATE) {
				try(Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
					
					int row = tableUtilityNames.getSelectedRow();
					
					String sql;
					
					PreparedStatement pstmt;
					
					if($.getColumn() == 0) {
						String message = "Cannot Change Employee Number\nDelete and make a new record";
						
						JOptionPane.showMessageDialog(rootPane, message);	
					}
					if($.getColumn() == 1) {
						
						sql = "Update Utility_T SET EmployeeFName = ? WHERE EmployeeID = ? AND FormingReportID = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tableUtilityNames.getValueAt(row, $.getColumn()).toString());
						pstmt.setInt(2, Integer.parseInt(tableUtilityNames.getValueAt(row, 0).toString()));
						pstmt.setString(3, reportID);
						
						pstmt.execute();
						
					}
					if($.getColumn() == 2) {
						
						sql = "Update Utility_T SET EmployeeLName = ? WHERE EmployeeID = ? AND FormingReportID = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, tableUtilityNames.getValueAt(row, $.getColumn()).toString());
						pstmt.setInt(2, Integer.parseInt(tableUtilityNames.getValueAt(row, 0).toString()));
						pstmt.setString(3, reportID);
						
						pstmt.execute();
						
					}
					if($.getColumn() == 3) {
						
						sql = "Update Utility_T SET EmployeeHoursWorked = ? WHERE EmployeeID = ? AND FormingReportID = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, Integer.parseInt(tableUtilityNames.getValueAt(row, $.getColumn()).toString()));
						pstmt.setInt(2, Integer.parseInt(tableUtilityNames.getValueAt(row, 0).toString()));
						pstmt.setString(3, reportID);
						
						pstmt.execute();
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
			
		}
		
	}
	private class ScrapTableChangedListener implements TableModelListener {

		@Override
		public void tableChanged(TableModelEvent $) {
			if($.getType() == TableModelEvent.UPDATE) {
				
				try(Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
					
					int row = tableScrapTickets.getSelectedRow();
					
					String sql;
					
					PreparedStatement pstmt;
					
					if($.getColumn() == 0) {
						String message = "Cannot Change the Roll Number\nDelete and make a new ticket";
						
						JOptionPane.showMessageDialog(rootPane, message);
					}
					if($.getColumn() == 1) {
						sql = "UPDATE Scrap_T SET Weight = ? WHERE ScrapTicketIDNum = ? AND FormingReportID = ?";
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setDouble(1, Double.parseDouble(tableScrapTickets.getValueAt(row, $.getColumn()).toString()));
						pstmt.setInt(2, Integer.parseInt(tableScrapTickets.getValueAt(row, 0).toString()));
						pstmt.setString(3, reportID);
						
						pstmt.execute();
					}
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
			
		}
		
	}
	//
	//Class RemoveTableItemListener
	//
	private class RemoveTableItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent $) {
			if ($.getSource() == btnRemoveTechnician) {
				try {
			      int row = tableTechName.getSelectedRow();
			      if (row > -1) {
			    	  
			    	  
			    	  try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
				    		 
				    		 String sql = "DELETE FROM Technicians_T WHERE EmployeeID = ? AND FormingReportID = ?";
			    		 
			         		 PreparedStatement pstmt = conn.prepareStatement(sql);
			    		 
			         		 pstmt.setInt(1, Integer.parseInt((employeeTechModel.getValueAt(row, 0).toString())));
			         		 pstmt.setString(2, reportID);
			    		 
			         		 pstmt.execute();
			    		 
				    	 }catch (Exception ex) {
				    		 JOptionPane.showMessageDialog(null, ex);
				    	 } finally {
				    		 employeeTechModel.removeRow(row);
				    	 }
			    	  
			    	  
			      }
			    }
			    catch (Exception e) {
			      JOptionPane.showMessageDialog(null, e);
			    }
			}	
			if ($.getSource() == btnRemoveUtility) {
				try {
				      int row = tableUtilityNames.getSelectedRow();
				      if (row > -1) {
				    	  
				    	  
				    	  try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
				    		 
				    		 String sql = "DELETE FROM Utility_T WHERE EmployeeID = ? AND FormingReportID = ?";
			    		 
			         		 PreparedStatement pstmt = conn.prepareStatement(sql);
			    		 
			         		 pstmt.setInt(1, Integer.parseInt((employeeUtilityModel.getValueAt(row, 0).toString())));
			         		 pstmt.setString(2, reportID);
			    		 
			         		 pstmt.execute();
			    		 
				    	 }catch (Exception ex) {
				    		 JOptionPane.showMessageDialog(null, ex);
				    	 } finally {
				    		 employeeUtilityModel.removeRow(row);
				    	 }
				         
				      }
				    }
				    catch (Exception e) {
				      JOptionPane.showMessageDialog(null, e);
				    }
			}
			if ($.getSource() == btnDeleteRoll) {
				try {
				      int row = tableRolls.getSelectedRow();
				      if (row > -1) {
				         
			         	 try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
			    		 
			         		 String sql = "DELETE FROM Rolls_T WHERE RollNum = ? AND FormingReportID = ?";
			    		 
			         		 PreparedStatement pstmt = conn.prepareStatement(sql);
			    		 
			         		 pstmt.setString(1, (rollModel.getValueAt(row, 4).toString()));
			         		 pstmt.setString(2, reportID);
			    		 
			         		 pstmt.execute();
			    		 
				    	 }catch (Exception ex) {
				    		 JOptionPane.showMessageDialog(null, ex);
				    	 } finally {
				    		 rollModel.removeRow(row);
				    	 }
			         
			         
			      }
				   }
				   catch (Exception e) {
				      JOptionPane.showMessageDialog(null, e);
				   }
			}
			if ($.getSource() == btnRemoveScrapTicket) {
		
				try {
				      int row = tableScrapTickets.getSelectedRow();
				      if (row > -1) {
				    	  
				    	 try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
				    		 
				    		 String sql = "DELETE FROM Scrap_T WHERE ScrapTicketIDNum = ? AND FormingReportID = ?";
				    		 
				    		 PreparedStatement pstmt = conn.prepareStatement(sql);
				    		 
				    		 pstmt.setInt(1, Integer.parseInt(scrapModel.getValueAt(row, 0).toString()));
				    		 pstmt.setString(2, reportID);
				    		 
				    		 pstmt.execute();
				    		 
				    	 }catch (Exception ex) {
				    		 JOptionPane.showMessageDialog(null, ex);
				    	 } finally {
				    		 scrapModel.removeRow(row);
				    	 }
				    	  
				         
				      }
				   }
				   catch (Exception e) {
				      JOptionPane.showMessageDialog(null, e);
				   }
			}
		}
			
	}//End Class RemoveTableItemListener
	//
	//Class AddScrapTicket
	//
	private class AddScrapTicket implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent $) {
				NewScrapTicketFrame st = new NewScrapTicketFrame(scrapModel, reportID);
				st.setVisible(true);
			}
			
	}//End Class AddScrapTicket
	
	
	//
	//Class SetInfoListener
	//
//	private class SetInfoListener implements ActionListener {
////		final int MAX_FEED_LENGTH = 100;
////		final double CONVERSION = 25.4;
////		String date;
////		String shift;
////		String crusher;
////		String machineNumber;
////		String workOrder;
////		String productCode;
////		String productDescription;
////		double feedLength;
//		DecimalFormat df = new DecimalFormat("###.00");
//		
//		@Override
//		public void actionPerformed(ActionEvent $) {
//			
//			try (Connection conn = DriverManager.getConnection(Constants.DB_URL)){
//				String sql = "INSERT INTO Employee(EmployeeID, EmployeeName, HoursAtMachine) "
//						+ "VALUES(?,?,?)";
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//
//				for(int i = 0; i < employeeTechModel.getRowCount(); i++) {
//					
//					pstmt.setInt(1, Integer.parseInt(employeeTechModel.getValueAt(i, 0).toString()));
//					pstmt.setString(2, employeeTechModel.getValueAt(i, 1).toString());
//					pstmt.setInt(3, Integer.parseInt(employeeTechModel.getValueAt(i, 2).toString()));
//					
//					pstmt.addBatch();
//					
//				}
//				
//				pstmt.executeBatch();
//				
////				date = txtDate.getText();
////				shift = shiftComboBox.getSelectedItem().toString();
////				crusher = crusherLetterComboBox.getSelectedItem().toString();
////				machineNumber = textFieldMachineNumber.getText();
////				workOrder = textFieldWorkOrder.getText();
////				productCode = textFieldProductCode.getText();
////				productDescription = textFieldProductDescription.getText();
////				feedLength = Double.parseDouble(textFieldFeedLength.getText());
////				if (feedLength < MAX_FEED_LENGTH) {
////					feedLength = feedLength * CONVERSION;
////					textFieldFeedLength.setText(df.format(feedLength));
////				}
////				txtDate.setEnabled(false);
////				shiftComboBox.setEnabled(false);
////				crusherLetterComboBox.setEnabled(false);
////				textFieldMachineNumber.setEnabled(false);
////				textFieldWorkOrder.setEnabled(false);
////				textFieldProductCode.setEnabled(false);
////				textFieldProductDescription.setEnabled(false);
////				textFieldFeedLength.setEnabled(false);
////				btnUpdateInfo.setEnabled(false);
////				FileOps.makeBackup(ReportDriver.getReport());
//			}	
//			catch (Exception e) {
//
//				JOptionPane.showMessageDialog(null, "Invalid Data has been entered into the Technician Table");
//			}
//			
//			try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
//				
//				String sql = "INSERT INTO Employee(EmployeeID, EmployeeName, HoursAtMachine) "
//						+ "VALUES(?,?,?)";
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//
//				for(int i = 0; i < employeeUtilityModel.getRowCount(); i++) {
//					
//					pstmt.setInt(1, Integer.parseInt(employeeUtilityModel.getValueAt(i, 0).toString()));
//					pstmt.setString(2, employeeUtilityModel.getValueAt(i, 1).toString());
//					pstmt.setInt(3, Integer.parseInt(employeeUtilityModel.getValueAt(i, 2).toString()));
//					
//					pstmt.addBatch();
//					
//				}
//				
//				pstmt.executeBatch();
//				
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "Invalid Data has been entered into the Utility Table");
//			}
//			
//			try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
//				String sql = "INSERT INTO Scrap(TicketID, Weight) "
//						+ "VALUES(?,?)";
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//
//				for(int i = 0; i < scrapModel.getRowCount(); i++) {
//					
//					pstmt.setInt(1, Integer.parseInt(scrapModel.getValueAt(i, 0).toString()));
//					pstmt.setDouble(2, Double.parseDouble(scrapModel.getValueAt(i, 1).toString()));
//					
//					pstmt.addBatch();
//					
//				}
//				
//				pstmt.executeBatch();
//				
//			} catch (Exception e) {
//				
//				System.out.println(e.getMessage());
//				
//				JOptionPane.showMessageDialog(null, "Invalid Data has been entered into the Scrap Table");
//			}
//			
//			try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
//				
//				String month;
//				String year;
//				String day;
//				String hour;
//				String minute;
//				
//				String sql = "INSERT INTO rolls(SheetCode, ExtrusionMO, DateTimeMade, RollNumber, RollLength, RollWeight, TotalShots, AvgPartWeight) "
//						+ "VALUES(?,?,?,?,?,?,?,?)";
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//
//				for(int i = 0; i < rollModel.getRowCount(); i++) {
//					
//				
//					
//					String dateTime = rollModel.getValueAt(i, 2).toString() 
//							+ " " + rollModel.getValueAt(i, 3);
//					
//					month = dateTime.substring(0, 2);
//					day = dateTime.substring(3, 5);
//					year = dateTime.substring(6, 10);
//					hour = dateTime.substring(11, 13);
//					minute = dateTime.substring(14, 16);
//					
//					System.out.println(dateTime);
//					
//					System.out.println(year + "-"
//									   + month + "-"
//									   + day + " "
//									   + hour + ":"
//									   + minute);
//					
//					
//					pstmt.setString(1, rollModel.getValueAt(i, 0).toString());
//					pstmt.setString(2, rollModel.getValueAt(i, 1).toString());
//					pstmt.setString(3, year + "-"
//							   		   + month + "-"
//							           + day + " "
//							           + hour + ":"
//							           + minute);
//					pstmt.setInt(4, Integer.parseInt(rollModel.getValueAt(i, 4).toString()));
//					pstmt.setInt(5, Integer.parseInt(rollModel.getValueAt(i, 5).toString()));
//					pstmt.setInt(6, Integer.parseInt(rollModel.getValueAt(i, 6).toString()));
//					pstmt.setInt(7, Integer.parseInt(rollModel.getValueAt(i, 7).toString()));
//					pstmt.setDouble(8, Double.parseDouble(rollModel.getValueAt(i, 8).toString()));
//					
//					pstmt.addBatch();
//					
//				}
//				
//				pstmt.executeBatch();
//				
//				
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(null, "Invalid Data has been entered into the Roll Table");
//			}
//			
//			
//			String month;
//			String day;
//			String year;
//			try {
//				month = txtDate.getText(0, 2);
//				System.out.println(month);
//				day = txtDate.getText(3, 2);
//				System.out.println(day);
//				year = txtDate.getText(6, 4);
//				System.out.println(year);
//				
//			} catch (BadLocationException e) {
//				e.printStackTrace();
//			}
//			
////			String month;
////			String day;
////			String year;
////			try {
////				month = txtDate.getText(0, 2);
////				System.out.println(month);
////				day = txtDate.getText(3, 2);
////				System.out.println(day);
////				year = txtDate.getText(6, 4);
////				System.out.println(year);
////				
////			} catch (BadLocationException e) {
////				e.printStackTrace();
////			}
//			
//			
//			
//		}
//	}//End Class SetInfoListener
	//
	//Class MenuExitButtonListener
	//
	private class MenuExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent $) {
			System.exit(0);
			
		}
		
	}//End Class MenuExitButtonListener
	//
	//createBackup Method
	//
//	private void createBackup(String filename) throws FileNotFoundException {
//		
//		
//		
//	}//End createBackup
	//
	//createFormatter method
	//
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
	
	private Action actionRolls = new AbstractAction()
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 7424695927623146974L;

		public void actionPerformed(ActionEvent e)
	    {
	        TableCellListener tcl = (TableCellListener)e.getSource();
	        
	        if(tableRolls.getSelectedColumn() == 4) {
	        	rollModel.setValueAt(tcl.getOldValue(), tableRolls.getSelectedRow(), 4);
	        }
	    }
	};
	
	private Action actionScrap = new AbstractAction()
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 7424695927623146974L;

		public void actionPerformed(ActionEvent e)
	    {
	        TableCellListener tcl = (TableCellListener)e.getSource();
	        
	        if(tableScrapTickets.getSelectedColumn() == 0) {
	        	scrapModel.setValueAt(tcl.getOldValue(), tableScrapTickets.getSelectedRow(), 0);
	        }
	    }
	};
	
	private Action actionTech = new AbstractAction()
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 7424695927623146974L;

		public void actionPerformed(ActionEvent e)
	    {
	        TableCellListener tcl = (TableCellListener)e.getSource();
	        
	        if(tableTechName.getSelectedColumn() == 0) {
	        	employeeTechModel.setValueAt(tcl.getOldValue(), tableTechName.getSelectedRow(), 0);
	        }
	        
	    }
	};
	
	private Action actionUtility = new AbstractAction()
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 7424695927623146974L;

		public void actionPerformed(ActionEvent e)
	    {
	        TableCellListener tcl = (TableCellListener)e.getSource();
	        
	        if(tableUtilityNames.getSelectedColumn() == 0) {
	        	employeeUtilityModel.setValueAt(tcl.getOldValue(), tableUtilityNames.getSelectedRow(), 0);
	        }
	        
	    }
	};
	
	private String getDate(String date) {
		String month;
		String day;
		String year;
		
		month = date.substring(0, 2);
		day = date.substring(3, 5);
		year = date.substring(6);
		
		return year + "-" + month + "-" + day;
	}
	
//	private void loadFile(File file) throws FileNotFoundException{
//		
//	}
}//End ReportGUI class