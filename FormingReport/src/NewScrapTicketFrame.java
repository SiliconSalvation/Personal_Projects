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
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;

public class NewScrapTicketFrame extends JFrame {
	
	private JTextField textFieldIDNum;
	private JTextField textFieldWeight;
	private JPanel panelMain;
	private JPanel panelTicketID;
	private JPanel panelWeight;
	private JPanel panelButtons;
	private JButton btnAdd;
	private JButton btnCancel;
	private DefaultTableModel scrapModel;
	private String reportID;
	
	public NewScrapTicketFrame(DefaultTableModel scrapModel, String reportID) {
		this.scrapModel = scrapModel;
		this.reportID = reportID;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(300, 300);
		setTitle("New Scrap Ticket");
		
		panelMain = new JPanel();
		panelMain.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New Scrap Ticket", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(2, 0, 0, 0));
		
		panelTicketID = new JPanel();
		panelTicketID.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Scrap Ticket ID #", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.add(panelTicketID);
		
		textFieldIDNum = new JTextField();
		panelTicketID.add(textFieldIDNum);
		textFieldIDNum.setColumns(10);
		
		panelWeight = new JPanel();
		panelWeight.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pounds", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMain.add(panelWeight);
		
		textFieldWeight = new JTextField();
		panelWeight.add(textFieldWeight);
		textFieldWeight.setColumns(10);
		
		panelButtons = new JPanel();
		getContentPane().add(panelButtons, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add");
		panelButtons.add(btnAdd);
		btnAdd.addActionListener(new AddScrapListener());
		
		
		btnCancel = new JButton("Cancel");
		panelButtons.add(btnCancel);
		btnCancel.addActionListener(new CancelScrapListener());
		
		setLocationRelativeTo(null);
	}
	
	private class AddScrapListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] newScrapTicket = {textFieldIDNum.getText(), textFieldWeight.getText()};
			scrapModel.addRow(newScrapTicket);
			newScrapTicket = null;
			
			try (Connection conn = DriverManager.getConnection(Constants.DB_URL)) {
				
				String sql = "INSERT INTO Scrap_T(ScrapTicketIDNum, FormingReportID, Weight) VALUES(?,?,?)";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(textFieldIDNum.getText()));
				pstmt.setString(2, reportID);
				pstmt.setDouble(3, Double.parseDouble(textFieldWeight.getText()));
				
				
				pstmt.execute();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
			
			dispose();
		}
		
	}
	
	private class CancelScrapListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			
		}
		
	}
	
}