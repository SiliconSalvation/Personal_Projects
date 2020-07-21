import java.io.File;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ReportDriver {
	private final static String FILE_BACKUP_NAME = "backup.dat";
	private static ReportGUI gui;
	
	public static void main(String[] args) throws SQLException {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new NewFormingReportFrame();
		//gui = new ReportGUI();
		
	}
	public static ReportGUI getReport() {
		return gui;
	}
}