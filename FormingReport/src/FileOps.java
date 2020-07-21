//import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileOps implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7838329830131821142L;
	private final static String FILENAME = "backup.dat";
	
//	public static void makeBackup(ReportGUI gui) throws IOException {
//		 FileOutputStream outStream = new FileOutputStream(FILENAME);
//         ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
//         objectOutputFile.writeObject(gui);
//         objectOutputFile.close();
//	}
//	public static void saveReport(ReportGUI gui, String fileName) throws IOException {
//		FileOutputStream outStream = new FileOutputStream(fileName);
//        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
//        objectOutputFile.writeObject(gui);
//        objectOutputFile.close();
//	}
	
//	public static void loadReport() {
//		 JFileChooser chooser = new JFileChooser();
//			
//		 int returnVal = chooser.showOpenDialog(null);
//		 try {
//			 if(returnVal == JFileChooser.APPROVE_OPTION) {
////		           String fileName = chooser.getSelectedFile().getPath();
//		           FileInputStream inStream = new FileInputStream(FILENAME);
//		           ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
//		           ReportGUI loadedReport = ((ReportGUI)(objectInputFile.readObject()));
//		           loadedReport.setVisible(true);
//		           objectInputFile.close();
//			 }      
//		 }catch (EOFException eof) {
//			
//		 }catch (Exception ex) {
//			 JOptionPane.showMessageDialog(null, "There was a problem loading the file");
//		 }
//	}
	
}