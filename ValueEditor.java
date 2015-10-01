package bvclient.project;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ValueEditor extends AbstractCellEditor
        implements TableCellEditor, ItemListener {
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        
        private CheckboxCellRenderer vr = new CheckboxCellRenderer();

        public ValueEditor() {
            vr.addItemListener(this);
        }

        @Override
        public Object getCellEditorValue() {
            return vr.isSelected();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int col) {
            Value v = (Value) value;
            vr.setSelected(v.selected);
            if (!v.selected)
                vr.setText("00:00");
            else
                vr.setText(v.value);
            return vr;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            this.fireEditingStopped();

            if (e.getStateChange() == ItemEvent.DESELECTED)
            {
                vr.setText("00:00");
            }
        }
    }
