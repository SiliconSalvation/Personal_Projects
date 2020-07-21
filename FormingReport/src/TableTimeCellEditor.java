import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.text.MaskFormatter;

class TableTimeCellEditor extends AbstractCellEditor implements TableCellEditor {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComponent component = new JFormattedTextField(createFormatter("##':##"));

  @Override
public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
    int rowIndex, int vColIndex) {
	((JFormattedTextField) component).setToolTipText("Enter as 'HH:MM'");;
    ((JFormattedTextField) component).setText((String) value);

    return component;
  }

  @Override
public Object getCellEditorValue() {
    return ((JFormattedTextField) component).getText();
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