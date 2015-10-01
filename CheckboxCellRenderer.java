package bvclient.project;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckboxCellRenderer extends JCheckBox
        implements TableCellRenderer {

        TableCellRenderer delegate;
        
        public CheckboxCellRenderer(TableCellRenderer defaultRenderer){
        this.delegate = defaultRenderer;
        }
        
        public CheckboxCellRenderer(){
            
        }
        
        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {
            
            SimpleDateFormat df = new SimpleDateFormat("mm:ss");
            
            Value v = (Value) value;
            this.setSelected(v.selected);
            //if (v.selected)
            this.setText(v.value);
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            return this;
        }
    }